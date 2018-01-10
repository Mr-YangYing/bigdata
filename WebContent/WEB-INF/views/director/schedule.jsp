<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <title>课程表列表</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
  <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/html5shiv/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/respond/respond.min.js"></script>
  <![endif]-->
  <script src="${pageContext.request.contextPath}/jquery/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
  <script src="${pageContext.request.contextPath}/js/layer.js"></script>
  <script src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/common.js"></script>
  <script src="${pageContext.request.contextPath}/js/director/schedule.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
</head>
<body style="font-family: Microsoft YaHei">
<!--页头部分开始-->
 <%@include file="/WEB-INF/views/common/header.jsp" %>
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
        <!--高级查询开始-->
        <div class="container-fluid" style="border: 1px solid gainsboro;margin-bottom: 10px;height: 50px">
          <div class="row" style="padding-top: 8px">
            <div class="col-sm-10" style="padding-left: 0">
              <form class="form-inline" action="${pageContext.request.contextPath}/course/list" method="post"><!--当屏幕小于768时，变为两行-->
              

                <div class="form-group col-sm-1" >
                  <input type="submit" class="btn btn-info" value="查询"/>
                </div>
              </form>
            </div>
            <div class="col-sm-2">
              <div class="btn-group pull-right">
                <a class="btn btn-info btn-sm" style="" href="javascript:configureSchedule()">课表配置</a>
              </div>
            </div>
          </div>
        </div>
        <!--高级查询结束-->
        <!--数据展示部分开始-->
        <h5 style="font-weight: bold;padding-left: 10px">课程表列表</h5>
        <div style="height: 200px;overflow-y:auto"><!-- 为了能显示滚动条 -->
	        <table class="table table-striped table-hover text-center table-bordered">
	          <thead>
	          <tr>
	            <th class="text-center">序号</th>
	          </tr>
	          </thead>
	          <tbody>
	          <c:forEach items="${pageResult.listData}" var="course" varStatus="c">
		          <tr>
		            <td>${c.count}</td>

		            <td>
		              <div class="btn-group">
		              <c:if test="${course.courseOpen==0}">
		                <a class="" href="javascript:openCourseById(${course.id})" >课程开启</a>
		              </c:if>
		              <c:if test="${course.courseOpen==1}">
		                <a class="" href="javascript:closeCourseById(${course.id})" >课程关闭</a>
		              </c:if>
		              </div>
		            </td>
		          </tr>
	          </c:forEach>
	          </tbody>
	        </table>
	      </div>
	      <br/>
	     <!--分页信息条开始-->
        <c:if test="${pageResult.totalCount!=0}">
	        <%@include file="/WEB-INF/views/common/pagination.jsp" %>
        </c:if>
        <!--数据展示部分结束-->
      </div>
      <!--主体部分结束-->
    </div>
  </div>
<!--中间部分结束-->
<!--页脚部分开始-->
<%@include file="/WEB-INF/views/common/footer.jsp" %>
<!--页脚部分结束-->
</body>

</html>
