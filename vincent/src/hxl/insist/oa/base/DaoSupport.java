package hxl.insist.oa.base;

import java.util.LinkedHashMap;
import java.util.List;

import hxl.insist.oa.domain.notpersistent.QueryResult;

public interface DaoSupport<T> {
	
	/**
	 * 保存实体
	 * @param entity
	 */
	void save(T entity);
	
	/**
	 * 删除实体
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * 更新实体
	 * @param entity
	 */
	void update(T entity);
	
	/**
	 * 按ID查询
	 * @param id
	 * @return
	 */
	T getById(Long id);
	
	/**
	 * 按ID查询
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Long[] ids);
	
	/**
	 * 查询所有
	 * @return
	 */
	List<T> findAll();
	
	/**
	 * 根据分页和where条件查询数据
	 * 
	 * @param firstindex
	 *            开始查询的第一条索引
	 * @param maxresult
	 *            查询的记录数
	 * @param wherejpql
	 *            where语句
	 * @param queryParams
	 *            条件参数
	 */
	QueryResult<T> getScrollData(Class<T> entityClass, int firstindex, int maxresult, String wherehql, Object[] queryParams, LinkedHashMap<String, String> orderby);
	
}
