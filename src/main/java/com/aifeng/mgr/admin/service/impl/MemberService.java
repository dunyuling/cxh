package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.constants.InsuranceType;
import com.aifeng.mgr.admin.dao.impl.MemberDao;
import com.aifeng.mgr.admin.model.Member;
import com.aifeng.mgr.admin.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pro on 17-4-27.
 */
@Service
public class MemberService extends BaseService<Member> implements IMemberService {

    private final MemberDao memberDao;
    private final AddressService addressService;

    @Autowired
    public MemberService(MemberDao memberDao, AddressService addressService) {
        this.memberDao = memberDao;
        this.addressService = addressService;
    }

    @Transactional
    public String registerMember(String province,String city,String area,String name,String mobile,int index) {
        String msg = "";
        try {
            long address_id = addressService.getAddressId(province,city,area);
            if(address_id == 0) msg = "注册失败！相应地区不存在";
            else {
                Member member = new Member();
                member.setName(name);
                member.setMobile(mobile);
                member.setType(InsuranceType.getTypeByIndex(index));
                member.setAddress_id(address_id);
                memberDao.insert(member);
                msg = "注册成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
}
