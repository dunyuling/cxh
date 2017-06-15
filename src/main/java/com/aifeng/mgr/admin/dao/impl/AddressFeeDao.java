package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IAddressFeeDao;
import com.aifeng.mgr.admin.model.AddressFee;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AddressFeeDao extends BaseDao<AddressFee> implements IAddressFeeDao {

    public AddressFee getByAddressId(long address_id) {
        Map<String, Object> params = new HashMap<>();
        params.put("address_id", address_id);
        String sql = "from AddressFee where address_id =:address_id";
        return this.findOneByHql(sql,params);
    }
}
