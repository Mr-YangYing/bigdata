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
  <title>任务管理</title>
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
 <%@include file="/WEB-INF/views/common/teacher/header.jsp" %>
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
              <form class="form-inline" action="${pageContext.request.contextPath}/task/courseList" method="post"><!--当屏幕小于768时，变为两行-->
              <!-- ---------------携带分页信息隐藏域 --------------------->
              	<input type="hidden" name ="currentPage" value="1" id="currentPage">
              	<input type="hidden" name ="pageSize" value="${pageResult.pageSize}" id="pageSize">
              <!-- ---------------携带分页信息隐藏域 --------------------->
              	
                <div class="form-group col-sm-3">
                  <div class="col-sm-6 control-label" style="text-align: center;padding: 6px 0px;">
                    <label>课程名称：</label>
                  </div>
                  <div class="col-sm-6" style="padding: 0">
                    <input type="text" placeholder="请输入课程名称" name="courseName" value ="${qo.courseName}" class="form-control" size="12">
                  </div>
                </div>

                <div class="form-group col-sm-3">
                  <div class="col-sm-5 control-label" style="text-align: center;padding: 6px 0px">
                    <label>任课老师：</label>
                  </div>
                  <div class="col-sm-4" style="padding: 0">
                    <input type="text" placeholder="请输入负责老师" name="teacherName" value ="${qo.teacherName}" class="form-control" size="12">
                  </div>
                </div>
                <div class="form-group col-sm-3" style="padding-left: 0">
                  <div class="col-sm-5 control-label" style="text-align: center;padding: 6px 0px;">
                    <label>开课时间：</label>
                  </div>
                  <div class="col-sm-6" style="padding: 0">
                    <input type="text"  name="startTime" value ="<fmt:formatDate value="${qo.startTime}" pattern="yyyy-MM-dd"/>" class="form-control" size="12" style="width: 120px;height: 34px;">
                  </div>
                </div>
                <div class="form-group col-sm-2" >
                  <div class="col-sm-6" style="padding: 0">
                    <input type="text" placeholder="关键字查询"  name="keywords" value="${qo.keywords}"  class="form-control" style="width: 120px;height: 34px;">
                  </div>
                </div>
				
                <div class="form-group col-sm-1" >
                  <input type="submit" class="btn btn-info" value="查询"/>
                </div>
              </form>
            </div>
          </div>
        </div>
        <!--高级查询结束-->
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
	          <c:forEach items="${pageResult.listData}" var="course" varStatus="c">
		          <tr>
		        <%--     <td>${c.count}</td> --%>
		            <td>${course.id}</td>
		            <td>${course.courseName}</td>
		            <td>${course.teacherName}</td>
		            <td>${fn:length(course.tasks)}</td>
		            <td>
		              <div class="btn-group">
		                <a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/task/courseTaskDetail?courseId=${course.id}" style="margin-right: 20px">查看</a>
		                <a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/task/courseTaskEdit?courseId=${course.id}" >编辑</a>
		              </div>
		            </td>
		          </tr>
	          </c:forEach>
	          </tbody>
	        </table>
        <!--数据展示部分结束-->
        <!--分页信息条开始-->
        <c:if test="${pageResult.totalCount!=0}">
	        <%@include file="/WEB-INF/views/common/pagination.jsp" %>
        </c:if>
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
