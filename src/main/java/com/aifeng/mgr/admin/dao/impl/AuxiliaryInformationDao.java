package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IAuxiliaryInformationDao;
import com.aifeng.mgr.admin.model.AuxiliaryInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuxiliaryInformationDao extends BaseDao<AuxiliaryInformation> implements IAuxiliaryInformationDao {

    public AuxiliaryInformation getFirst() {
        String sql = "from AuxiliaryInformation";
        List<AuxiliaryInformation> auxiliaryInformationList = (List<AuxiliaryInformation>) this.findByHql(sql,null);
        if(!auxiliaryInformationList.isEmpty()) {
            return auxiliaryInformationList.get(0);
        }
        return new AuxiliaryInformation();
    }
}
