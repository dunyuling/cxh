package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.AdminDao;
import com.aifeng.mgr.admin.model.Admin;
import com.aifeng.mgr.admin.model.Role;
import com.aifeng.mgr.admin.service.IAdminService;
import com.aifeng.util.Md5;
import com.aifeng.util.Pager;
import com.aifeng.util.PropUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("adminService")
public class AdminService extends BaseService<Admin> implements IAdminService {

    private final AdminDao adminDao;
    private final RoleService roleService;
    private final UserRoleService userRoleService;

    @Autowired
    public AdminService(AdminDao adminDao, RoleService roleService, UserRoleService userRoleService) {
        this.adminDao = adminDao;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    @Override
    public Admin findByAccount(String account) {
        // TODO Auto-generated method stub
        List<Admin> list = (List<Admin>) this.findByHql("from Admin where account = '" + account + "'", null);

        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Transactional(readOnly = false)
    public List<Map<String, Object>> getPermissions(String userCode) {
        StringBuffer sb = new StringBuffer(
                "SELECT tmm.display, tmur.role_code,tmr.name AS role_name, tmm.id AS menu_id, tmm.name AS menu_name, tma.id as user_id,action_val, menu_code, pid, url, weight,icon FROM tb_mgr_user_role tmur left join tb_mgr_admin tma on (tma.code = tmur.user_code)");
        sb.append(" LEFT JOIN  tb_mgr_role tmr ON (tmur.`role_code` = tmr.code) ");
        sb.append(" RIGHT JOIN tb_mgr_role_menu tmrm ON (tmur.`role_code` = tmrm.`role_code`)  ");
        sb.append(" LEFT JOIN tb_mgr_menu tmm ON(tmm.code = tmrm.`menu_code`) ");
        sb.append(" WHERE tmur.user_code = '" + userCode + "'");
        sb.append(" ORDER BY tmm.code, weight ");

        System.out.println("---------- sb: " + sb.toString());
        return (List<Map<String, Object>>) this.findBySql(sb.toString());
    }

    @Override
    @Transactional
    public Pager getList(Pager page) {
        // TODO Auto-generated method stub
        return this.findPageByHql("from Admin ", page, null);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean initPwd(int id) {
        // TODO Auto-generated method stub
        Admin admin = this.findById(id);
        if (admin != null) {
            admin.setPwd(Md5.getMd5(PropUtil.getString("user.initPwd") + admin.getAccount()));
            this.update(admin);
            return true;
        }
        return false;
    }

    @Transactional
    public List<Map<String, Object>> getRoleList(int userId) {
        StringBuffer sb = new StringBuffer(
                " SELECT tmr.id AS role_id, tmr.code AS role_code, tmr.name AS role_name,  ");
        sb.append(
                " (SELECT IFNULL(count(tmur.id), 0) FROM tb_mgr_user_role tmur LEFT JOIN tb_mgr_admin tma " +
                        "ON tma.code = tmur.user_code WHERE tma.id = " + userId);
        sb.append(" AND tmr.code = tmur.role_code) AS 'set' ");
        sb.append(" FROM tb_mgr_role tmr  ");
        return (List<Map<String, Object>>) this.findBySql(sb.toString());

    }


    @Transactional
    public List<Map<String, Object>> getPagerCustomerService(int page, int pageSize) {
        return adminDao.getCustomerService(page, pageSize);
    }

    @Transactional
    public long getTotal() {
        return adminDao.countAll();
    }

    @Transactional
    public Map<String, Object> getSingleCustomerService(long id) {
        return adminDao.getSingleCustomerService(id);
    }

    transient boolean provincesUsed = false;

    @Transactional
    public boolean addCustomerService(String name, String pwd, String province, String phone, int sex) {
        synchronized (lock) {
            getUsedProvinces().forEach(map -> {
                Object province1 = map.get("province");
                if (province1 != null && province1.toString().equals(province))
                    provincesUsed = true;
            });

            if (!provincesUsed) {
                String code = generateCode();

                Admin admin = new Admin();
                admin.setPwd(Md5.getMd5(pwd));
                admin.setName(name);
                admin.setAddr(province);
                admin.setCode(code);
                admin.setPhone(phone);
                admin.setAccount("cs" + code);
                admin.setSex(sex);
                adminDao.insert(admin);
                provincesUsed = false;

                Role role = roleService.getRoleByName("客服");
                userRoleService.save(code, role.getCode());
                return true;
            } else {
                return false;
            }
        }
    }

    private final Object lock = new Object();

    @Transactional
    public boolean editCustomerService(int id, String name, String pwd, String province, String phone, int sex) {
        synchronized (lock) {
            getUsedProvinces().forEach(map -> {
                Object province1 = map.get("province");
                if (province1 != null && province1.toString().equals(province))
                    provincesUsed = true;
            });

            if (!provincesUsed) {
                Admin admin = findById(id);
                admin.setName(name);
                if (!pwd.isEmpty()) {
                    admin.setPwd(Md5.getMd5(pwd));
                }
                admin.setAddr(province);
                admin.setPhone(phone);
                admin.setSex(sex);
                adminDao.update(admin);
                provincesUsed = false;
                return true;
            } else {
                return false;
            }
        }
    }

    @Transactional
    private String generateCode() {
        long total = getTotal() + 1;
        return total < 10 ? "00000" + total : "0000" + total;
    }

    @Transactional
    public void delCustomerService(long id) {
        adminDao.deleteByWhere(" and id = " + id);
    }

    @Transactional
    public List<Map<String, Object>> getUsedProvinces() {
        return adminDao.getUsedProvinces();
    }
/*
    SELECT tmur.role_code,tmr.name AS role_name, tmm.id AS menu_id, tmm.name AS menu_name, tma.id as user_id,action_val, menu_code, pid, url, weight,icon
		FROM tb_mgr_user_role tmur
		left join tb_mgr_admin tma on (tma.code = tmur.user_code)
		LEFT JOIN  tb_mgr_role tmr ON (tmur.`role_code` = tmr.code)
		RIGHT JOIN tb_mgr_role_menu tmrm ON (tmur.`role_code` = tmrm.`role_code`)
		LEFT JOIN tb_mgr_menu tmm ON(tmm.code = tmrm.`menu_code`)
		WHERE tmur.user_code = '001' ORDER BY tmm.code, weight
	*/
}
