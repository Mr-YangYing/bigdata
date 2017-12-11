<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <title>登录页</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css">
  <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/html5shiv/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/respond/respond.min.js"></script>
  <![endif]-->
</head>
<body class="gray-bg">
	<!--start of login form-->
	<div class=" text-center loginscreen  animated fadeInDown">
		<div>
			<div class="header">
				<span>大数据教学管理系统登录页</span>
			</div>
			<div class="content">
				<div class="login-wrap">
					<div class="w">
						<div class="login-form">
							<div class="login-u">用户登录</div>
							
							<c:if test="${error != null}">
							<div class="alert alert-warning alert-dismissable">
                            <button aria-hidden="true" data-dismiss="alert" class="close" type="button">×</button>
                             	${error}
                        	</div>
							</c:if>
							<form action="/teacher/list" method="post">
								<input type="text" id="username" name="username" class="login-name" placeholder="用户名" type="text">
								<input type="password" id="password" name="password" class="login-word" placeholder="密码" type="password">
								<button type="submit" class="btn btn-success btn-block">登录</button>
							</form>
						</div>
					</div>
					<div class="login-banner">
						<div class="bg-w"></div>
					</div>
				</div>
			</div>
			<div class="foot">
				<span>版权所有：北京博宇通达科技有限公司</span>
			</div>
		</div>
	</div>
	<!--end of login form  -->

	<!-- 全局js -->
  <script src="${pageContext.request.contextPath}/jquery/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
</body>

</html>
