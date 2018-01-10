//删除教师
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
			  window.location.href = "/teacher/delete?id="+id;
		  });
		}, function(){
		});
	}
	//更改教师
	function getTeacherById(id){
		layer.open({
			  type: 1,
			  title:["教师修改","font-size:18px"],
			  skin: 'layui-layer-rim', //加上边框
			  area: ['600px', '500px'], //宽高
			  content: $('#updateTeacherDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
			  success: function(){
					$.ajax({
						  type: "get",
						  url: "/teacher/get",
						  data: "id="+id,
						  success: function(teacher){
							  //将返回teacher的值写入表单
							  $("#updateId").val(teacher.id);  
							  $("#updateTeacherAccount").val(teacher.teacherAccount);  
							  $("#updateTeacherName").val(teacher.teacherName);
							  $("#updatePositionalTitles").val(teacher.positionalTitles);
						  }
						});
			  },
			  btn:['提交','取消'],
			  btn1:function(index){
				document.forms['updateForm'].submit();
				layer.msg('修改成功',{
					icon: 1,
					time:1000
					});
					layer.close(index);
					//window.location.reload();//重新加载,刷新当前页面
				},
			  btn2:function(){
					
				}
			});
	}
	//添加教师
	function addTeacher(){
		layer.open({
			  type: 1,
			  title:["教师添加","font-size:18px"],
			  skin: 'layui-layer-rim', //加上边框
			  area: ['600px', '500px'], //宽高
			  content: $('#updateTeacherDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
			  success: function(){
					//将返回teacher的值写入表单
				  $("#updateId").val("-1");  
				  $("#updateTeacherAccount").val("");  
				  $("#updateTeacherName").val("");
				  $("#updatePositionalTitles").val("");
			  },
			  btn:['提交','取消'],
			  btn1:function(index){
				document.forms['updateForm'].submit();
				layer.msg('添加成功',{
					icon: 1,
					time:1000
					});
				layer.close(index);
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
	    		url:"/teacher/batchDelete",
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
    
    
	
});