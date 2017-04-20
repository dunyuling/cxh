package com.aifeng.core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aifeng.core.dao.IDicDao;
import com.aifeng.core.model.Dic;
import org.springframework.stereotype.Repository;

@Repository
public class DicDao extends BaseDao<Dic> implements IDicDao {

	@Override
	public Dic findByCode(String code) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		return this.findOneByHql("from Dic where code =:code", params);
	}

	@Override
	public List<Dic> findByType(String type) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		return (List<Dic>) this.findByHql("from Dic where type =:type", params);
	}

	@Override
	public Dic findByTypeAndCode(String type, String code) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("type", type);
		return this.findOneByHql("from Dic where code =:code and type =:type", params);
	}
	
	
	
	
	

}
