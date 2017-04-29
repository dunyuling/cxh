package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.AddressFeeDao;
import com.aifeng.mgr.admin.model.AddressFee;
import com.aifeng.mgr.admin.service.IAddressFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pro on 17-4-24.
 */
@Service
public class AddressFeeService extends BaseService<AddressFee> implements IAddressFeeService {


    private final
    AddressFeeDao addressFeeDao;

    @Autowired
    public AddressFeeService(AddressFeeDao addressFeeDao) {
        this.addressFeeDao = addressFeeDao;
    }


    @Transactional
    public AddressFee getAddressFee(long address_id) {
        return addressFeeDao.getByAddressId(address_id);
    }

    @Transactional
    public AddressFee getOne(long id) {
        return addressFeeDao.findById(id);
    }

    @Transactional
    public void saveAddressFee(long address_id,int amount) {
        AddressFee addressFee = null;
        try {
            addressFee =  getAddressFee(address_id);
        } catch (DataAccessException e) {
//            e.printStackTrace();
            System.out.println("");
        }
        if(addressFee == null) {
            addressFee = new AddressFee();
            addressFee.setAddress_id(address_id);
        }
        addressFee.setAmount(amount);
        addressFeeDao.insert(addressFee);
    }

    @Transactional
    public void delByAddressId(long address_id) {
        addressFeeDao.deleteByWhere("address_id = " + address_id);
    }
}