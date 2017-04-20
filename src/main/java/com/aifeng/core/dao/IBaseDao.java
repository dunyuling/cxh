package com.aifeng.core.dao;

import java.util.List;
import java.util.Map;

import com.aifeng.util.Pager;

/**
 * 
 * @ClassName: IBaseDao 
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2016年8月20日 上午12:10:24 
 * @param <T>
 */
public interface IBaseDao<T> {

	/**
	 * 持久化对象到db
	 * @Title: insert 
	 * @Description: TODO
	 * @param t
	 * @return
	 * @return: T
	 */
	T insert(T t);

	/**
	 * 删除数据
	 * @Title: delete 
	 * @Description: TODO
	 * @param t
	 * @return
	 * @return: T
	 */
	T delete(T t);

	/**
	 * 更新对象并持久化到db
	 * @Title: update 
	 * @Description: TODO
	 * @param t
	 * @return
	 * @return: T
	 */
	T update(T t);

	/**
	 * 
	 * @Title: findById 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: T
	 */
	T findById(java.io.Serializable id);

	/**
	 * 根据主键返回一个对象，使用悲观锁的Lock该条数据，事物提交后释放
	 * @Title: findByIdForLock 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: T
	 */
	T findByIdForLock(java.io.Serializable id);

	/**
	 * 
	 * @Title: deleteByProperties 
	 * @Description: TODO
	 * @param params
	 * @return: void
	 */
	void deleteByProperties(Map<String, Object> params);

	/**
	 * 
	 * @Title: deleteByWhere 
	 * @Description: TODO
	 * @param where
	 * @return: void
	 */
	void deleteByWhere(String where);

	/**
	 * 根据sql返回一个对象
	 * @Title: findOne 
	 * @Description: TODO
	 * @param sql
	 * @return
	 * @return: T
	 */
	T findOne(String sql, Class<T> clzz);
	
	/**
	 * 分页返回所有数据 
	 * @Title: findAll 
	 * @Description: TODO
	 * @param page				Not null
	 * @return
	 * @return: List<T>
	 */
	List<T> findAll(Pager page);
	
	/**
	 * 
	 * @Title: findAll 
	 * @Description: TODO
	 * @param sql
	 * @param clazz
	 * @return
	 * @return: List<T>
	 */
	public List<T> findAll(String sql, Class<T> clazz);
	
	public <E> List<E> findAll(Pager page, Class<E> clazz);
	
//	/**
//	 * 
//	 * @Title: findAll 
//	 * @Description: TODO
//	 * @param sql
//	 * @return
//	 * @return: List<Map<String,Object>>
//	 */
//	public List<Map<String, Object>> findAll(String sql);

	/**
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	List<?> findByHql(String hql, Map<String, Object> params);
	
	Long countByHql(String hql, Map<String, Object> params);
	/**
	 * 
	 * @param hql
	 * @param page			分页对象
	 * @param params 		查询参数
	 * @return
	 */
	Pager findPageByHql(final String hql, final Pager page, final Map<String, Object> params);
	
	List<?> _findByHql(final String hql, final Pager page, final Map<String, Object> params);
	
	List<?> _findBySql(String sql, Pager page, Map<String, Object> params);
	
	public <E> E findBySql(String sql, Class<E> clazz);
	
	Long countBySql(String sql, Map<String, Object> params);

	int execute(String sql, String type);

	int execute(String sql, String type, Map<String, String> params);

	List<?> executeProc(final String sql, final Map<String, Object> params, final Class<?> clazz);

	/**
	 * 分页获取数据
	 * @Title: findPageBySql 
	 * @Description: TODO
	 * @param sql
	 * @param page
	 * @param params
	 * @return
	 * @return: Pagination
	 */
	Pager findPageBySql(final String sql, Pager page, Map<String, Object> params);

	/**
	 * 根据sql查询构造一个map,如果不存在返回null
	 * @Title: queryForMap 
	 * @Description: TODO
	 * @param sql
	 * @return
	 * @return: Map<String,Object>
	 */
	public Map<String,Object> queryForMap(String sql);
	
	/**
	 * 保存对象到db
	 * @Title: insert 
	 * @Description: TODO
	 * @param obj
	 * @param clazz
	 * @return
	 * @return: Object
	 */
	public Object insert(Object obj, Class<T> clazz);

	/**
	 * 更新对象到db
	 * @Title: update 
	 * @Description: TODO
	 * @param obj
	 * @param clazz
	 * @return
	 * @return: Object
	 */
	public Object update(Object obj, Class<T> clazz);

	/**
	 * 
	 * @Title: sqlUpdate 
	 * @Description: TODO
	 * @param sql
	 * @param args
	 * @return
	 * @return: int
	 */
	public int sqlUpdate(final String sql, Object... args);
	
	
	public List<Map<String, Object>> findBySql(String sql);
	
	public List<?> findBySql(final String sql, final Pager page, final Map<String, Object> params);
	
	public T findOneByHql(String hql, Map<String, Object> params);
	

	
}
