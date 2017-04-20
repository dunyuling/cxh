package com.aifeng.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aifeng.core.model.Dic;
import com.aifeng.core.service.IDicService;
import org.springframework.stereotype.Service;

@Service("dicService")
public class DicService extends BaseService<Dic> implements IDicService {
	
	@Override
	public Dic getByTypeAndCode(String type, String code){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		params.put("code", code);
		List<Dic> list = (List<Dic>) this.findByHql("from Dic where type =:type and code =:code ", params);
		if(list != null && list.size() > 0 ){
			return list.get(0);
		}
		else{
			return null;
		}
	}
	
	
    @Override
    public List<Dic> getByType(String type) {
        //在查询之前，先进行缓存的查询，如果可以查到，则直接使用缓存
    	Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		return (List<Dic>) this.findByHql("from Dic where type =:type", params);
    }

}
