package com.aifeng.mgr.sys.service.impl;

import com.aifeng.core.model.Dic;
import com.aifeng.util.Pager;
import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.sys.service.IDicMgrService;
import org.springframework.stereotype.Service;

@Service("dicMgrService")
public class DicMgrService extends BaseService<Dic> implements IDicMgrService{

	@Override
	public Pager getDicList(Pager page) {
		// TODO Auto-generated method stub
		return this.findPageByHql("from Dic", page, null);
	}

}
