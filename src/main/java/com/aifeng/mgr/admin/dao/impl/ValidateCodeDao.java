package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IValidateCodeDao;
import com.aifeng.mgr.admin.model.ValidateCode;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ValidateCodeDao extends BaseDao<ValidateCode> implements IValidateCodeDao {


    public ValidateCode getByMobileAndCode(String mobile, String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", mobile);
        String sql = "from ValidateCode where mobile =:mobile and code =:code";
        return this.findOneByHql(sql, map);
    }
}
