package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IAddressDao;
import com.aifeng.mgr.admin.model.Address;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AddressDao extends BaseDao<Address> implements IAddressDao {

    public void test() {
        this.countAll();
    }

    public List<Map<String, Object>> getAddressFee(int page, int pageSize, String addr) {
        String sql = "select a.id ,a.area,a.city,a.province ,af.amount from address a " +
                " left join address_fee af on a.id = af.address_id " +
                "where a.active is true ";
        sql += addr == null ? "" : " and a.province in (" + addr + ") ";
        sql += "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(sql);
    }

    public int getAddressFeeCount(String addr) {
        String sql = "select count(a.id) as count from address a " +
                " left join address_fee af on a.id = af.address_id " +
                "where a.active is true ";
        sql += addr == null ? "" : " and a.province in (" + addr + ") ";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> getAddressFee(int page, int pageSize, String province, String city, String area, String addr) {
        String sql = "select a.id ,a.area,a.city,a.province ,af.amount from address a " +
                "left join address_fee af on a.id = af.address_id  " +
                "where a.active is true ";
        sql += addr == null ? "" : " and a.province in (" + addr + ") ";
        sql += " and a.province like '%" + province +
                "%' and a.city like '%" + city + "%' and  a.area like '%" + area + "%'" +
                " limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        System.out.println("sql: " + sql);
        return this.findBySql(sql);
    }

    public int getAddressFeeCount(String province, String city, String area, String addr) {
        String sql = "select count(a.id) as count from address a " +
                "left join address_fee af on a.id = af.address_id  " +
                "where a.active is true ";
        sql += addr == null ? "" : " and a.province in (" + addr + ") ";
        sql += " and a.province like '%" + province +
                "%' and a.city like '%" + city + "%' and  a.area like '%" + area + "%'";
        List<Map<String, Object>> list = this.findBySql(sql);
        return list.isEmpty() ? 0 : Integer.parseInt(list.get(0).get("count").toString());
    }

    public long getAddressId(String province, String city, String area) {
        Map<String, Object> params = new HashMap<>();
        params.put("province", province);
        params.put("city", city);
        params.put("area", area);
        String str = "select a from Address a where a.province =:province and a.city =:city and a.area =:area";
        Address address = this.findOneByHql(str, params);
        return address == null ? 0 : address.getId();
    }
}