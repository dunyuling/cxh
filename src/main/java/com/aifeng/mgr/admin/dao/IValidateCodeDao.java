package com.aifeng.mgr.admin.dao;

import com.aifeng.core.dao.IBaseDao;
import com.aifeng.mgr.admin.model.ValidateCode;

public interface IValidateCodeDao extends IBaseDao<ValidateCode> {
    public ValidateCode getByMobileAndCode(String mobile, String code) ;
}
