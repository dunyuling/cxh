package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.AgentDao;
import com.aifeng.mgr.admin.model.AddressFee;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.model.ProxyAddress;
import com.aifeng.mgr.admin.service.IAgentService;
import com.aifeng.util.DateStyle;
import com.aifeng.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.SynchronousQueue;

@Service
public class AgentService extends BaseService<Agent> implements IAgentService {

    private final AgentDao agentDao;
    private final AddressService addressService;
    private final AddressFeeService addressFeeService;
    private final ProxyAddressService proxyAddressService;

    @Autowired
    public AgentService(AgentDao agentDao, AddressService addressService, AddressFeeService addressFeeService, ProxyAddressService proxyAddressService) {
        this.agentDao = agentDao;
        this.addressService = addressService;
        this.addressFeeService = addressFeeService;
        this.proxyAddressService = proxyAddressService;
    }

    @Transactional
    public synchronized String saveAgent(String name, String mobile, String IDCard, String corpName, String licenceImg, String expireDate, String addr) {
        String result = "代理已成功添加，请等待后台认证";
        Agent agent = agentDao.getAgentByIDCard(IDCard);
        if (agent == null) {
            agent.setName(name);
            agent.setMobile(mobile);
            agent.setIDCard(IDCard);
            agent.setCorpName(corpName);
            agent.setBusinessLicenseImg(licenceImg);
            agent.setBusinessLicenseExpireDate(DateUtil.parseDate(DateStyle.YYYYMMDD, expireDate));
            agent = agentDao.insert(agent);

            AddressFee addressFee = addressFeeService.getAddressFee(getAddressId(addr));
            proxyAddressService.save(agent.getId(), addressFee.getId());
        } else {
            long af_id = addressFeeService.getAddressFee(getAddressId(addr)).getId();
            boolean otherAdded = proxyAddressService.checkOthersProxied(af_id);
            if (otherAdded) {
                result = "其它代理商已经代理过该地区";
            } else {
                boolean selfAdded = proxyAddressService.checkSelfProxied(agent.getId(), af_id);
                if (selfAdded) {
                    result = "您正在代理该地区或已提交代理该地区的申请";
                }
            }
        }
        return result;
    }

    @Transactional
    private long getAddressId(String addr) {
        String arr[] = addr.split("-");
        return addressService.getAddressId(arr[0], arr[1], arr[2]);
    }

    @Transactional
    public Agent getAgentByProxyAddressId(long proxyAddressId) {
        ProxyAddress proxyAddress = proxyAddressService.getOne(proxyAddressId);
        long agent_id = proxyAddress.getAgent_id();
        return this.agentDao.findById(agent_id);
    }
}
