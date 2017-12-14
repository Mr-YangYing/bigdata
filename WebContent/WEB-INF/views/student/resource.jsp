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
  <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/html5shiv/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/respond/respond.min.js"></script>
  <![endif]-->
  <script src="${pageContext.request.contextPath}/jquery/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
  <script src="${pageContext.request.contextPath}/js/layer.js"></script>
  <script src="${pageContext.request.contextPath}/js/teacher/resource.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
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
       
        <!--高级查询结束-->
        <!--数据展示部分开始-->
        <h5 style="font-size: 14px;font-weight: 900;margin: 5px 0">任务资源列表 :</h5>
        <div style="height: 490px;margin-bottom:5px;overflow-y:auto">
        	<c:forEach items="${resourceList}" var="resource">
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
			        		<a class="btn btn-xs btn-info" style="margin-top: 26px" href="${pageContext.request.contextPath }/resource/resourceDownload?id=${resource.id}">下载</a>
		        		</div>
		        	</div>
		        </div>
        	</c:forEach>
        </div>
        <!--数据展示部分结束-->
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

</body>
</html>
