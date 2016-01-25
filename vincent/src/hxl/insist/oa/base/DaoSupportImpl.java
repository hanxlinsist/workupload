package hxl.insist.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import hxl.insist.oa.domain.notpersistent.QueryResult;

@Transactional
@SuppressWarnings("unchecked")
public class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	private SessionFactory sessionFactory;

	private Class<T> clazz;

	public DaoSupportImpl() {
		// 获取当前new的对象的泛型的父类类型
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		// 获取第一个类型参数的真实类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	/**
	 * 获取当前可用的Session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	 
	public void save(T entity) {
		getSession().save(entity);
	}

	 
	public void delete(Long id) {
		Object obj = getById(id);
		if (obj != null) {
			getSession().delete(obj);
		}
	}

	 
	public void update(T entity) {
		getSession().update(entity);
	}

	 
	public T getById(Long id) {
		if (id != null) {
			return (T) getSession().get(clazz, id);
		}

		return null;
	}

	 
	public List<T> getByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		} else {
			return getSession().createQuery(//
					"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
					.setParameterList("ids", ids)//
					.list();
		}
	}

	 
	public List<T> findAll() {
		return getSession().createQuery("FROM " + clazz.getSimpleName()).list();
	}

	public QueryResult<T> getScrollData(Class<T> entityClass, int firstindex, int maxresult, String wherehql, Object[] queryParams, LinkedHashMap<String, String> orderby) {
		@SuppressWarnings("rawtypes")
		QueryResult qr = new QueryResult<T>();
		String entityname = entityClass.getSimpleName();
		
		Query query = getSession().createQuery("select o from " + entityname + " o " + ( wherehql == null || "".equals( wherehql.trim() ) ? "" : "where "+ wherehql ) + buildOrderby(orderby));
		setQueryParams(query, queryParams);
		query.setFirstResult(firstindex).setMaxResults(maxresult);  //设置分页条件
		qr.setResultlist(query.list());
		
		query = getSession().createQuery("select count(o) from " + entityname + " o " + ( wherehql == null || "".equals( wherehql.trim() ) ? "" : "where "+ wherehql ));
		setQueryParams(query, queryParams);
		qr.setTotalrecord((Long) query.uniqueResult());
		return qr;
	}

	/**
	 * 构建排序语句
	 */
	private String buildOrderby(LinkedHashMap<String, String> orderby) {
		StringBuffer orderbyql = new StringBuffer("");
		if(orderby != null && orderby.size() > 0){
			orderbyql.append(" order by ");
			for(String key : orderby.keySet())
				orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			orderbyql.deleteCharAt(orderbyql.length()-1);
		}
		return orderbyql.toString();
	}

	/**
	 * 为创建的HQL语句设置参数
	 */
	private void setQueryParams(Query query, Object[] queryParams) {
		if(queryParams != null && queryParams.length > 0)
			for(int i = 0; i < queryParams.length; i++)
				query.setParameter(i, queryParams[i]);
	}

}
