package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.model.Problem;
import com.aifeng.mgr.admin.service.IProblemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;

/**
 * Created by pro on 17-4-28.
 */
@Service
public class ProblemService extends BaseService<Problem> implements IProblemService {

    @Transactional
    public void save(String title,String description,long customerService_id) {
        Problem problem = new Problem();
        problem.setTitle(title);
        problem.setDescription(description);
        problem.setCustomerService_id(customerService_id);
    }
}