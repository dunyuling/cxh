package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.core.util.SpringUtil;
import com.aifeng.mgr.admin.dao.impl.AgentDao;
import com.aifeng.mgr.admin.model.AddressFee;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.service.IAgentService;
import com.aifeng.util.DateStyle;
import com.aifeng.util.DateUtil;
import com.aifeng.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
    private MessageService messageService;

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
        if (agent != null) {
            long af_id = addressFeeService.getAddressFee(getAddressId(addr)).getId();
            boolean proxied = proxyAddressService.checkProxied(af_id);
            if (proxied) {
                result = "您或其它代理商已经申请过代理该地区";
                success = true;
            } else {
                result = "身份证信息未正确填写";
            }
            map.put("result", result);
            map.put("success", success);
            return map;
        }


        agent = agentDao.getAgentByMobile(IDCard);
        if (agent != null) {
            long af_id = addressFeeService.getAddressFee(getAddressId(addr)).getId();
            boolean proxied = proxyAddressService.checkProxied(af_id);
            if (proxied) {
                result = "您或其它代理商已经申请过代理该地区";
                success = true;
            } else {
                result = "该手机号已被使用";
            }
            map.put("result", result);
            map.put("success", success);
            return map;
        }

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
        boolean proxied = proxyAddressService.checkProxied(addressFee.getId());
        if (proxied) {
            result = "您或其它代理商已经申请过代理该地区";
        } else {
            proxyAddressService.save(agent.getId(), addressFee.getId());
            success = true;
        }
        map.put("result", result);
        map.put("success", success);
        return map;
    }

    @Transactional
    public synchronized String reAddAgent(String userid, String addr) {
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
    public List<Map<String, Object>> getAgent(int page, int size, String addr) {
        return agentDao.getAgentByAddr(page, size, addr);
    }

    @Transactional
    public int getCount(String addr) {
        return agentDao.getAgentCount(addr);
    }

    @Transactional
    public List<Map<String, Object>> getAgent(int page, int size, String mobile, String IDCard, String expire_days, String addr) {
        return agentDao.getAgentByAddr(page, size, mobile, IDCard, expire_days, addr);
    }

    @Transactional
    public int queryCount(String mobile, String IDCard, String expire_days, String addr) {
        return agentDao.getAgentCount(mobile, IDCard, expire_days, addr);
    }

    @Transactional
    public void editAgent(long id, String name, String IDCard, String mobile, String corpName, String licenceImg, String expireDate) {
        //mobile 不能修改，在企业号后台已经通过短信方式验证
        //user_id 也不能修改
        Agent agent = agentDao.findById(id);
        agent.setName(name);
        agent.setIDCard(IDCard);
        agent.setMobile(mobile);
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
    public Agent getAgent(String userid) {
        return agentDao.getAgentByUserId(userid);
    }

    @Transactional
    public Map<String, Object> getAgentSubmitMsgFromWx(String user_id) {
        return agentDao.getAgentSubmitMessageFromWx(user_id);
    }

    @Transactional
    public Agent getAgentByMobile(String mobile) {
        return agentDao.getActiveAgentByMobile(mobile);
    }

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    @Transactional
    public void balanceLow() {
        messageService = messageService == null ? SpringUtil.getBean("messageService") : messageService;

        List<Map<String, Object>> list = agentDao.findAllBalanceLow();
        for (Map<String, Object> map : list) {
            StringBuilder content = new StringBuilder();
            content.append("尊敬的").append(map.get("name")).append("用户,您当前余额为:").append(map.get("money")).append("元。");
            content.append("请及时充值，以免遗漏会员提醒，给您造成损失。");
            System.out.println("-----: " + map.get("name") + " \t " + map.get("money") + "\t" + content);
        }
    }

    @Transactional
    public void editAgentPwd(long id, String pwd) {
        Agent agent = this.agentDao.findById(id);
        agent.setPwd(Md5.getMd5(pwd));
        agentDao.update(agent);
    }

    @Transactional
    public Map<String, Object> getByMobile(String mobile) {
        return agentDao.getByMobile(mobile);
    }

    @Transactional
    public void auditCancel(long id) {
        Agent agent = agentDao.findById(id);
        agent.setActive(false);
        update(agent);

        proxyAddressService.auditCancelProxyAddress(id);
    }

    @Transactional
    public void reAudit(long id) {
        Agent agent = agentDao.findById(id);
        agent.setActive(true);
        update(agent);
    }
}