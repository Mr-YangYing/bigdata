<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <title>我的课程</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layer.css">
  <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/html5shiv/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/respond/respond.min.js"></script>
  <![endif]-->
  <style type="text/css">
  </style>
</head>
<body style="font-family: Microsoft YaHei">
<!--页头部分开始-->
 <%@include file="/WEB-INF/views/common/student/header.jsp" %>
<!--页头部分结束-->
<hr style="margin-bottom: 4px;margin-top: 4px">
<!--中间部分开始-->
  <div class="main container-fluid" style="height: 80%;overflow:auto;">
    <div class="row">
      <!--导航条菜单开始-->
		<%@include file="/WEB-INF/views/common/menu.jsp"%>
      <!--导航条菜单结束-->
      <!--主体部分开始-->
      <div class="content col-md-10">
        <h5 style="font-weight: bold;padding-left: 10px">我的课程</h5>
        <div style="height: 450px;overflow-y:auto"><!-- 为了能显示滚动条 -->
        <!--数据展示部分开始-->
	        <table class="table table-striped table-hover text-center table-bordered">
	          <thead>
	          <tr>
	           <!--  <th class="text-center">序号</th> -->
	            <th class="text-center">课程编号</th>
	            <th class="text-center">课程名称</th>
	            <th class="text-center">任课老师</th>
	            <th class="text-center">教学任务数</th>
	            <th class="text-center">操作</th>
	          </tr>
	          </thead>
	          <tbody>
	          <c:forEach items="${list}" var="course" varStatus="c">
		          <tr>
		        <%--     <td>${c.count}</td> --%>
		            <td>${course.id}</td>
		            <td>${course.courseName}</td>
		            <td>${course.teacherName}</td>
		            <td>${fn:length(course.tasks)}</td>
		            <td>
		              <div class="btn-group">
		                <a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/student/courseTaskDetail?courseId=${course.id}">查看</a>
		              </div>
		            </td>
		          </tr>
	          </c:forEach>
	          </tbody>
	        </table>
	     </div>
        <!--数据展示部分结束-->
        <!--分页信息条开始-->
      <%--   <c:if test="${pageResult.totalCount!=0}">
	        <%@include file="/WEB-INF/views/common/pagination.jsp" %>
        </c:if> --%>
		<!--分页信息条结束  -->
      </div>
      <!--主体部分结束-->
    </div>
  </div>
<!--中间部分结束-->
<!--页脚部分开始-->
<%@include file="/WEB-INF/views/common/footer.jsp" %>
<!--页脚部分结束-->
  <script src="${pageContext.request.contextPath}/jquery/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
  <script src="${pageContext.request.contextPath}/js/layer.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript">
  $(function () {
	    //处理日期控件
	    $("input[name='startTime']").addClass("Wdate").click(function () {
	        WdatePicker({
	            maxDate: new Date()
	        });
	    });
	    

	});
  </script>
</body>

</html>
