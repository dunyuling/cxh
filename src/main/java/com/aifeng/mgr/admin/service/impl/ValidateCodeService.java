package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.IValidateCodeDao;
import com.aifeng.mgr.admin.model.ValidateCode;
import com.aifeng.mgr.admin.service.IValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ValidateCodeService extends BaseService<ValidateCode> implements IValidateCodeService {

    @Autowired
    private IValidateCodeDao validateCodeDao;

    @Transactional
    public ValidateCode get(String mobile, String code) {
        return validateCodeDao.getByMobileAndCode(mobile, code);
    }



}
