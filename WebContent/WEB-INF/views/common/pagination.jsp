<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!-- 分页条 -->
 <ul class="pagination pull-right" style="margin: 0">
			<li ${pageResult.currentPage == 1 ? "class = 'disabled '" : ""}>
				<a href="javascript:goPage(1);">首页</a>
			</li>
			<li ${pageResult.currentPage == 1 ? "class = 'disabled '" : ""}>
				<a href="javascript:goPage(${pageResult.prePage});">上一页</a>
			</li>
			<c:forEach begin="${pageResult.beginIndex}" end="${pageResult.endIndex}" var="pageNumber">
				<li ${pageResult.currentPage == pageNumber ? "class = 'active '" : ""}>
					<a href="javascript:goPage(${pageNumber});">${pageNumber}</a>
				</li>
			</c:forEach>
			<li ${pageResult.currentPage == pageResult.totalPage ? "class = 'disabled '" : ""}>
				<a href="javascript:goPage(${pageResult.nextPage});">下一页</a>
			</li>
			<li ${pageResult.currentPage == pageResult.totalPage ? "class = 'disabled '" : ""}>
				<a href="javascript:goPage(${pageResult.totalPage});">末页</a>
			</li>
			<li>
				<a>共${pageResult.totalCount}条数据 </a>
			</li>
			<li>
				<a>当前第${pageResult.currentPage}/${pageResult.totalPage}页</a>
			</li>
			<li>
				<a style="height: 34px;">每页显示
					<select id = "pageSizeItem" onchange ="goPage(1)">
						<c:forEach items="${pageResult.pageSizeItems}" var="pageSizeItem">
							<option ${pageSizeItem == pageResult.pageSize ? "selected='selected'" : ""}>${pageSizeItem}</option>
						</c:forEach>
					</select>
					条
				</a>
			</li>
		</ul>
		
  <script type="text/javascript">
  	function goPage(pageNo){
  		//将翻页的页码存储到高级表单中
  		document.getElementById("currentPage").value = pageNo;
  		//获取每页显示的条数,并存储到高级表单中
  		document.getElementById("pageSize").value = document.getElementById("pageSizeItem").value;
  		//提交高级查询表单
  		document.forms["searchForm"].submit();
  	}
  </script>