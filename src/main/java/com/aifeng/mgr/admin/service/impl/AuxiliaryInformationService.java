package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.AuxiliaryInformationDao;
import com.aifeng.mgr.admin.model.AuxiliaryInformation;
import com.aifeng.mgr.admin.service.IAuxiliaryInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by pro on 17-4-26.
 */
@Service
public class AuxiliaryInformationService extends BaseService<AuxiliaryInformation> implements IAuxiliaryInformationService {

    private final
    AuxiliaryInformationDao auxiliaryInformationDao;

    @Autowired
    public AuxiliaryInformationService(AuxiliaryInformationDao auxiliaryInformationDao) {
        this.auxiliaryInformationDao = auxiliaryInformationDao;
    }

    @Transactional
    public AuxiliaryInformation getOrMockFirst() {
        return auxiliaryInformationDao.getFirst();
    }

    @Transactional
    public void edit(String access_token, String CorpID, String secret) {
        AuxiliaryInformation auxiliaryInformation = auxiliaryInformationDao.getFirst();
        auxiliaryInformation.config(access_token, CorpID, secret);
        auxiliaryInformationDao.update(auxiliaryInformation);
    }

    @Transactional
    public void updateAccessToken(AuxiliaryInformation ai, String access_token) {
        ai.setAccess_token(access_token);
        ai.setUpdateDate(new Date());
        this.auxiliaryInformationDao.insert(ai);
    }
}