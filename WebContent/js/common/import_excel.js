{
	current_config={columns:[],columns_text:"",upload_file_name:"",upload_file_path:"",is_skip_first_row:false,output_table:"",column_count:0}
	var originalColumns  = [];
	var responseColumns = [];
	var golbleDomainName = '';
	/*$(function(){
		nodeData = window.parent.getNodeData();
		parent.setActionDialogSize(600, 460);
		current_config.output_table = nodeData.database + "." + "table_" + nodeData.taskId + "_" + nodeData.nodeId;
		if(nodeData.config!=""){
			current_config = JSON.parse(nodeData.config);
			$("#excel").val(current_config.upload_file_name);
		}
		$("#btn_ok").click(function(){
			//构建当前配置信息
			build_current_config(nodeData);
		});
	});*/
	
	function fileSelected(e) {
		var pathName=window.document.location.pathname;
		var file = document.getElementById('fileToUpload').files[0];
		var fileName = file.name;
		current_config.upload_file_name = fileName;
		var fileExt = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
		if((fileExt!="xls")){
			layer.msg('只支持上传xls格式文件',{
				icon: 1,
				time:2000
			});
			return;
		}
		var xhr = new XMLHttpRequest();
		if (xhr.upload) {
			xhr.upload.addEventListener("progress",
	            function(e) {
	                if (e.lengthComputable) {
						progressValue = ""+Math.round(e.loaded * 100 / e.total) + "%";
						$("#btn_upload").text( progressValue);
	                } else {
	                    //percent.innerHTML = "无法计算文件大小";
	                }
	            },
	            false);
			xhr.onload = function(evt) {
				$("#excel").val(file.name);
				$("#btn_upload").text( "上传");
			}
	        var formdata = new FormData();
			formdata.append("upload", file);
//			current_config.upload_file_path = "/etc/tdp/explore/uploads/"+"111111"+"/"+fileExt
			//current_config.upload_file_path = "D:/";
			formdata.append("filePath",current_config.upload_file_path);
			// 开始上传
			xhr.open("POST", "/uploadFile/upload", true);
			xhr.send(formdata);
		}
		//FormData对象是html5的一个对象,可以实现ajax提交整个表单数据
/*		var formData = new FormData(document.getElementById("uploadForm"));
		$.ajax({
			type:"post",
			url:"/uploadFile/upload",
			data:formData,
			success:function(){
				$("#excel").val(file.name);
			}
		});*/
	}
	function next_step(index,domainName){
		if(index==1){
			if(current_config.upload_file_name==""||current_config.upload_file_name==undefined){
				console.log(current_config.upload_file_name);
				layer.msg('请选择文件!!!',{
					icon: 1,
					time:2000
				});
				return
			}
			$("#choose_file").removeClass("active");
			$("#config_input").removeClass("active");
			$("#choose_field").addClass("active");
			$("#config_args").addClass("active");
			load_excel_schema(domainName);
		}else{
			$("#choose_file").addClass("active");
			$("#config_input").addClass("active");
			$("#choose_field").removeClass("active");
			$("#config_args").removeClass("active");
		}
	}
	//解析Excel
	function load_excel_schema(domainName){
		golbleDomainName = domainName;//赋值给全局的domainName
		$.post("/parseFile/parseExcel",{"uploadFileName":$("#excel").val()/*,"filePath":current_config.upload_file_path*/},function(result){
			originalColumns = result.data;
			console.log(originalColumns);
			var trs = "";
			if(result.code==-1){
				trs = "数据为空";
			}else{
				var data = originalColumns.split(" ");
				if(data.length==current_config.column_count){
					trs+=load_current_config(current_config.columns_text,data);
					$("#is_skip:checkbox").attr("checked", current_config.is_skip_first_row);
				}else{
					//获取数据库表列名
					var optionString ="";
					$.ajax({
						type:"post",
						url:"/"+domainName+"/getColumnName",
						async: false,//ajax异步导致局部变量不能复制给外部变量,所以要设置成false
						success:function(columnName){
							for(var j=0;j<columnName.length;j++){
								optionString+="<option value='"+columnName[j]+"' >"+columnName[j]+"</option>";
							}
						}
					});
					//拼接表格
					for(var i=0;i<data.length;i++){
						trs+="<tr>";
						var tds = "" /*"<td style='width:5%'><input type='checkbox' flag='source_column'/></td>"*/;
						tds+="<td>"+i+"</td>";
						tds+="<td>"+data[i]+"</td>";
						//tds+="<td><input type='text' value='"+data[i]+"'/></td>"
						tds+="<td><select name='columnNameSelect' >"+optionString+"<select></td>";
					/*	tds+="<td><select><option>int</option><option>string</option><option>double</option></select></td>";*/
						trs+=tds;
						trs+="</tr>";
					}
				}
			}
			current_config.column_count = data.length;
			$("#tbody_excel_fields").empty();
			$("#tbody_excel_fields").append(trs);
		})
	}
	$(function(){
		//点击导入按钮
		$("#btn_ok").click(function(){
			$.ajax({
				type:"post",
				url:"/parseFile/parseExcelBody",
				data:{"uploadFileName":$("#excel").val(),"filePath":current_config.upload_file_path},
				success:function(excelData){//得到Excel中的数据
					var databaseFiled = [];
					$("select :selected").each(function(){
						databaseFiled.push($(this).val());//页面选择的数据库字段数据
					});
					$.ajax({
						type:"post",
						//contentType:"application/json",
						url:"/"+golbleDomainName+"/batchUpdate",
						data:{"excelData":JSON.stringify(excelData),"databaseFiled":JSON.stringify(databaseFiled)},
						success:function(){
							layer.msg('导入成功!!!', {
								  icon: 1,
								  time: 2000 //2秒关闭（如果不配置，默认是3秒）
								}, function(){
								  //do something
									parent.location.reload(); 
								});
						},
						error:function(){
							layer.msg('导入出错!!', {
								  icon: 1,
								  time: 2000 //2秒关闭（如果不配置，默认是3秒）
								}, function(){
								});
						}
					});
				}
			});
		});
	});
	
	/*
	function load_current_config(columns_text,data){
		var column_line = columns_text.split(";");
		var trs = "";
		var column_index = [];
		var column_name = [];
		var column_type = [];
		for(var i=0;i<column_line.length;i++){
			column_index.push(parseInt(column_line[i].split(",")[0]));
			column_name.push(column_line[i].split(",")[1]);
			column_type.push(column_line[i].split(",")[2]);
		}
		for(var i=0;i<data.length;i++){
			trs+="<tr>"
			if($.inArray(i, column_index)>=0){
				var tds = "<td style='width:5%'><input type='checkbox' flag='source_column' checked='true'/></td>";
				tds+="<td>"+i+"</td>"
				tds+="<td>"+data[i]+"</td>"
				tds+="<td><input type='text' value='"+ column_name[$.inArray(i, column_index)]+"'/></td>"
				tds+="<td><select>"
				var options = "";
			    if(column_type[$.inArray(i, column_index)]=="int"){
			    	options+="<option selected='selected'>int</option>"
			    	options+="<option>string</option>"
			    	options+="<option>double</option>"
			    }else if(column_type[$.inArray(i, column_index)]=="string"){
			    	options+="<option>int</option>"
			    	options+="<option selected='selected'>string</option>"
			    	options+="<option>double</option>"
			    }else if(column_type[$.inArray(i, column_index)]=="double"){
			    	options+="<option>int</option>"
			    	options+="<option>string</option>"
			    	options+="<option selected='selected'>double</option>"
			    }	
			    tds+=options;
			    tds+="</select></td>"
				
			}else{
				var tds = "<td style='width:5%'><input type='checkbox' flag='source_column'/></td>";
				tds+="<td>"+i+"</td>"
				tds+="<td>"+data[i]+"</td>"
				tds+="<td><input type='text' value='"+data[i]+"'/></td>"
				tds+="<td><select><option>int</option><option>string</option><option>double</option></select></td>"
			}
			trs+=tds;
			trs+="</tr>"
		}
		return trs;
	}
	//“选择所有”复选框动作处理
	$("#tbl_excel_fields #check_all").click(function () {
	    var checkedFlag = this.checked;
	    $("input[type='checkbox'][flag='source_column']").each(function () {
	        this.checked = checkedFlag;
	    });
	});
	
	function get_column_text(){
		var checkedElements = $("input[type='checkbox'][flag='source_column']:checked");
		var column_lines="";
		for(var i=0;i<checkedElements.length;i++){
			var current = checkedElements.eq(i);
			var row = current.parent().parent();
			var num = row.children().eq(1).text();
			var column_name = row.children().eq(3).children().eq(0).val();
			var column_type = row.children().eq(4).children().eq(0).val();
			column_lines += num+","+column_name+","+column_type;
			if(i!=checkedElements.length-1){
				column_lines+=";"
			}
		}
		current_config.columns_text = column_lines;
	}
	function is_skip_first_row(){
		var flag = $("#is_skip").is(':checked');
		current_config.is_skip_first_row=flag;
	}
	//点击取消按钮，关闭弹出框
	function btn_cancel(){
		window.parent.closeActionDialog();
	}
	function build_current_config(){
		get_column_text();
		is_skip_first_row();
		nodeData.outputs = current_config.output_table;
        var shell = "/etc/tdp/explore/preprocessing/submit.sh ";
        var script = shell + "ImportExcel" +
            //" -DinputFileName=\"" + current_config.upload_file_path +
        	" -DinputFileName=\"/home/lzj/data/query_result.xls"+
            "\" -DoutputTableName=\"" + current_config.output_table +
            "\" -DcolNames=\"" + current_config.columns_text +
            "\" -DskipFirstRow=" + current_config.is_skip_first_row
        alert(script);
        nodeData.config = JSON.stringify(current_config);
        nodeData.completed = "true";
        nodeData.script = script;
        window.parent.setNodeData(nodeData);
        $.post("analysisTaskNodeAction!run.do",{taskId: nodeData.taskId,nodeId:nodeData.nodeId,script:script,outputs:current_config.output_table},function(result){
        });
        window.parent.closeActionDialog();
	}*/
}
