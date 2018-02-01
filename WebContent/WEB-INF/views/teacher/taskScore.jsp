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
  <title>任务打分</title>
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
  <script src="${pageContext.request.contextPath}/js/teacher/taskScore.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
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
        <div class="container-fluid" style="border: 1px solid gainsboro;margin-bottom: 5px;height: 50px">
          <div class="row" style="padding-top: 8px">
            <div class="col-sm-10" style="padding-left: 0">
              <form class="form-inline" name="searchForm" name="searchForm" action="${pageContext.request.contextPath}/task/taskList" method="post"><!--当屏幕小于768时，变为两行-->
              <!-- ---------------携带分页信息隐藏域 --------------------->
<%--               	<input type="hidden" name ="currentPage" value="1" id="currentPage">
              	<input type="hidden" name ="pageSize" value="${pageResult.pageSize}" id="pageSize"> --%>
              <!-- ---------------携带分页信息隐藏域 --------------------->
              	<input type="hidden" name ="teacherId" value="${loginUser.id}" id="teacherId"><!--教师ID  -->
<!--               	<input type="hidden" name ="courseId" value="" id="courseId">课程ID 
              	<input type="hidden" name ="studentId" value="" id="studentId">学生ID  -->
              	
              	<div class="form-group col-sm-4">
                  <div class="col-sm-6 control-label" style="text-align: center;padding: 6px 0px;">
                    <label>选择课程：</label>
                  </div>
                  <div class="col-sm-6" style="padding: 0;margin-left: -18px;">
                  	<select name="courseId" class="form-control" id="courseSelect">
                  		<option value="-1">------请选择------</option>
                  		<c:forEach items="${courseList}" var="course">
                  			<option ${course.id == courseId ? "selected='selected'" : ""} value="${course.id}">
                  				${course.courseName}
                  			</option>
                  		</c:forEach>
                  	</select>
                  </div>
                </div>	
              	
                <div class="form-group col-sm-4">
	              <div class="col-sm-6 control-label" style="text-align: center;padding: 6px 0px;">
                    <label>选择班级：</label>
                  </div>
                  <div class="col-sm-6" style="padding: 0">
                  	<select name="classesId" class="form-control" id="classesSelect">
                  		<option value="-1">------请选择------</option>
                  		<c:forEach items="${classesList}" var="classes">
                  			<option ${classes.id == classesId ? "selected='selected'" : ""} value="${classes.id}">
                  				${classes.classesNumber}
                  			</option>
                  		</c:forEach>
                  	</select>
                  </div>
                </div>
                
                 <div class="form-group col-sm-4">
                  <div class="col-sm-6 control-label" style="text-align: center;padding: 6px 0px;">
                    <label>选择学生：</label>
                  </div>
                  <div class="col-sm-6" style="padding: 0">
                  	<select name="studentId" class="form-control" id="studentSelect">
                  		<option>------请选择------</option>
                  		<c:forEach items="${studentList}" var="student">
                  			<option ${student.id == studentId ? "selected='selected'" : ""} value="${student.id}">
                  				${student.studentName}
                  			</option>
                  		</c:forEach>
                  	</select>
                  </div>
                </div>
                
			

              </form>
            </div>
          </div>
        </div>
        <!--高级查询结束-->
        <!--数据展示部分开始-->
        <h5 style="font-size: 14px;font-weight: 900;margin: 5px 0">课程任务列表 :</h5>
       	 <div style="height: 375px;margin-bottom:5px;overflow-y:auto">
        	 <table class="table table-striped table-hover text-center table-bordered">
		          <thead>
		          <tr>
		           <!--  <th class="text-center">序号</th> -->
		            <th class="text-center">任务名称</th>
		            <th class="text-center">完成状态</th>
		            <th class="text-center">任务类型</th>
		            <th class="text-center">成绩</th>
		            <th class="text-center">操作</th>
		          </tr>
		          </thead>
		          <tbody>
		          <c:forEach items="${taskList}" var="task" varStatus="c">
			          <tr>
			            <td>${task.taskName}</td>
						<c:if test="${task.completeStatus==0}">
							<td>未完成</td>
						</c:if>
						<c:if test="${task.completeStatus==1}">
							<td>已完成</td>
						</c:if>
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
			            <c:if test="${task.mark.score==0}">
			            	<td></td>
			            </c:if>
			            <c:if test="${task.mark.score!=0}">
			            	<td>${task.mark.score}</td>
			            </c:if>
			            <td>
			              <div class="btn-group">
				             <c:if test="${task.completeStatus==1}">
			                	<a target="_blank" href="${pageContext.request.contextPath}/report/getReportByTaskId?taskId=${task.id}&studentId=${studentId}">查看报告</a>
							</c:if>
			                	<a href="javascript:setScoreByTaskId(${task.id},'${studentId}')" style="margin-left: 10px">评分</a>
			              </div>
			            </td>
			          </tr>
		          </c:forEach>
		          </tbody>
		        </table>
        	</div>
        <!--数据展示部分结束-->
        <!--分页信息条开始-->
    <%--     <c:if test="${pageResult.totalCount!=0}">
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

</body>
<!-- 任务详情的表单 -->
<div id="resourceDiv" style="display:none;overflow: hidden;padding-top: 30px">
	<!--水平表单-->
	<form class="form-horizontal" action="${pageContext.request.contextPath}/resource/resourceUpload" method="post" name="updateForm" enctype="multipart/form-data">

		<!-- ---------------携带分页信息隐藏域 --------------------->
		<input type="hidden" name="currentPage" value="${pageResult.currentPage}" id="currentPage">
		<input type="hidden" name="pageSize" value="${pageResult.pageSize}" id="pageSize">
		<!-- ---------------携带分页信息隐藏域 --------------------->
		<!-- 隐藏域 ,隐藏课程的类型:"我的课程或者其他课程" -->
        <input type="hidden" name="courseType" value="${qo.courseType}" id="courseType">
		<!-- 隐藏域存放id -->
		<input type="hidden" name="id" value="-1" id="updateId">
		<!-- 隐藏域存放taskId -->
		<input type="hidden" name="taskId" value="${qo.taskId}" id="taskId">
	
		<!--当屏幕小于768时，变为两行-->
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>资源编号：</label>
			</div>
			<div class="col-sm-3">
				<input type="text" name="resourceNumber" class="form-control" value="" id="resourceNumber">
			</div>
			<div class="col-sm-2 control-label">
				<label>资源名称：</label>
			</div>
			<div class="col-sm-3">
				<input type="text" name="resourceName"  class="form-control" value="" id="resourceName" >
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>上传文件：</label>
			</div>
			<div class="col-sm-5">
				<input type="text" name="fileName" id="fileName" value="" class="form-control" readonly="readonly">
				<input type="file" name="uploadFile" value="" id="uploadFile" style="visibility: hidden;" onchange="fileSelected();">
			</div>
			<div class="col-sm-3">
				<input id="btn_upload" type="button" class="btn btn-default" onclick="uploadFile.click()" value="选择文件"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>简介：</label>
			</div>
			<div class="col-sm-7">
				<textarea  name="description"  class="form-control"  id="description" rows="8"></textarea>
			</div>
		</div>
	</form>
</div>
</html>
