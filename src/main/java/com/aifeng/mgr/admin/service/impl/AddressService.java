package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.init_address.AdminRanking;
import com.aifeng.mgr.admin.dao.impl.AddressDao;
import com.aifeng.mgr.admin.model.Address;
import com.aifeng.mgr.admin.model.AddressFee;
import com.aifeng.mgr.admin.model.ProxyAddress;
import com.aifeng.mgr.admin.service.IAddressService;
import com.aifeng.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-24.
 */
@Service
@Scope(value="singleton")
public class AddressService extends BaseService<Address> implements IAddressService {


    private final
    AddressDao addressDao;

    private final
    AddressFeeService addressFeeService;
    private final ProxyAddressService proxyAddressService;

    @Autowired
    public AddressService(AddressDao addressDao, AddressFeeService addressFeeService, ProxyAddressService proxyAddressService) {
        this.addressDao = addressDao;
        this.addressFeeService = addressFeeService;
        this.proxyAddressService = proxyAddressService;
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
        /*Address address = new Address("2","3","1");
        addressDao.insert(address);*/
    }

    @Transactional
    public Map<String, Object> getAll(int page) {
        long total = addressDao.countAll();
        long mod = total % 10;
        long divide = total / 10;
        long totalPage = mod == 0 ? divide : (divide + 1);
        Pager pager = new Pager(page);
        List<Address> addresses = addressDao.findAll(pager);

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("totalPage", totalPage);
        map.put("addresses", addresses);
        return map;
    }

    @Transactional
    public long getTotal() {
        return addressDao.countAll();
    }

    @Transactional
    public int getTotalPage(int pageSize) {
        long total = addressDao.countAll();
        long mod = total % pageSize;
        long divide = total / pageSize;
        return (int) (mod == 0 ? divide : (divide + 1));
    }

    @Transactional
    public List<Address> getByPage(int page) {
        Pager pager = new Pager();
        return addressDao.findAll(pager);
    }

    @Transactional
    public List<Map<String, Object>> getByPage(int page, int size) {
        return addressDao.getAddressFee(page, size);
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
    public List<Map<String, Object>> getAddressFee(String province, String city, String area) {
        return addressDao.getAddressFee(province, city, area);
    }

    @Transactional
    public Address getOne(long id) {
        return this.addressDao.findById(id);
    }

    @Transactional
    public Address getAddressByProxyAddressId(long proxyAddressId) {
        ProxyAddress proxyAddress = proxyAddressService.getOne(proxyAddressId);
        long af_id = proxyAddress.getAf_id();
        long address_id = addressFeeService.getOne(af_id).getAddress_id();
        return this.addressDao.findById(address_id);
    }

    @Transactional
    public List<Address> getAll() {
        return addressDao.getAllAddress();
    }
}