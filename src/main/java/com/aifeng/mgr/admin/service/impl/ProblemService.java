package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.ProblemDao;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.model.Problem;
import com.aifeng.mgr.admin.service.IProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-28.
 */
@Service
public class ProblemService extends BaseService<Problem> implements IProblemService {

    private final
    ProblemDao problemDao;

    @Autowired
    public ProblemService(ProblemDao problemDao) {
        this.problemDao = problemDao;
    }

    @Transactional
    public void save(String title, String description, long customerService_id) {
        Problem problem = new Problem();
        problem.setTitle(title);
        problem.setDescription(description);
        problem.setCustomerService_id(customerService_id);
        problemDao.insert(problem);
    }

    @Transactional
    public void edit(long id, String title, String description, long customerService_id) {
        Problem problem = findById(id);
        problem.setTitle(title);
        problem.setDescription(description);
        problem.setCustomerService_id(customerService_id);
        problemDao.insert(problem);
    }

    @Transactional
    public void delProblem(long id) {
        Problem problem = problemDao.findById(id);
        problemDao.delete(problem);
    }

    @Transactional
    public List<Map<String, Object>> getPagerProblems(int page, int size) {
        return problemDao.getProblems(page, size);
    }

    @Transactional
    public Map<String, Object> getSingleProblem(long id) {
        return problemDao.getSingleProblem(id);
    }

    @Transactional
    public long getTotal() {
        return problemDao.countAll();
    }

}