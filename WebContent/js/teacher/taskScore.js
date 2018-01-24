$(function(){
		
	//根据选择的课程,得到课程下的班级
	$("#courseSelect").change(function(){
		var courseId = $(this).children('option:selected').val();
		$.ajax({
			type:"post",
			url:"/classes/getClassesByCourseId",
			data:"courseId="+courseId,
			success:function(classesList){
				$("#classesSelect").empty();
				$("#classesSelect").append("<option>------请选择------</option>");
				for(var i = 0 ;i < classesList.length ; i++){
					$("#classesSelect").append("<option value = '"+classesList[i].id+"'>"+classesList[i].classesNumber+"</option>");
				}
			}
		});
	});
	//根据选择的班级,得到班级下的学生
	$("#classesSelect").change(function(){
		var classesId = $(this).children('option:selected').val();
		$.ajax({
			type:"get",
			url:"/student/getStudentByClassesId",
			data:"classesId="+classesId,
			success:function(studentList){
				$("#studentSelect").empty();
				$("#studentSelect").append("<option>------请选择------</option>");
				for(var i = 0 ;i < studentList.length ; i++){
					$("#studentSelect").append("<option value = '"+studentList[i].id+"'>"+studentList[i].studentName+"</option>");
				}
			}
		});
	});
/*	
	//根据选择的学生,得到学生选择的课程
	$("#studentSelect").change(function(){
		var studentId = $(this).children('option:selected').val();
		//设置用于评分的input
		$("#studentId").val(studentId);
		$.ajax({
			type:"get",
			url:"/course/getCourseByStudentId",
			data:"studentId="+studentId,
			success:function(courseList){
				$("#courseSelect").empty();
				$("#courseSelect").append("<option>------请选择------</option>");
				for(var i = 0 ;i < courseList.length ; i++){
					$("#courseSelect").append("<option value = '"+courseList[i].id+"'>"+courseList[i].courseName+"</option>");
				}
			}
		});
	});*/
	//根据选择的课程,提交表单,查询到对应的任务
	$("#studentSelect").change(function(){
		/*$("#courseId").val($(this).val());*/
		document.forms["searchForm"].submit();
	});
});

function setScoreByTaskId(taskId,studentId){
	//prompt层
	layer.prompt({
		title: '输入分数:1~100，并确认',
		formType: 0
		},function(score, index){
			layer.close(index);
			$.ajax({
				type:"get",
				url:"/task/setScoreByTaskId",
				data:"score="+score+"&taskId="+taskId+"&studentId="+studentId,
				success:function(){
					layer.msg('评分成功', {
						icon : 1,
						time : 2000
					}, function() {
						window.location.reload();
					});
				}
			});
	  }
	);
}











