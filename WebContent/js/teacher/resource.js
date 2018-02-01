
var fileUploadName = ""//文件上传的名称
var courseType = "";//我的课程
	
$(function(){
	
	//根据选择的课程类型,得到课程列表
	$("input:radio[type='radio']").change(function(){
		var courseURL;
		var teacherId = $("#teacherId").val();
		courseType = $(this).next().text();
		if(courseType == "我的课程"){
			courseURL = "/course/getCoursesByTeacherId";//我的课程
		}else{
			courseURL = "/course/getOtherCoursesByTeacherId";//其他课程
		}
		$.ajax({
			type:"get",
			url:courseURL,
			data:"teacherId="+teacherId,
			success:function(courseList){
				$("#courseSelect").empty();
				$("#courseSelect").append("<option value = '-1'>------请选择------</option>");
				for(var i = 0 ;i < courseList.length ; i++){
					$("#courseSelect").append("<option value = '"+courseList[i].id+"'>"+courseList[i].courseName+"</option>");
				}
			}
		});
	});
	
	//根据选择的课程,查询任务列表
	$("#courseSelect").change(function(){
		var taskURL;
		var courseId = $(this).children('option:selected').val();
		if(courseType == "我的课程"){
			taskURL = "/task/getTaskByCourseId";//获取所有任务
		}else{
			taskURL = "/task/getPublishedTaskByCourseId";//获取已经发布的任务
		}
		$.ajax({
			type:"get",
			url:taskURL,
			data:"courseId="+courseId,
			success:function(taskList){
				$("#taskSelect").empty();
				$("#taskSelect").append("<option>------请选择------</option>");
				for(var i = 0 ;i < taskList.length ; i++){
					$("#taskSelect").append("<option value = '"+taskList[i].id+"'>"+taskList[i].taskName+"</option>");
				}
			}
		});
	});
	//根据选择的任务,提交表单,查询到对应的资源
	$("#taskSelect").change(function(){
		if ($("#courseSelect").children('option:selected').val() == -1) {
				layer.msg('请正确选择',{
					icon: 0,
					time:2000
				});
			}else{
				document.forms["searchForm"].submit();
			}
		
	});
});
//选择文件后
function fileSelected(){
	var file = document.getElementById("uploadFile").files[0];
	fileUploadName = file.name;
	$("#fileName").val(fileUploadName);
}
//资源上传
function addResource(taskId){
	layer.open({
		  type: 1,
		  title:["资源上传","font-size:18px"],
		  skin: 'layui-layer-rim', //加上边框
		  area: ['600px', '500px'], //宽高
		  content: $('#resourceDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		  success: function(){
			  $("#taskId").val(taskId);
			  $("#updateId").val('-1');
			  $("#resourceNumber").val('');
			  $("#resourceName").val('');
			  $("#description").val('');
			  $("#fileName").val('');
		  },
		  btn:['提交','取消'],
		  btn1:function(index){
			  $("#courseType").val(courseType);
				
				var fileValue = $("#uploadFile").val();//获取file下载框的值
				if(fileValue!='' && fileValue.length > 1){//判断文件是否为空
					document.forms['updateForm'].submit();
					layer.msg('上传成功',{
						icon: 1,
						time:2000
					});
					layer.close(index);
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
//资源编辑
function editResource(id,taskId){
	layer.open({
		type: 1,
		title:["资源上传","font-size:18px"],
		skin: 'layui-layer-rim', //加上边框
		area: ['600px', '500px'], //宽高
		content: $('#resourceDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		success: function(){
			$("#taskId").val(taskId);
			  $.ajax({
				  type:"get",
				  url:"/resource/getById",
				  data:"id="+id,
				  success:function(resource){
					  $("#updateId").val(resource.id);
					  $("#resourceNumber").val(resource.resourceNumber);
					  $("#resourceName").val(resource.resourceName);
					  var resourceAddr = resource.resourceAddr.split("\\");
					  $("#fileName").val(resourceAddr[resourceAddr.length-1]);
					  $("#description").val(resource.description);
				  }
			  });
		},
		btn:['提交','取消'],
		btn1:function(index){
			$("#courseType").val(courseType);
			
			var fileValue = $("#uploadFile").val();//获取file下载框的值
			if(fileValue!='' && fileValue.length > 1){//判断文件是否为空
				document.forms['updateForm'].submit();
				layer.msg('编辑成功',{
					icon: 1,
					time:2000
				});
				layer.close(index);
			}else{
				layer.msg('请重新选择文件!!',{
					icon: 0,
					time:2000
				});
			}
		},
		btn2:function(){
			
		}
	});
}
//资源删除
function deleteResource(resourceId){
	//询问框
	layer.confirm('您确定要删除么？', {
	  btn: ['确定','取消'],//按钮
	  icon:3
	}, function(){
		$.ajax({
			type:"get",
			data:"resourceId="+resourceId,
			url:"/resource/resourceDelete",
			success:function(){
				layer.msg('删除成功', {
					icon : 1,
					time : 2000
				}, function() {
					window.location.reload();
				});
			}
		});
	}, function(){
	});
}



