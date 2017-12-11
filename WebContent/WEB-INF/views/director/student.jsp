<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <title>学生管理</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layer.css">
  <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/html5shiv/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/respond/respond.min.js"></script>
  <![endif]-->
  <script src="${pageContext.request.contextPath}/jquery/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
  <script src="${pageContext.request.contextPath}/js/layer.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/common.js"></script>
  <script src="${pageContext.request.contextPath}/js/director/student.js"></script>
</head>
<body style="font-family: Microsoft YaHei">
<!--页头部分开始-->
 <%@include file="/WEB-INF/views/common/director/header.jsp" %>
<!--页头部分结束-->
<hr style="margin-bottom: 4px;margin-top: 4px">
<!--中间部分开始-->
  <div class="main container-fluid" style="height: 80%;overflow:auto;">
    <div class="row">
      <!--导航条菜单开始-->
		<%@include file="/WEB-INF/views/common/director/menu.jsp"%>
      <!--导航条菜单结束-->
      <!--主体部分开始-->
      <div class="content col-md-10">
        <!--高级查询开始-->
        <div class="container-fluid" style="border: 1px solid gainsboro;margin-bottom: 10px;height: 50px">
          <div class="row" style="padding-top: 8px">
            <div class="col-sm-8">
              <form class="form-inline" action="${pageContext.request.contextPath}/student/list" method="post"><!--当屏幕小于768时，变为两行-->
              <!-- ---------------携带分页信息隐藏域 --------------------->
              	<input type="hidden" name ="currentPage" value="1" id="currentPage">
              	<input type="hidden" name ="pageSize" value="${pageResult.pageSize}" id="pageSize">
              <!-- ---------------携带分页信息隐藏域 --------------------->
              	
                <div class="form-group col-sm-4">
                  <div class="col-sm-5 control-label" style="text-align: center;padding: 6px 0px;">
                    <label>学号：</label>
                  </div>
                  <div class="col-sm-7" style="padding: 0">
                    <input type="text" placeholder="请输入学生学号" name="studentNumber" value ="${qo.studentNumber}" class="form-control" size="12">
                  </div>
                </div>

                <div class="form-group col-sm-4">
                  <div class="col-sm-5 control-label" style="text-align: center;padding: 6px 0px">
                    <label>姓名：</label>
                  </div>
                  <div class="col-sm-7" style="padding: 0">
                    <input type="text" placeholder="请输入学生姓名" name="studentName" value ="${qo.studentName}" class="form-control" size="12">
                  </div>
                </div>

                <div class="form-group col-sm-4" >
                  <input type="submit" class="btn btn-info" value="查询"/>
                </div>
              </form>
            </div>
            <div class="col-sm-4">
              <div class="btn-group pull-right">
                <a class="btn btn-info btn-sm" style="margin-right: 20px" href="javascript:batchUpload('student')">批量导入</a>
                <a class="btn btn-info btn-sm" style="margin-right: 20px" href="javascript:addStudent()">添加学生</a>
                <a class="btn btn-info btn-sm btn_batch_delete" style="margin-right: 20px">批量删除</a>
              </div>
            </div>
          </div>
        </div>
        <!--高级查询结束-->
        <!--数据展示部分开始-->
        <table class="table table-striped table-hover text-center table-bordered">
          <thead>
          <tr>
            <th class="text-center"><input type="checkbox" id="all"></th>
            <th class="text-center">序号</th>
            <th class="text-center">学生学号</th>
            <th class="text-center">学生姓名</th>
            <th class="text-center">学期</th>
            <th class="text-center">学院</th>
            <th class="text-center">专业</th>
           <!--  <th class="text-center">班级</th> -->
            <th class="text-center">操作</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${pageResult.listData}" var="student" varStatus="s">
	          <tr>
	            <td><input type="checkbox" class="currentCheckbox" data-eid="${student.id}"></td>
	            <td>${s.count}</td>
	            <td>${student.studentNumber}</td>
	            <td>${student.studentName}</td>
	            <td>${student.currentTerm}</td>
	            <td>${student.college}</td>
	            <td>${student.profession}</td>
	            <%-- <td>${student.classes}</td> --%>
	            <td>
	              <div class="btn-group">
	                <a class="btn btn-warning btn-sm" href="javascript:getStudentById(${student.id})" style="margin-right: 20px">修改</a>
	                <a class="btn btn-danger btn-sm" href="javascript:deleteById(${student.id})" >删除</a>
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
</body>

<!-- 修改学生的表单 -->
<div id="updateStudentDiv" style="display:none;overflow: hidden;padding-top: 60px">
	<!--水平表单-->
	<form class="form-horizontal" action="${pageContext.request.contextPath}/student/update" method="post" name="updateForm">

		<!-- ---------------携带分页信息隐藏域 --------------------->
		<input type="hidden" name="currentPage" value="${pageResult.currentPage}" id="currentPage">
		<input type="hidden" name="pageSize" value="${pageResult.pageSize}" id="pageSize">
		<!-- ---------------携带分页信息隐藏域 --------------------->

		<!-- 隐藏域存放id -->
		<input type="hidden" name="id" value="-1" id="updateId">
	
		<!--当屏幕小于768时，变为两行-->
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>学号：</label>
			</div>
			<div class="col-sm-7">
				<input type="text" name="studentNumber" class="form-control" value="" id="updateStudentNumber">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>姓名：</label>
			</div>
			<div class="col-sm-7">
				<input type="text" name="studentName"  class="form-control" value="" id="updateStudentName">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>学期：</label>
			</div>
			<div class="col-sm-7">
				<input type="text" name="currentTerm"  class="form-control" value="" id="updateCurrentTerm">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>学院：</label>
			</div>
			<div class="col-sm-7">
				<input type="text" name="college"  class="form-control" value="" id="updateCollege">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>专业：</label>
			</div>
			<div class="col-sm-7">
				<input type="text" name="profession"  class="form-control" value="" id="updateProfession">
			</div>
		</div>
	<!-- 	<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>班级：</label>
			</div>
			<div class="col-sm-7">
				<input type="text" name="classes"  class="form-control" value="" id="updateClasses">
			</div>
		</div> -->
	</form>
</div>
</html>