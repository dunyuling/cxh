package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.ProblemDao;
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
    public void save(String title, String description, int customerService_id, long agent_id) {
        Problem problem = new Problem();
        problem.setTitle(title);
        problem.setDescription(description);
        problem.setCustomerService_id(customerService_id);
        problem.setAgent_id(agent_id);
        problemDao.insert(problem);
    }

    @Transactional
    public void edit(long id, String title, String description) {
        Problem problem = findById(id);
        problem.setTitle(title);
        problem.setDescription(description);
        problemDao.insert(problem);
    }

    @Transactional
    public void solve(long id, String solution) {
        Problem problem = findById(id);
        problem.setSolve(true);
        problem.setSolution(solution);
        problemDao.insert(problem);
    }

    @Transactional
    public void delProblem(long id) {
        Problem problem = problemDao.findById(id);
        problemDao.delete(problem);
    }

    @Transactional
    public List<Map<String, Object>> getProblems(int page, int size) {
        return problemDao.getProblem(page, size);
    }

    @Transactional
    public int getCount() {
        return problemDao.countAll();
    }


    @Transactional
    public List<Map<String, Object>> queryProblems(int page, int size, String agent_name, String cs_name) {
        return problemDao.queryProblem(page, size, agent_name, cs_name);
    }

    @Transactional
    public int queryCount(String agent_name, String cs_name) {
        return problemDao.queryProblemsCount(agent_name,cs_name);
    }


    @Transactional
    public List<Map<String, Object>> getCustomerServiceProblems(int id, int page, int size) {
        return problemDao.getCustomerServiceProblems(id, page, size);
    }

    @Transactional
    public int getCustomerServiceCount(int id) {
        return problemDao.getCustomerServiceProblemCount(id);
    }

    @Transactional
    public List<Map<String, Object>> getAgentProblems(long id, int page, int size) {
        return problemDao.getAgentProblems(id, page, size);
    }

    @Transactional
    public int getAgentCount(long id) {
        return problemDao.getAgentProblemCount(id);
    }

    @Transactional
    public Map<String, Object> getSingleProblem(long id) {
        return problemDao.getSingleProblem(id);
    }


}