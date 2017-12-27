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

function setClasses(courseId){
	layer.open({
		  type: 1,
		  title:["配置班级","font-size:18px"],
		  skin: 'layui-layer-rim', //加上边框
		  area: ['600px', '500px'], //宽高
		  content: $('#setClassesDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		  success: function(){
			 $.ajax({
				  type: 'post',
				  url: '/classes/getClassesNotInCourseId',
				  data:"courseId="+courseId,
				  success: function(classesList){
					  $("#left").empty();
						for(var i = 0 ;i < classesList.length ; i++){
							$("#left").append("<option value = '"+classesList[i].id+"'>"+classesList[i].classesNumber+"</option>");
						}
				  }
				});
			 $.ajax({
				 type: 'post',
				 url: '/classes/getClassesByCourseId',
				 data:"courseId="+courseId,
				 success: function(classesList){
					 $("#right").empty();
					 for(var i = 0 ;i < classesList.length ; i++){
						 $("#right").append("<option value = '"+classesList[i].id+"'>"+classesList[i].classesNumber+"</option>");
					 }
				 }
			 });
		  },
		  btn:['提交','取消'],
		  btn1:function(index){
			//获取已选择班级Id
		   	var ids = $.map($("#right option"),function(item){
		   		return $(item).val();
		   	});
		   	//配置班级
		   	$.post("/course/setClasses",{"ids":ids,"courseId":courseId},function(){
		   		layer.msg('设置班级成功', {
		   			icon : 1,
		   			time : 2000
		   		},function(){
		   			window.location.reload();//重新加载
		   		});
		   	});

		},
		  btn2:function(){
				
			}
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
	/*1.选中单击去右边*/
	$("#selectOneToRight").click(function(){
		console.log($("#left option:selected"));
		console.log($("#right"));
		$("#left option:selected").appendTo($("#right"));
	});
	
	/*2.单击全部去右边*/
	$("#selectAllToRight").click(function(){
		$("#left option").appendTo($("#right"));
	});
	
	/*1.选中单击去左边*/
	$("#selectOneToleft").click(function(){
		$("#right option:selected").appendTo($("#left"));
	});
	
	/*2.单击全部去左边*/
	$("#selectAllToleft").click(function(){
		$("#right option").appendTo($("#left"));
	});
	
});