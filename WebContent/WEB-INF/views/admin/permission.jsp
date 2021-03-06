<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <title>权限管理</title>
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
  <script src="${pageContext.request.contextPath}/js/common/common.js"></script>
  <script src="${pageContext.request.contextPath}/js/admin/permission.js"></script>
  <script src="${pageContext.request.contextPath}/js/menu.js"></script>
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
            <div class="col-sm-7">
              <form class="form-inline" name="searchForm" action="${pageContext.request.contextPath}/permission/list" method="post"><!--当屏幕小于768时，变为两行-->
              <!-- ---------------携带分页信息隐藏域 --------------------->
              	<input type="hidden" name ="currentPage" value="1" id="currentPage">
              	<input type="hidden" name ="pageSize" value="${pageResult.pageSize}" id="pageSize">
              <!-- ---------------携带分页信息隐藏域 --------------------->
              	
                <div class="form-group col-sm-4">
                  <div class="col-sm-5 control-label" style="text-align: center;padding: 6px 0px;">
                    <label>关键字：</label>
                  </div>
                  <div class="col-sm-7" style="padding: 0">
                    <input type="text" placeholder="请输入关键字" name="code" value ="${qo.code}" class="form-control" size="12">
                  </div>
                </div>

                <div class="form-group col-sm-4">
                  <div class="col-sm-5 control-label" style="text-align: center;padding: 6px 0px;">
                    <label>名称：</label>
                  </div>
                  <div class="col-sm-7" style="padding: 0;margin-left: -6px;">
                    <input type="text" placeholder="请输入权限名称" name="name" value ="${qo.name}" class="form-control" size="12">
                  </div>
                </div>

                <div class="form-group col-sm-4" >
                  <input type="submit" class="btn btn-info" value="查询"/>
                </div>
              </form>
            </div>
            <div class="col-sm-5">
              <div class="btn-group pull-right">
                <a class="btn btn-info btn-sm" style="margin-right: 20px" href="javascript:addPermission()">添加权限</a>
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
            <th class="text-center">关键字</th>
            <th class="text-center">权限名称</th>
            <th class="text-center">访问路径</th>
<!--             <th class="text-center">是否生成菜单</th>
            <th class="text-center">优先级</th> -->
           <!--  <th class="text-center">父功能点</th> -->
            <th class="text-center">描述</th>
            <th class="text-center">操作</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${pageResult.listData}" var="permission" varStatus="p">
	          <tr>
	            <td><input type="checkbox" class="currentCheckbox" data-eid="${permission.id}"></td>
	            <td>${p.count}</td>
	            <td>${permission.code}</td>
	            <td>${permission.name}</td>
	            <td>${permission.page}</td>
	            <td>${permission.description}</td>
	            <td>
	              <div class="btn-group">
	                <a class="" href="javascript:getPermissionById('${permission.id}')" style="margin-right: 20px">修改</a>
	                <a class="" href="javascript:deleteById('${permission.id}')">删除</a>
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
<div id="updatePermissionDiv" style="display:none;overflow: hidden;padding-top: 60px">
	<!--水平表单-->
	<form class="form-horizontal" action="${pageContext.request.contextPath}/permission/update" method="post" name="updateForm">

		<!-- ---------------携带分页信息隐藏域 --------------------->
		<input type="hidden" name="currentPage" value="${pageResult.currentPage}" id="currentPage">
		<input type="hidden" name="pageSize" value="${pageResult.pageSize}" id="pageSize">
		<!-- ---------------携带分页信息隐藏域 --------------------->

		<!-- 隐藏域存放id -->
		<input type="hidden" name="id" value="-1" id="updateId">
	
		<!--当屏幕小于768时，变为两行-->
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>关键字：</label>
			</div>
			<div class="col-sm-7">
				<input type="text" name="code" class="form-control" value="" id="updateCode" required="required">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>名称：</label>
			</div>
			<div class="col-sm-7">
				<input type="text" name="name"  class="form-control" value="" id="updateName" required="required">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>访问路径：</label>
			</div>
			<div class="col-sm-7">
				<input type="text" name="page"  class="form-control" value="" id="updatePage">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>是否生成菜单：</label>
			</div>
			<div class="col-sm-7">
				<select name="generatemenu" class="form-control" id="updateGeneratemenu">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>优先级：</label>
			</div>
			<div class="col-sm-7">
				<input type="number" name="zindex"  class="form-control" value="" id="updateZindex">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>父功能点：</label>
			</div>
			<div class="col-sm-7">
				<select name="pid" class="form-control" id="permissionSelect" required="required">
					<option value="-1">------请选择------</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>描述：</label>
			</div>
			<div class="col-sm-7">
				<textarea  name="description" cols="10" rows="3"  class="form-control"  id="updateDescription"></textarea>
			</div>
		</div>
	</form>
</div>
</html>
