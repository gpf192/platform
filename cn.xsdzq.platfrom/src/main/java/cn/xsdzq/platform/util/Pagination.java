package cn.xsdzq.platform.util;

/**
 * @author Jiarong Xu
 * @date Mar 21, 2013 2:09:57 PM
 * 
 */
public class Pagination {

	private static final int DEFAULT_START = 0;

	private static final int DEFAULT_PAGE_SIZE = 10;

	private static final int MAX_PAGE_SIZE = 1000;

	/**
	 * start item position
	 */
	private int start = DEFAULT_START;

	/**
	 * item number per page
	 */
	private int limit = DEFAULT_PAGE_SIZE;

	/**
	 * total items in all page
	 */
	private int totalItems;

	public Pagination() {

	}

	public Pagination(int start, int limit) {
		this.start = start;
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		if (limit > MAX_PAGE_SIZE) {
			this.limit = MAX_PAGE_SIZE;
		} else {
			this.limit = limit;
		}
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
}
