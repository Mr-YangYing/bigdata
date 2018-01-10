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
  <title>配置课程表</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layer.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/configureSchedule.css">
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
  
  <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">

.left {
	width: 120px;
	float: left;
}

.left table {
	background: #E0ECFF;
}

.left td {
	background: #eee;
}

.right {
	float: right;
	width: 570px;
}

.right table {
	background: #E0ECFF;
	width: 100%;
}

.right td {
	background: #fafafa;
	color: #444;
	text-align: center;
	padding: 2px;
}

.right td {
	background: #E0ECFF;
}

.right td.drop {
	background: #fafafa;
	width: 100px;
}

.right td.over {
	background: #FBEC88;
}

.item {
	text-align: center;
	border: 1px solid #499B33;
	background: #fafafa;
	color: #444;
	width: 100px;
	margin-bottom: 10px;
}

.assigned {
	border: 1px solid #BC2A4D;
}
</style>
</head>
<body>
	<h2>创建一个课程表</h2>
	<div class="demo-info" style="margin-bottom: 10px">
		<div class="demo-tip icon-tip">&nbsp;</div>
		<div>点击拖一个课程到课程表里.</div>
	</div>
	<div style="width: 700px;">
		<div class="left">
			<table>
				<tr>
					<td><div class="item">英语</div></td>
				</tr>
				<tr>
					<td><div class="item">科学</div></td>
				</tr>
				<tr>
					<td><div class="item">音乐</div></td>
				</tr>
				<tr>
					<td><div class="item">历史</div></td>
				</tr>
				<tr>
					<td><div class="item">计算机</div></td>
				</tr>
				<tr>
					<td><div class="item">数学</div></td>
				</tr>
				<tr>
					<td><div class="item">艺术</div></td>
				</tr>
				<tr>
					<td><div class="item">伦理学</div></td>
				</tr>
			</table>
		</div>
		<div class="right">
			<table>
				<tr>
					<td class="blank"></td>
					<td class="title">星期一</td>
					<td class="title">星期二</td>
					<td class="title">星期三</td>
					<td class="title">星期四</td>
					<td class="title">星期五</td>
				</tr>
				<tr>
					<td class="time">08:00</td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
				</tr>
				<tr>
					<td class="time">09:00</td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
				</tr>
				<tr>
					<td class="time">10:00</td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
				</tr>
				<tr>
					<td class="time">11:00</td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
				</tr>
				<tr>
					<td class="time">12:00</td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
				</tr>
				<tr>
					<td class="time">13:00</td>
					<td class="lunch" colspan="5">午餐时间</td>
				</tr>
				<tr>
					<td class="time">14:00</td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
				</tr>
				<tr>
					<td class="time">15:00</td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
				</tr>
				<tr>
					<td class="time">16:00</td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
					<td class="drop"></td>
				</tr>
			</table>
			<script type="text/javascript">
				$(function() {
					$('.left .item').draggable({
						revert : true,
						proxy : 'clone'
					});

					$('.right td.drop').droppable({
						onDragEnter : function() {
							$(this).addClass('over');
						},
						onDragLeave : function() {
							$(this).removeClass('over');
						},
						onDrop : function(e, source) {
							$(this).removeClass('over');
							if ($(source).hasClass('assigned')) {
								$(this).append(source);
							} else {
								var c = $(source).clone().addClass('assigned');
								$(this).empty().append(c);
								c.draggable({
									revert : true
								});
							}
						}
					});
				});
			</script>
			<!--主体部分结束-->
		</div>
	</div>
</body>

</html>
