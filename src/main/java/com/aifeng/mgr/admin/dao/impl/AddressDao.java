package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IAddressDao;
import com.aifeng.mgr.admin.model.Address;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AddressDao extends BaseDao<Address> implements IAddressDao {

    public void test() {
        this.countAll();
    }

    public List<Map<String,Object>> getAddressFee(int page,int pageSize) {
        String str = "select a.id ,a.area,a.city,a.province ,af.amount from address a left join address_fee af on a.id = af.address_id  " +
                "where a.active is true limit " + pageSize + " offset " + (page-1) * pageSize + ";";
        return this.findBySql(str);
    }
}
