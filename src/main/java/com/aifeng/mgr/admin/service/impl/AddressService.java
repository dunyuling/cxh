package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.init_address.AdminRanking;
import com.aifeng.mgr.admin.dao.impl.AddressDao;
import com.aifeng.mgr.admin.model.Address;
import com.aifeng.mgr.admin.model.AddressFee;
import com.aifeng.mgr.admin.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class AddressService extends BaseService<Address> implements IAddressService {

    private final AddressDao addressDao;
    private final AddressFeeService addressFeeService;

    @Autowired
    public AddressService(AddressDao addressDao, AddressFeeService addressFeeService) {
        this.addressDao = addressDao;
        this.addressFeeService = addressFeeService;
    }

    @Transactional
    public void init(List<AdminRanking> areas, List<AdminRanking> cites, List<AdminRanking> provinces) {

        for (AdminRanking area : areas) {
            for (AdminRanking city : cites) {
                if (area.getParent_code().equals(city.getCode())) {
                    for (AdminRanking province : provinces) {
                        if (province.getCode().equals(city.getParent_code())) {
                            Address address = new Address(area.getName(), city.getName(), province.getName());
                            addressDao.insert(address);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    @Transactional
    public List<Map<String, Object>> getByPage(int page, int size, String addr) {
        return addressDao.getAddressFee(page, size, addr);
    }

    @Transactional
    public long getTotal(String addr) {
        return addressDao.getAddressFeeCount(addr);
    }

    @Transactional
    public void saveAddress(String province, String city, String area, int amount) {
        Address address = new Address();
        address.setProvince(province);
        address.setCity(city);
        address.setArea(area);
        address = addressDao.insert(address);

        addressFeeService.saveAddressFee(address.getId(), amount);
    }

    @Transactional
    public void editAddress(long id, String province, String city, String area, int amount) {
        Address address = addressDao.findById(id);
        address.setProvince(province);
        address.setCity(city);
        address.setArea(area);
        address = addressDao.update(address);


        AddressFee addressFee = addressFeeService.getAddressFee(address.getId());
        addressFee.setAmount(amount);
        addressFeeService.update(addressFee);
    }

    @Transactional
    public void delAddress(long id) {
        Address address = addressDao.findById(id);
        addressDao.delete(address);
        addressFeeService.delByAddressId(address.getId());
    }

    @Transactional
    public long getAddressId(String province, String city, String area) {
        return addressDao.getAddressId(province, city, area);
    }

    @Transactional
    public List<Map<String, Object>> getAddressFee(int page, int pageSize, String province, String city, String area, String addr) {
        return addressDao.getAddressFee(page, pageSize, province, city, area, addr);
    }

    @Transactional
    public int getAddressFeeCount(String province, String city, String area, String addr) {
        return addressDao.getAddressFeeCount(province, city, area, addr);
    }
}