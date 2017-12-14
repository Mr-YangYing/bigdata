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
  <title>课程详情</title>
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
        <!--课程数据开始-->
        <div class="container-fluid" style="border: 1px solid gainsboro;margin-bottom: 10px;height: 150px">
        	<div class="row">
        		<div class="col-sm-6" >
        			<div class="col-sm-3" style="padding-left: 0;padding-right: 0">
        				<h5>课程描述:</h5>
        			</div>
        			<div class="col-sm-9" style="padding-left: 0;margin-top: 10px;margin-left:-60px;overflow: hidden;">
        				<p style=" text-indent:2em;">${course.description}</p>
        			</div>
        		</div>
        		<div class="col-sm-6">
        			<div class="col-sm-6">
        				<div style="padding: 10px 0px;">
        					主讲老师: ${course.teacherName}
        				</div>
        				<div style="padding: 10px 0px;">
        					开课时间: ${course.startDate}
        				</div>
        			</div>
        			<div class="col-sm-6">
        				<div style="padding: 10px 0px;">
        					课程名称: ${course.courseName}
        				</div>
        				<div style="padding: 10px 0px;">
        					节课时间: ${course.endDate}
        				</div>
        			</div>
        		</div>
        	</div>
        </div>
        <!--课程数据结束-->
        <!--任务数据展示部分开始-->
        	<h5 style="padding-left: 10px;font-size: 14px;font-weight: 900;">课程任务列表 :</h5>
        	<a class="btn btn-sm btn-info" style="margin: 0 0 10px 10px" href="javascript:addTask(${course.id})">添加任务</a>
        	<div style="height: 330px;overflow-y:auto"><!-- 为了能显示滚动条 -->
		        <table class="table table-striped table-hover text-center table-bordered">
		          <thead>
		          <tr>
		           <!--  <th class="text-center">序号</th> -->
		            <th class="text-center">任务名称</th>
		            <th class="text-center">完成难度</th>
		            <th class="text-center">任务类型</th>
		            <th class="text-center">操作</th>
		          </tr>
		          </thead>
		          <tbody>
		          <c:forEach items="${taskList}" var="task" varStatus="c">
			          <tr>
			          <%--   <td>${c.count}</td> --%>
			            <td>${task.taskName}</td>
			            <!--完成难度判断  -->
			            <c:choose>
			            	<c:when test="${task.difficulty==0}">
			            		<td>简单</td>
			            	</c:when>
			            	<c:when test="${task.difficulty==1}">
			            		<td>一般</td>
			            	</c:when>
			            	<c:when test="${task.difficulty==2}">
			            		<td>中等</td>
			            	</c:when>
			            	<c:otherwise>
			            		<td>难</td>
			            	</c:otherwise>
			            </c:choose>
			            <!-- 任务类型判断 -->
			            <c:choose>
			            	<c:when test="${task.taskType==0}">
			            		<td>实验</td>
			            	</c:when>
			            	<c:when test="${task.taskType==1}">
			            		<td>作业</td>
			            	</c:when>
			            	<c:otherwise>
			            		<td>学习</td>
			            	</c:otherwise>
			            </c:choose>
			            <td>
			              <div class="btn-group">
			              <c:if test="${task.publishTask==0}">
			                <a class="btn btn-info btn-sm" href="javascript:publishTaskById(${task.id},${course.id})" style="margin-right: 20px">发布</a>
			              </c:if>
			                <a class="btn btn-warning btn-sm" href="javascript:editTaskById(${task.id},${course.id})" style="margin-right: 20px">编辑</a>
			                <a class="btn btn-danger btn-sm" href="javascript:deleteTaskById(${task.id},${course.id})">删除</a>
			              </div>
			            </td>
			          </tr>
		          </c:forEach>
		          </tbody>
		        </table>
		       </div>
        <!--任务数据展示部分结束-->
        <!--分页信息条开始-->
<%--         <c:if test="${pageResult.totalCount!=0}">
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
  <script src="${pageContext.request.contextPath}/js/teacher/courseTaskEdit.js"></script>
</body>

<!-- 任务详情的表单 -->
<div id="taskDetailDiv" style="display:none;overflow: hidden;padding-top: 30px">
	<!--水平表单-->
	<form class="form-horizontal" action="${pageContext.request.contextPath}/task/updateTask" method="post" name="updateForm">

		<!-- ---------------携带分页信息隐藏域 --------------------->
		<input type="hidden" name="currentPage" value="${pageResult.currentPage}" id="currentPage">
		<input type="hidden" name="pageSize" value="${pageResult.pageSize}" id="pageSize">
		<!-- ---------------携带分页信息隐藏域 --------------------->

		<!-- 隐藏域存放id -->
		<input type="hidden" name="id" value="-1" id="updateId">
		<!-- 隐藏域存放courseId -->
		<input type="hidden" name="courseId" value="" >
	
		<!--当屏幕小于768时，变为两行-->
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>任务名称：</label>
			</div>
			<div class="col-sm-3">
				<input type="text" name="taskName" class="form-control" value="" id="taskName">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>任务类型：</label>
			</div>
			<div class="col-sm-7">
				<label class="radio-inline">
					<input type="radio" name="taskType"  value="0" />实验
				</label>
				<label class="radio-inline">
					<input type="radio" name="taskType"  value="1" />作业
				</label>
				<label class="radio-inline">
					<input type="radio" name="taskType"  value="2"/>学习
				</label>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>上传报告：</label>
			</div>
			<div class="col-sm-7">
				<label class="radio-inline">
					<input type="radio" name="uploadReport" value="0" >关闭
				</label>
				<label class="radio-inline">
					<input type="radio" name="uploadReport" value="1" >开启
				</label>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>难度：</label>
			</div>
			<div class="col-sm-7">
				<label class="radio-inline">
					<input type="radio" name="difficulty"  value="0" >简单
				</label>
				<label class="radio-inline">
					<input type="radio" name="difficulty"  value="1" >一般
				</label>
				<label class="radio-inline">
					<input type="radio" name="difficulty"  value="2" >中等
				</label>
				<label class="radio-inline">
					<input type="radio" name="difficulty"  value="3" >难
				</label>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>描述：</label>
			</div>
			<div class="col-sm-7">
				<textarea  name="description"  class="form-control"  id="description" rows="8"></textarea>
			</div>
		</div>
	</form>
</div>
</html>
