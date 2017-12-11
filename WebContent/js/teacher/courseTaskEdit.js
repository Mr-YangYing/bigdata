//为指定的课程添加任务
function addTask(courseId){
	layer.open({
		  type: 1,
		  title:["任务详情","font-size:18px"],
		  skin: 'layui-layer-rim', //加上边框
		  area: ['600px', '500px'], //宽高
		  content: $('#taskDetailDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		  success: function(){
			  $("#updateId").val("-1");
			  $("#taskName").val('');
			  $("#usefulTime").val('');
			//遍历所有name='taskType'的input
			  $("input[name='taskType']").each(function(){
				  $(this).removeAttr("checked");
			  });
			  $("input[name='uploadReport']").each(function(){
				  $(this).removeAttr("checked");
			  });
			  $("input[name='difficulty']").each(function(){
				  $(this).removeAttr("checked");
			  });
			  
			  $("#description").val('');
		  },
		  btn:['提交','取消'],
		  btn1:function(index){
			$("input[name='courseId']").val(courseId);//将课程隐藏Id设置进去
			document.forms['updateForm'].submit();//提交表单
			
			layer.msg('添加成功',{
				icon: 1,
				time:3000
				});
			layer.close(index);
			//window.location.reload();//重新加载当前页面
			},
		  btn2:function(){
				
			}
	});
}
//发布任务
function publishTaskById(taskId,courseId){
	//询问框
	layer.confirm('您确定要发布此任务么？', {
	  btn: ['确定','取消'],//按钮
	  icon:3
	}, function(){
	  layer.msg('发布成功',{
		  icon: 1,
		  time:1000
		  },function(){
		  window.location.href = "/task/publishTask?taskId="+taskId+"&courseId="+courseId;
	  });
	}, function(){
	});
}
//编辑任务
function editTaskById(taskId,courseId){
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
					  $("#updateId").val(task.id);
					  $("#taskName").val(task.taskName);
					  $("#usefulTime").val(task.usefulTime);
					  //遍历所有name='taskType'的input
					  $("input[name='taskType']").each(function(){
						  if($(this).val()==task.taskType){
							  $(this).attr("checked","checked");
						  }
					  });
					  $("input[name='uploadReport']").each(function(){
						  if($(this).val()==task.uploadReport){
							  $(this).attr("checked","checked");
						  }
					  });
					  $("input[name='difficulty']").each(function(){
						  if($(this).val()==task.difficulty){
							  $(this).attr("checked","checked");
						  }
					  });
					  
					  $("#description").val(task.description);
				  }
			  });
		  },
		  btn:['提交','取消'],
		  btn1:function(index){
			$("input[name='courseId']").val(courseId);//将课程隐藏Id设置进去
			document.forms['updateForm'].submit();
			layer.msg('修改成功',{
				icon: 1,
				time:3000
				});
			layer.close(index);
		  },
		  btn2:function(){
				
		  },
	});
}
//删除任务
function deleteTaskById(taskId,courseId){
	//询问框
	layer.confirm('您确定要删除此任务么？', {
	  btn: ['确定','取消'],//按钮
	  icon:3
	}, function(){
	  layer.msg('删除成功',{
		  icon: 1,
		  time:1000
		  },function(){
		  window.location.href = "/task/deleteTask?taskId="+taskId+"&courseId="+courseId;
	  });
	}, function(){
	});
}