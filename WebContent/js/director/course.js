//关闭课程
function closeCourseById(id){
		//询问框
		layer.confirm('您确定要关闭课程么？', {
		  btn: ['确定','取消'],//按钮
		  icon:3
		}, function(){
		  layer.msg('关闭成功',{
			  icon: 1,
			  time:2000
			  },function(){
			 window.location.href = "/course/closeCourse?id="+id;
		  });
		}, function(){
		});
	}
//开启课程
function openCourseById(id){
		//询问框
		layer.confirm('您确定要开启课程么？', {
		  btn: ['确定','取消'],//按钮
		  icon:3
		}, function(){
		  layer.msg('开启成功',{
			  icon: 1,
			  time:2000
			  },function(){
			  window.location.href = "/course/openCourse?id="+id;
		  });
		}, function(){
		});
	}

//日期的开始时间和结束时间的处理:开始时间<=结束时间,结束时间>=开始时间
 $(function() {
	//处理日期控件
	$("input[name='startTime']").addClass("Wdate").click(function() {
		WdatePicker({
			maxDate : $("input[name='endDate']").val()
		});
	});
	$("input[name='endTime']").addClass("Wdate").click(function() {
		WdatePicker({
			minDate : $("input[name='startTime']").val(),
			maxDate : new Date()
		});
	});

});