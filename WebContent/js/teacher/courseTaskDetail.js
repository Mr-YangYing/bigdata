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
		  }
	});
}