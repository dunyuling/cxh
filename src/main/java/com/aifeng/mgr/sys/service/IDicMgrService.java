package com.aifeng.mgr.sys.service;

import com.aifeng.core.service.IBaseService;
import com.aifeng.util.Pager;
import com.aifeng.core.model.Dic;

public interface IDicMgrService extends IBaseService<Dic> {

	public Pager getDicList(Pager page);
	
}
