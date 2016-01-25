package hxl.insist.oa.domain.notpersistent;

import java.util.List;

/**
 * 查询数据库的结果
 * 
 * @author 韩兴隆
 *
 * @param <T>
 */
public class QueryResult<T> {
	/**
	 * 查询结果列表
	 */
	private List<T> resultlist;

	/**
	 * 一共查询的记录数
	 */
	private long totalrecord;

	public List<T> getResultlist() {
		return resultlist;
	}

	public void setResultlist(List<T> resultlist) {
		this.resultlist = resultlist;
	}

	public long getTotalrecord() {
		return totalrecord;
	}

	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}
}
