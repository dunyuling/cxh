package com.aifeng.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.aifeng.core.dao.IBaseDao;
import com.aifeng.util.Pager;
import org.apache.log4j.Logger;
import com.aifeng.util.StringUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

/**
 * 
 * @ClassName: BaseDao
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2016年8月20日 上午12:10:33
 * @param <T>
 */
public class BaseDao<T> implements IBaseDao<T> {

	Logger log = Logger.getLogger(BaseDao.class);

	private Class<T> clzz;

	@Autowired
	public HibernateTemplate hibernateTemplate;
	@Autowired
	public MyJdbcTemplate jdbcTemplate;

	public BaseDao() {
		// init clazz
		// 得到当前对象的所有实现接口类型
		Type types = this.getClass().getGenericSuperclass();
		// 将得到的type类型转化可参数化的类型
		if (types instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) types;
			// 得到泛型的类型的名字
			clzz = (Class<T>) type.getActualTypeArguments()[0];
		}
	}

	public Class<T> getClazz() {
		return this.clzz;
	}

	public T insert(T t) {
		hibernateTemplate.save(t);
//		System.out.println("insert ------------ " + t.getClass().getName());
		return t;
	}

	public T delete(T t) {
		hibernateTemplate.delete(t);
		return t;
	}

	public T update(T t) {
		hibernateTemplate.saveOrUpdate(t);
		return t;
	}

	public T findById(Serializable id) {
		return hibernateTemplate.get(clzz, id);
	}

	public T findByIdForLock(Serializable id) {
		return hibernateTemplate.get(clzz, id, LockMode.PESSIMISTIC_WRITE);
	}

	@Override
	public T findOne(String sql, Class<T> clazz) {
		// TODO Auto-generated method stub
		List<T> list = findAll(sql, clazz);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public T findOneByHql(String hql, Map<String, Object> params){
		
		List<?> list = this.findByHql(hql, params);
		if(list != null && list.size() > 0){
			return (T) list.get(0);
		}
		return null;
		
	}
	
	public List<T> findAll(Pager page) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clzz);
		if (page == null) {
			return (List<T>) hibernateTemplate.findByCriteria(criteria);
		} else {
			return (List<T>) hibernateTemplate.findByCriteria(criteria, page.getStart(), page.getPageSize());
		}
	}

	public <E> List<E> findAll(Pager page, Class<E> clazz) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		if (page == null) {
			return (List<E>) hibernateTemplate.findByCriteria(criteria);
		} else {
			return (List<E>) hibernateTemplate.findByCriteria(criteria, page.getStart(), page.getPageSize());
		}
	}

	public List<T> findAll(String sql, Class<T> clazz) {
		return this.jdbcTemplate.queryForList(sql, clazz);
	}

//	public List<Map<String, Object>> findAll(String sql) {
//		return this.jdbcTemplate.queryForList(sql);
//	}

	public void deleteByProperties(final Map<String, Object> params) {
		hibernateTemplate.execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				String hql = "delete from " + clzz.getSimpleName() + " where 1=1 ";
				for (String key : params.keySet()) {
					hql = hql + " and " + key + " = '" + params.get(key) + "'";
				}
				Query query = session.createQuery(hql);
				return query.executeUpdate();
			}
		});
	}

	@Override
	public void deleteByWhere(final String where) {
		hibernateTemplate.execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				String hql = "delete from " + clzz.getSimpleName() + " where 1 = 1 " + where;

				Query query = session.createQuery(hql);
				return query.executeUpdate();
			}
		});
	}

	public Long countAll() {
		Number val =  hibernateTemplate.execute(new HibernateCallback<Number>() {
			@Override
			public Number doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(clzz);
				criteria.setProjection(Projections.alias(Projections.count("id"), "cnt"));
				return (Number) criteria.uniqueResult();
			}

		});
		
		return val == null ? 0 : val.longValue();
	}

	@Override
	public List<?> _findByHql(final String hql, final Pager page, final Map<String, Object> params) {
		if(page != null){
			page.setTotal(countByHql("select count(*) " + hql, params));
		}
		return hibernateTemplate.execute(new HibernateCallback<List<?>>() {

			@Override
			public List<?> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				if (params != null)
					query.setProperties(params);

				if (page != null) {
					query.setFirstResult(page.getStart());
					query.setMaxResults(page.getPageSize());
				}
				return query.list();
			}

		});
	}
	

	@Override
	public List<?> findByHql(final String hql, final Map<String, Object> params) {
		// TODO Auto-generated method stub
		return _findByHql(hql, null, params);
	}

	public Pager findPageByHql(final String hql, final Pager page, final Map<String, Object> params) {

		List<?> results = _findByHql(hql, page, params);
		page.setResults(results);
		return page;
	}

	public Long countByHql(final String hql, final Map<String, Object> params) {
		Number val =  hibernateTemplate.execute(new HibernateCallback<Number>() {

			@Override
			public Number doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				if (params != null)
					query.setProperties(params);

				return (Number) query.uniqueResult();
			}

		});
		
		return val == null ? 0 : val.longValue();
	}

	public List<?> _findBySql(final String sql, final Pager page, final Map<String, Object> params) {
		if(page != null){
			page.setTotal(countAllBySql(sql, params));
		}

		return hibernateTemplate.execute(new HibernateCallback<List<?>>() {

			@Override
			public List<?> doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				if (params != null)
					query.setProperties(params);

				if (page != null) {
					query.setFirstResult(page.getStart());
					query.setMaxResults(page.getPageSize());
				}
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				return query.list();
			}

		});
	}
	
	@Override
	public List<?> findBySql(final String sql, final Pager page, final Map<String, Object> params) {
		if(page != null){
			page.setTotal(countAllBySql(sql, params));
		}

		return hibernateTemplate.execute(new HibernateCallback<List<?>>() {

			@Override
			public List<?> doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				if (params != null)
					query.setProperties(params);

				if (page != null) {
					query.setFirstResult(page.getStart());
					query.setMaxResults(page.getPageSize());
				}
				return query.list();
			}

		});
	}


	public Long countBySql(final String sql, final Map<String, Object> params) {
		Number val =   hibernateTemplate.execute(new HibernateCallback<Number>() {

			@Override
			public Number doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				if (params != null)
					query.setProperties(params);

				return  (Number) query.uniqueResult();
			}

		});
		
		return val == null ? 0 : val.longValue();
	}

	@Override
	public int execute(final String sql, final String type) {
		return hibernateTemplate.execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				if (StringUtil.isBlank(type) || type.equals("sql")) {
					SQLQuery query = session.createSQLQuery(sql);
					return query.executeUpdate();
				} else {
					Query query = session.createQuery(sql);
					return query.executeUpdate();
				}
			}
		});
	}

	@Override
	public int execute(final String sql, final String type, final Map<String, String> params) {
		return hibernateTemplate.execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				if (StringUtil.isBlank(type) || type.equals("sql")) {
					SQLQuery query = session.createSQLQuery(sql);
					if (params != null)
						query.setProperties(params);
					return query.executeUpdate();
				} else {
					Query query = session.createQuery(sql);
					if (params != null)
						query.setProperties(params);
					return query.executeUpdate();
				}
			}
		});
	}

	@Override
	public Pager findPageBySql(String sql, Pager page, Map<String, Object> params) {
		List<?> results = _findBySql(sql, page, params);
		page.setResults(results);
		return page;
	}

	/**
	 * 使用sql查询总数
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	private int countAllBySql(String sql, final Map<String, Object> params) {
		final String querySQL = getRowCountSql(sql);

		Number val = hibernateTemplate.execute(new HibernateCallback<Number>() {

			@Override
			public Number doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(querySQL);
				if (params != null)
					query.setProperties(params);
				return (Number) query.uniqueResult();
			}
		});

		return val == null ? 0 : val.intValue();
	}

	/**
	 * 使用查询列表sql生成查询总数sql
	 * 
	 * @param sql
	 * @return
	 */
	private static String getRowCountSql(String sql) {
		if (sql != null) {
			sql = "select count(1) from  (" + clearSql(sql) + ") aa";
		}
		return sql;
	}

	/**
	 * 清除掉排序,提高效率
	 * 
	 * @param sql
	 * @return
	 */
	private static String clearSql(String sql) {
		if (sql == null) {
			return null;
		}

		String sqlCopy = sql;

		int orderIndex = sqlCopy.toLowerCase().indexOf("order by");
		while (orderIndex > 0) {
			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append(sqlCopy.substring(0, orderIndex));
			int subIndex = sqlCopy.indexOf(")", orderIndex);

			if (subIndex > 0) {
				String substring = sqlCopy.substring(subIndex);
				if (substring.trim().length() > 1)
					sqlBuilder.append(substring);
			}

			sqlCopy = sqlBuilder.toString();
			orderIndex = sqlCopy.toLowerCase().indexOf("order by");
		}

		return sqlCopy;
	}

	@Override
	public Object insert(Object T, Class clazz) throws DataAccessException {
		if (T.getClass().equals(clazz))
			hibernateTemplate.save(T);
		return T;
	}

	public Object update(Object T, Class<T> clazz) throws DataAccessException {
		if (T.getClass().equals(clazz))
			hibernateTemplate.saveOrUpdate(T);
		return T;
	}

	@Override
	public List<?> executeProc(final String sql, final Map<String, Object> params, final Class<?> clazz) {
		return hibernateTemplate.execute(new HibernateCallback<List<?>>() {

			@Override
			public List<?> doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql).addEntity(clazz);
				if (params != null)
					query.setProperties(params);
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				return query.list();
			}
		});
	}

	public Map<String, Object> queryForMap(String sql) {
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}

	}

	@Override
	public int sqlUpdate(String sql, Object... args) {
		int update = jdbcTemplate.update(sql, args);
		return update;
	}

	@Override
	public <E> E findBySql(String sql, Class<E> clazz) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(sql, clazz);
	}

	@Override
	public List<Map<String, Object>> findBySql(String sql) {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.queryForList(sql);
	}
	
	

}
