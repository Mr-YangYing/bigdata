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
  <title>资源列表</title>
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
  <script src="${pageContext.request.contextPath}/js/teacher/resource.js"></script>
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
              <form class="form-inline" name="searchForm" action="${pageContext.request.contextPath}/resource/resourceList" method="post"><!--当屏幕小于768时，变为两行-->
              <!-- ---------------携带分页信息隐藏域 --------------------->
              	<input type="hidden" name ="currentPage" value="1" id="currentPage">
              	<input type="hidden" name ="pageSize" value="${pageResult.pageSize}" id="pageSize">
              <!-- ---------------携带分页信息隐藏域 --------------------->
              <!-- 隐藏域 ,隐藏课程的类型:"我的课程或者其他课程" -->
              <input type="hidden" name="courseType" value="${qo.courseType}" id="courseType">
              <!-- 隐藏域 ,隐藏任务Id -->
              <input type="hidden" name="taskId" value="${qo.taskId}" id="taskId">
              	
                <div class="form-group col-sm-3" style="padding-top: 6px;">
	                <label class="radio-inline">
						<input type="radio" name="courseRadio" value="1"><span>我的课程</span>
					</label>
					<label class="radio-inline">
						<input type="radio" name="courseRadio" value="1" ><span>其他课程</span>
					</label>
                </div>
                
                 <div class="form-group col-sm-3">
                  <div class="col-sm-6 control-label" style="text-align: center;padding: 6px 0px;">
                    <label>选择课程：</label>
                  </div>
                  <div class="col-sm-6" style="padding: 0">
                  	<select name="courseName" class="form-control" id="courseSelect">
                  		<option>------请选择------</option>
                  	</select>
                  </div>
                </div>
                
				 <div class="form-group col-sm-5">
                  <div class="col-sm-6 control-label" style="text-align: center;padding: 6px 0px;">
                    <label style="margin-left: 35px;">选择任务：</label>
                  </div>
                  <div class="col-sm-6" style="padding: 0;margin-left: -18px;">
                  	<select name="taskId" class="form-control" id="taskSelect">
                  		<option>------请选择------</option>
                  	</select>
                  </div>
                </div>				

              </form>
            </div>
          </div>
        </div>
        <!--高级查询结束-->
        <!--数据展示部分开始-->
        <h5 style="font-size: 14px;font-weight: 900;margin: 5px 0">任务资源列表 :</h5>
        <c:if test="${qo.courseType=='我的课程'}">
        	<a class="btn btn-sm btn-info" style="margin-bottom: 5px" href="javascript:addResource(${qo.taskId})">资源上传</a>
        </c:if>
        <div style="height: 375px;margin-bottom:5px;overflow-y:auto">
        	<c:forEach items="${pageResult.listData}" var="resource">
		      <div class="container-fluid" style="border: 1px solid #2e6f9a;;margin-bottom: 10px;
		      box-shadow: 2px 2px 19px #e0e0e0;transition: background-color 0.25s ease-in;border-radius: 8px;opacity: 0.8;">
		        	<div class="row" style="margin-top: 5px">
		        		<div class="col-sm-9">
		        			<div class="col-sm-12">
		        				文件名称: <span style="color: gray;">${resource.resourceName}</span>
		        			</div>
		        			<div class="col-sm-12">
		        				简介: <p style="height: 35px;text-indent:2em;color: gray;">${resource.description}</p>
		        			</div>
		        			<div class="col-sm-12" style="margin-bottom: 5px">
		        				<div class="col-sm-4" style="padding-left: 0">
		        					上传者: <span style="color: gray;">${resource.uploader}</span>
		        				</div>
		        				<div class="col-sm-6">
		        					上传时间: <span style="color: gray;">${resource.uploadDate}</span>
		        				</div>
		        			</div>
		        		</div>
		        		<div class="col-sm-1 text-center" style="margin-top: 16px;">
		        			<c:if test="${qo.courseType=='我的课程'}">
			        			<a class="" style="margin-bottom: 5px" href="javascript:editResource(${resource.id},${qo.taskId})">编辑</a>
			        			<a class="" style="margin-bottom: 5px" href="${pageContext.request.contextPath }/resource/resourceDownload?id=${resource.id}">下载</a>
			        			<a class="" href="javascript:deleteResource(${resource.id})">删除</a>
		        			</c:if>
		        			<c:if test="${qo.courseType=='其他课程'}">
			        			<a class="" style="margin-top: 26px" href="${pageContext.request.contextPath }/resource/resourceDownload?id=${resource.id}">下载</a>
		        			</c:if>
		        		</div>
		        	</div>
		        </div>
        	</c:forEach>
        </div>
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
