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
  <title>cvs导入</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/import_excel.css">
  <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/html5shiv/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/respond/respond.min.js"></script>
  <![endif]-->
  <script src="${pageContext.request.contextPath}/jquery/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
  <script src="${pageContext.request.contextPath}/js/layer.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/import_excel.js"></script>
</head>
<body>
<body>
	<div class="container">
		<div>
			<ul id="config_nav_tab" class="nav nav-tabs">
				<li id="choose_file" class="active">
					<a href="#config_input" data-toggle="tab" style="pointer-events:none;cursor:not-allowed">1、选择文件</a>
				</li>
				<li id="choose_field">
					<a href="#config_args" data-toggle="tab" style="pointer-events:none;cursor:not-allowed">2、选择字段</a>
				</li>
			</ul>
		</div>
		<div id="config_content" class="tab-content">
			<div style="height:15px;"></div>
			<div class="tab-pane active" id="config_input">
					<div style="height:330px;">
				    	<form  enctype="multipart/form-data" id="uploadForm">
				       	 	<input style="width:90%;float:left;" class="form-control" id="excel" type="text" value=""/>
				            <input id="btn_upload" type="button" class="btn btn-default" onclick="fileToUpload.click()" value="上传"/>
							<input id="fileToUpload" name="uploadFile" style="visibility: hidden; position: absolute;display:inline-block;"  type="file" onchange="fileSelected();" />
				    	</form>
				    </div>
					<div style="height:25px;"></div>
					<div style="height: 5px; border-top: 1px solid #cccccc; margin: 0px 0px 0px 0px;"></div>
					<div style="float:left;margin-left:0px;">
						<a type="button" class="btn btn-default" onclick="btn_cancel()">取消</a>
					</div>
					<div style="float:right;margin-right:0px;">
						<a id = "btn_import_csv" type="button" class="btn btn-default" onclick="next_step(1,'${domainName}')">下一步</a>
					</div>
			</div>
			<div class="tab-pane" id="config_args">
				<div id="div_load_fields" style="height:330px;overflow:auto;">
					<div id="div_excel_fields" style="height:300px;">
						<table class='table table-bordered' id='tbl_excel_fields'>
							<thead id="thead_excel_fields">
								<tr>
									<!-- <td style='width: 5%'><input type='checkbox' id='check_all' /></td> -->
									<th>序号</th>
									<th>文本字段</th>
									<th>数据库字段</th>
									<!-- <th>字段类型</th> -->
								</tr>
							</thead>
							<tbody id="tbody_excel_fields">
								
							</tbody>
						</table>
					</div>
					<!-- <div id="div_skip_first_row"><b><input id="is_skip" type="checkbox"/>&nbsp;首行是否是列名</b></div> -->
				</div>
				
				<div style="height:25px;"></div>
				<div style="height: 5px; border-top: 1px solid #cccccc; margin: 0px 0px 0px 0px;"></div>
				<div style="float:left;margin-left:0px;">
					<a type="button" class="btn btn-default" onclick="btn_cancel()">取消</a>
				</div>
				<div style="float:right;margin-right:0px;">
					<a type="button" class="btn btn-default" onclick="next_step(0)">上一步</a>&nbsp;&nbsp;&nbsp;
					<a type="button" class="btn btn-default" id="btn_ok">导入</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>