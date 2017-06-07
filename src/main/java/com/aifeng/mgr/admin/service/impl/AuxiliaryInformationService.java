package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.AuxiliaryInformationDao;
import com.aifeng.mgr.admin.model.AuxiliaryInformation;
import com.aifeng.mgr.admin.service.IAuxiliaryInformationService;
import com.aifeng.util.Util;
import com.aifeng.ws.wx.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Created by pro on 17-4-26.
 */
@Service
public class AuxiliaryInformationService extends BaseService<AuxiliaryInformation> implements IAuxiliaryInformationService {

    private final
    AuxiliaryInformationDao auxiliaryInformationDao;
    private final RestTemplate restTemplate;


    @Autowired
    public AuxiliaryInformationService(AuxiliaryInformationDao auxiliaryInformationDao, RestTemplate restTemplate) {
        this.auxiliaryInformationDao = auxiliaryInformationDao;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public AuxiliaryInformation getOrMockFirst() {
        return auxiliaryInformationDao.getFirst();
    }

    @Transactional
    public void edit(String access_token, String CorpID, String secret, String sign, String template, String identifyPwdTemplate) {
        AuxiliaryInformation auxiliaryInformation = auxiliaryInformationDao.getFirst();
        auxiliaryInformation.config(access_token, CorpID, secret, sign, template, identifyPwdTemplate);
        auxiliaryInformationDao.update(auxiliaryInformation);
    }

    @Transactional
    public void updateAccessToken(AuxiliaryInformation ai, String access_token) {
        ai.setAccess_token(access_token);
        ai.setUpdateDate(new Date());
        this.auxiliaryInformationDao.insert(ai);
    }

    @Transactional
    public String getAccessToken() {
        AuxiliaryInformation ai = getOrMockFirst();
        String access_token = ai.getAccess_token();
        if (access_token.isEmpty() || Util.expire(System.currentTimeMillis(), ai.getUpdateDate().getTime())) {
            ResponseEntity<UserResponse> atResponse = restTemplate.getForEntity(Util.loadAccessTokenUrl(ai.getCorpID(), ai.getSecret()), UserResponse.class);
            System.out.println("===" + atResponse);
            access_token = atResponse.getBody().getAccess_token();
            updateAccessToken(ai, access_token);
        }
        return access_token;
    }
}