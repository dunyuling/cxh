package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.CustomerServiceDao;
import com.aifeng.mgr.admin.model.CustomerService;
import com.aifeng.mgr.admin.service.ICustomerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pro on 17-4-28.
 */
@Service
public class CustomerServiceService extends BaseService<CustomerService> implements ICustomerServiceService {

    private final CustomerServiceDao customerServiceDao;

    @Autowired
    public CustomerServiceService(CustomerServiceDao customerServiceDao) {
        this.customerServiceDao = customerServiceDao;
    }

    @Transactional
    public void save(String name, String province) {
        CustomerService customerService = new CustomerService();
        customerService.setName(name);
        customerService.setProvince(province);
        customerServiceDao.insert(customerService);
    }


    @Transactional
    public String update(String name, String province) {
        String result = "设置成功";
        CustomerService customerService = this.customerServiceDao.getCustomerServiceByProvince(province);
        if(customerService != null) {
            result = "该省已设置过客服";
        } else {
            save(name,province);
        }
        return result;
    }
}