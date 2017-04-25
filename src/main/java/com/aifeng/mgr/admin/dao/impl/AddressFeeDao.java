package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IAddressFeeDao;
import com.aifeng.mgr.admin.model.AddressFee;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AddressFeeDao extends BaseDao<AddressFee> implements IAddressFeeDao {

    /*public void test() {
        this.countAll();
    }

    public List<Map<String,Object>> getAddressFee(int page,int pageSize) {
        String str = "select a.id ,a.area,a.city,a.province ,af.amount from address a left join address_fee af on a.id = af.address_id  " +
                "where a.active is true limit " + pageSize + " offset " + page * pageSize + ";";
        return this.findBySql(str);
    }*/

    public AddressFee getByAddressId(long address_id) {
//        String str = "select * from address_fee where address_id = " + address_id;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("address_id", address_id);
        StringBuffer sb = new StringBuffer("from AddressFee where address_id =:address_id");
        return this.findOneByHql(sb.toString(),params);
    }
}
