//资源上传
function addReport(taskId,courseId,studentId){
	layer.open({
		  type: 1,
		  title:["作业上传","font-size:18px"],
		  skin: 'layui-layer-rim', //加上边框
		  area: ['600px', '500px'], //宽高
		  content: $('#reportDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		  success: function(){
			  $("#taskId").val(taskId);
			  $("#courseId").val(courseId);
			  //回显学生上传的作业
			  $.ajax({
				  type:"get",
				  url:"/report/getReport",
				  data:"taskId="+taskId+"&studentId="+studentId,
				  success:function(report){
					  var hasReport = $.isEmptyObject(report);//判断report是否为空
					  console.log(hasReport);
					  if(!hasReport){
						  $("#reportName").val(report.reportName);
						  var reportAddr = report.reportAddress.split("\\");
						  $("#fileName").val(reportAddr[reportAddr.length-1]);  
					  }else{
						  $("#reportName").val('');
						  $("#fileName").val(''); 
					  }
				  }
			  });
		  },
		  btn:['提交','取消'],
		  btn1:function(index){
				var fileValue = $("#uploadFile").val();//获取file下载框的值
				if(fileValue!='' && fileValue.length > 1){//判断文件是否为空
					//获取上传的文件的扩展名,并判断
					var file = document.getElementById('uploadFile').files[0];
					var fileName = file.name;
					var fileExt = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
					if((fileExt!="pdf")){
						layer.msg('只支持上传pdf格式文件',{
							icon: 0,
							time:2000
						});
					}else{
						document.forms['uploadForm'].submit();
						layer.msg('上传成功',{
							icon: 1,
							time:2000
						});
						layer.close(index);
					}
				}else{
					layer.msg('请选择文件!!',{
						icon: 0,
						time:2000
					});
				}
			},
		  btn2:function(){
				
			}
		});
}
//查看任务详情
function getTaskDetailById(taskId){
	layer.open({
		  type: 1,
		  title:["任务详情","font-size:18px"],
		  skin: 'layui-layer-rim', //加上边框
		  area: ['600px', '500px'], //宽高
		  content: $('#taskDetailDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		  success: function(){
			  $.ajax({
				  type:"post",
				  url:"/task/getTaskById",
				  data:{"taskId":taskId},
				  success:function(task){
					  $("#taskName").val(task.taskName);
					  $("#usefulTime").val(task.usefulTime);
					  //遍历所有name='taskType'的input
					  $("input[name='taskType']").each(function(){
						  if($(this).val()==task.taskType){
							  $(this).attr("checked","checked");
						  }else{
							  $(this).attr("disabled","disabled");
						  }
					  });
					  $("input[name='uploadReport']").each(function(){
						  if($(this).val()==task.uploadReport){
							  $(this).attr("checked","checked");
						  }else{
							  $(this).attr("disabled","disabled");
						  }
					  });
					  $("input[name='difficulty']").each(function(){
						  if($(this).val()==task.difficulty){
							  $(this).attr("checked","checked");
						  }else{
							  $(this).attr("disabled","disabled");
						  }
					  });
					  
					  $("#description").val(task.description);
				  }
			  });
		  }
	});
}

//选择文件后
function fileSelected(){
	var file = document.getElementById("uploadFile").files[0];
	fileUploadName = file.name;
	$("#fileName").val(fileUploadName);
}





