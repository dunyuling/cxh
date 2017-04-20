package com.aifeng.core.dao;

import java.util.List;

import com.aifeng.core.model.Dic;

/**
 * 数据字典
 * @ClassName: IDicDao 
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2017年3月27日 下午4:18:52
 */
public interface IDicDao extends IBaseDao<Dic>{
	
	/**
	 * 根据code返回对应的数据字典
	 * @Title: findByCode 
	 * @Description: TODO
	 * @param code
	 * @return
	 * @return: Dic
	 */
	public Dic findByCode(String code);
	
	
	/**
	 * 根据类型返回数据字典列表
	 * @Title: findByType 
	 * @Description: TODO
	 * @param type
	 * @return
	 * @return: List<Dic>
	 */
	List<Dic> findByType(String type);
	
	/**
	 * 根据类型和code返回数据字典
	 * @Title: findByTypeAndCode 
	 * @Description: TODO
	 * @param type
	 * @param code
	 * @return
	 * @return: Dic
	 */
	public Dic findByTypeAndCode(String type, String code);
}
