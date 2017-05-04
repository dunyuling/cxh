package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IProblemDao;
import com.aifeng.mgr.admin.model.Problem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-28.
 */
@Repository
public class ProblemDao extends BaseDao<Problem> implements IProblemDao {

    public List<Map<String, Object>> getProblems(int page, int pageSize) {
        String str = "select p.id, p.title, p.description, cs.name from problem p " +
                "left join customer_service cs on p.customerService_id = cs.id " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }

    public Map<String, Object> getSingleProblem(long id) {
        String str = "select p.id, p.title, p.description, cs.name from problem p " +
                "left join customer_service cs on p.customerService_id = cs.id " +
                "where p.id = " + id;
        return this.findBySql(str).get(0);
    }
}