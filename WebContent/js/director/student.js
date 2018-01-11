//删除学生	
function deleteById(id){
		//询问框
		layer.confirm('您确定要删除么？', {
		  btn: ['确定','取消'],//按钮
		  icon:3
		}, function(){
		  layer.msg('删除成功',{
			  icon: 1,
			  time:1000
			  },function(){
			  window.location.href = "/student/delete?id="+id;
		  });
		}, function(){
		});
	}
//更改学生
function getStudentById(id){
		layer.open({
			  type: 1,
			  title:["学生修改","font-size:18px"],
			  skin: 'layui-layer-rim', //加上边框
			  area: ['600px', '500px'], //宽高
			  content: $('#updateStudentDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
			  success: function(){
				  $.ajax({
					  type: 'get',
					  url: '/student/get',
					  data: "id="+id,
					  success: function(student){
						  //将返回student的值写入表单
						  $("#updateId").val(student.id);  
						  $("#updateStudentNumber").val(student.studentNumber);  
						  $("#updateStudentName").val(student.studentName);
						  $("#updateCurrentTerm").val(student.currentTerm);
						  $("#updateCollege").val(student.college);
						  $("#updateProfession").val(student.profession);
						  //$("#updateClasses").val(student.classes);
					  }
					});
			  },
			  btn:['提交','取消'],
			  btn1:function(index){
				  //判断学生学号是否为空
					if($("#updateStudentNumber").val()==""||$("#updateStudentNumber").val()==" "){
						layer.msg('学生学号不能为空,请输入账号!!!',{
							icon: 2,
							time:3000
							});
					}else{
						//判断教师账号是否修改
						$.ajax({
							  type: "get",
							  url: "/student/get",
							  data: "id="+id,
							  success: function(student){
								  //如果账户已经修改
								 if(student.studentNumber!=$("#updateStudentNumber").val()){

										//判断教师账号是否已经存在
									  	$.post('/student/checkStudentNumber', {studentNumber:$("#updateStudentNumber").val()},function(data){
										  if(data==0){
											  //已存在
											  layer.msg('您输入的学生学号已存在,请重新输入!!!',{
												  icon: 2,
												  time:3000
											  });
										  }else{
											  //不存在
												 document.forms['updateForm'].submit();
													layer.msg('修改成功',{
														icon: 1,
														time:1000
														});
													layer.close(index); 
											  
										  }	
									  	});
								 }else {
								  //如果账号没有修改,就可以直接修改学生数据
									 document.forms['updateForm'].submit();
										layer.msg('修改成功',{
											icon: 1,
											time:1000
											});
										layer.close(index); 
								}
							  }
							});

					}
						//window.location.reload();//重新加载,刷新当前页面
				  },
			  btn2:function(){
					
				}
			});
	}
	
//添加学生
function addStudent(){
	layer.open({
		  type: 1,
		  title:["学生添加","font-size:18px"],
		  skin: 'layui-layer-rim', //加上边框
		  area: ['600px', '500px'], //宽高
		  content: $('#updateStudentDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		  success: function(){
			  $("#updateId").val("-1"); 
			  $("#updateStudentNumber").val('');  
			  $("#updateStudentName").val('');
			  $("#updateCurrentTerm").val('');
			  $("#updateCollege").val('');
			  $("#updateProfession").val('');
			 // $("#updateClasses").val('');
		  },
		  btn:['提交','取消'],
		  btn1:function(index){
			  //判断学生学号是否为空
			  if($("#updateStudentNumber").val()==""||$("#updateStudentNumber").val()==" "){
				  layer.msg('学生学号不能为空,请输入学号!!!',{
						icon: 2,
						time:3000
					});
			  }else{
					//判断学生学号是否已经存在
				  $.post('/student/checkStudentNumber', {studentNumber:$("#updateStudentNumber").val()},function(data){
					  if(data==0){
						  layer.msg('您输入的学生学号已存在,请重新输入!!!',{
							  icon: 2,
							  time:3000
						  });
					  }else{
							  document.forms['updateForm'].submit();
							  layer.msg('添加成功',{
								  icon: 1,
								  time:1000
							  });
							  layer.close(index);
					  }
				  });
			  }

			},
		  btn2:function(){
				
			}
		});
}

$(function(){
	 //点击批量删除按钮
    $(".btn_batch_delete").on("click",function(){
    	var ids = $.map($(".currentCheckbox:checked"),function(item){
    		return $(item).data("eid");
    	});
    	console.log(ids);
    	if(ids.length==0){
    		layer.msg('您还没有选中!!!');
    		return;
    	}
    	
    	//询问框
		layer.confirm('您确定要批量删除么？', {
		  btn: ['确定','取消'],//按钮
		  icon:3
		}, function(){
	    	$.ajax({
	    		type:"get",
	    		url:"/student/batchDelete",
	    		data:{ids:ids},//数组参数会多一个方括号,此时需要禁用将表单元素数组或者对象序列化
	    		success:function(){
	    			layer.msg('批量删除成功',{
						icon: 1,
						time:2000
						},function(){
							window.location.reload();//重新加载
						});
	    		}
	    	});
		}, function(){
		});
    	

    });
    //点击配置班级按钮
    $(".btn_batch_setClasses").on("click",function(){
    	var ids = $.map($(".currentCheckbox:checked"),function(item){
    		return $(item).data("eid");
    	});
    	if(ids.length==0){
    		layer.msg('您还没有选中!!!');
    		return;
    	}
    	
    	layer.open({
  		  type: 1,
  		  title:["配置班级","font-size:18px"],
  		  skin: 'layui-layer-rim', //加上边框
  		  area: ['600px', '500px'], //宽高
  		  content: $('#setClassesDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
  		  success: function(){
  			 $.ajax({
				  type: 'post',
				  url: '/classes/getClassesList',
				  success: function(classesList){
					  $("#classesSelect").empty();
						$("#classesSelect").append("<option>------请选择------</option>");
						for(var i = 0 ;i < classesList.length ; i++){
							$("#classesSelect").append("<option value = '"+classesList[i].id+"'>"+classesList[i].classesNumber+"</option>");
						}
				  }
				});
  		  },
  		  btn:['提交','取消'],
  		  btn1:function(index){
  			  var classesId = $("#classesSelect option:selected").val();
			 $.ajax({
				  type: 'get',
				  url: "/student/batchSetClasses",
				  data:"ids="+ids+"&classesId="+classesId,
				  success: function(classesList){
						layer.msg('设置班级成功', {
							icon : 1,
							time : 2000
						},function(){
							window.location.reload();//重新加载
						});
				  }
				});

  		},
  		  btn2:function(){
  				
  			}
  		});
    });
    
    
	
});