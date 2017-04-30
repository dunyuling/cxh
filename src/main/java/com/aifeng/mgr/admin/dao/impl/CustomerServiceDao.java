package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.ICustomerServiceDao;
import com.aifeng.mgr.admin.model.CustomerService;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pro on 17-4-27.
 */
@Repository
public class CustomerServiceDao extends BaseDao<CustomerService> implements ICustomerServiceDao {

    public CustomerService getCustomerServiceByProvince(String province) {
        Map<String, Object> params = new HashMap<>();
        params.put("province", province);
        String sql = "from CustomerService where province =:province";
        return this.findOneByHql(sql, params);
    }
}