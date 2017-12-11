package cn.bytd.queryPage.page;

import java.util.Arrays;
import java.util.List;


import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 说明:分页的结果集对象,封装了所有的分页信息
 * @author yangying
 * @version 1.0
 * @date 2017-10-24 下午9:04:59
 *
 */

public class PageResult {
	//在构造函数中传入
	private List listData;//当前页的结果数据集,通过sql查询出来的
	private Integer totalCount;//结果总数,通过sql查询出来的

	//通过用户传入
	private Integer currentPage = 1;//当前页,默认为1
	private Integer pageSize = 7;//每页最多显示多少条数据
	//在构造函数中计算出来
	private Integer beginPage = 1;//首页
	private Integer prePage;//上一页 
	private Integer nextPage;//下一页
	private Integer totalPage;//末页/总页数
	
	//页码的开始索引和结束索引,在构造函数中设置值,用于实现 :首页 上页 [1] 2 3 4 5 6 7 8 9 10 下页 末页中间的索引页码
	private Integer beginIndex;
	private Integer endIndex;
	
	//每页显示条数的List
	private List pageSizeItems = Arrays.asList(5,7);
	
	public PageResult(){};

	public PageResult(List listData, Integer totalCount, Integer currentPage,
			Integer pageSize) {
		super();
		this.listData = listData;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		//-----------------------------------------------------------------------

		this.totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
		this.prePage = this.currentPage - 1 > 0 ? this.currentPage - 1 : 1;
		this.nextPage = this.currentPage + 1 <= this.totalPage ? this.currentPage + 1 : this.totalPage;
		//设置页码的开始索引和结束索引
		PageIndex pageIndex = PageIndex.getPageIndex(5, currentPage, totalPage);
		this.beginIndex = pageIndex.getBeginIndex();
		this.endIndex = pageIndex.getEndIndex();
	}

	public List getListData() {
		return listData;
	}

	public void setListData(List listData) {
		this.listData = listData;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(Integer beginPage) {
		this.beginPage = beginPage;
	}

	public Integer getPrePage() {
		return prePage;
	}

	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

	public List getPageSizeItems() {
		return pageSizeItems;
	}

	public void setPageSizeItems(List pageSizeItems) {
		this.pageSizeItems = pageSizeItems;
	}
	
	
}
