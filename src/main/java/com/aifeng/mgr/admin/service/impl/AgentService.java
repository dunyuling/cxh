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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public synchronized Map<String, Object> saveAgent(String name, String mobile, String IDCard, String corpName, String licenceImg, String expireDate, String addr, String user_id) {
        Map<String, Object> map = new HashMap<>();
        boolean success = false;
        String result = "代理已成功添加，请等待运营认证";
        Agent agent = agentDao.getAgentByIDCard(IDCard);
        if (agent == null) {
            agent = new Agent();
            agent.setName(name);
            agent.setMobile(mobile);
            agent.setIDCard(IDCard);
            agent.setCorpName(corpName);
            agent.setLicenseImg(licenceImg);
            agent.setExpireDate(DateUtil.parseDate(DateStyle.YYYYMMDD, expireDate));
            agent.setUserid(user_id);
            agent = agentDao.insert(agent);

            AddressFee addressFee = addressFeeService.getAddressFee(getAddressId(addr));
            proxyAddressService.save(agent.getId(), addressFee.getId());
            success = true;
        } else {
            long af_id = addressFeeService.getAddressFee(getAddressId(addr)).getId();
            boolean proxied = proxyAddressService.checkProxied(af_id);
            if (proxied) {
                result = "您或其它代理商已经申请过代理该地区";
            }
        }
        map.put("result", result);
        map.put("success", success);
        return map;
    }

    @Transactional
    public synchronized String reAdd(String userid, String addr) {
        String result = "代理已成功添加，请等待运营认证";

        AddressFee addressFee = addressFeeService.getAddressFee(getAddressId(addr));
        long af_id = addressFee.getId();
        boolean proxied = proxyAddressService.checkProxied(af_id);
        if (proxied) {
            result = "您或其它代理商已经申请过代理该地区";
        } else {
            Agent agent = agentDao.getAgentByUserId(userid);
            proxyAddressService.save(agent.getId(), addressFee.getId());
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


    @Transactional
    public List<Map<String, Object>> getPagerAgent(int page, int size) {
        return agentDao.getAgents(page, size);
    }

    @Transactional
    public long getTotal() {
        return agentDao.countAll();
    }

    @Transactional
    public void editAgent(long id, String name, String IDCard, String corpName, String licenceImg, String expireDate) {
        //mobile 不能修改，在企业号后台已经通过短信方式验证
        //user_id 也不能修改
        Agent agent = agentDao.findById(id);
        agent.setName(name);
        agent.setIDCard(IDCard);
        agent.setCorpName(corpName);
        if (licenceImg != null && !licenceImg.isEmpty()) {
            agent.setLicenseImg(licenceImg);
        }
        agent.setExpireDate(DateUtil.parseDate(DateStyle.YYYYMMDD, expireDate));
        agentDao.update(agent);
    }

    @Transactional
    public void delAgent(long id) {
        Agent agent = agentDao.findById(id);
        agentDao.delete(agent);
    }

    @Transactional
    public Agent getAgentByUserid(String userid) {
        return agentDao.getAgentByUserId(userid);
    }
}