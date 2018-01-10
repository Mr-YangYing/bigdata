//失去焦点事件.检查用户是否存在
function checkUsername(){
	//发送ajax请求，获取所有的角色数据
	$.post('/user/checkUsername', {username:$("#updateUsername").val()},function(data){
		if(data==0){
				layer.msg('您用户已存在,请重新输入用户名!!!',{
					icon: 1,
					time:3000
				});
			}
	});
}
//删除用户	
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
			  window.location.href = "/user/delete?id="+id;
		  });
		}, function(){
		});
	}
//更改用户
function getUserById(id){
		layer.open({
			  type: 1,
			  title:["用户修改","font-size:18px"],
			  skin: 'layui-layer-rim', //加上边框
			  area: ['600px', '500px'], //宽高
			  content: $('#updateUserDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
			  success: function(){
				  $.ajax({
					  type: 'get',
					  url: '/user/get',
					  data: "id="+id,
					  success: function(user){
						  //将返回user的值写入表单
						  $("#updateId").val(user.id);  
						  $("#updateUsername").val(user.username);  
						  $("#passwordDIV").hide();
						  //遍历所有name='gender'的input
						  $("input[name='gender']").each(function(){
							  if($(this).val()==user.gender){
								  $(this).attr("checked","checked");
							  }
						  });
						  $("#updateGender").val(user.gender);
						  $("#updateStation").val(user.station);
						  $("#updateTelephone").val(user.telephone);
						  $("#updateRemark").val(user.remark);
						//发送ajax请求，获取所有的角色数据
						  $.ajax({
							  type: 'post',
							  url: '/role/roleList',
							  async:false,
							  success: function(data){
									//在ajax回调函数中，解析json数据，展示为checkbox
									$("#roleDIV").empty();
									for(var i=0;i<data.length;i++){
										var id = data[i].id;
										var name = data[i].name;
										$("#roleDIV").append('<input id="'+id+'" type="checkbox" name="roleIds" value="'+id+'"><label for="'+id+'">'+name+'</label>');
									}
							  }
						  });

							  //遍历所有name='roleIds'的input
							  $("input[name='roleIds']").each(function(){
								  for(var i=0;i<user.roles.length;i++){
									  if($(this).attr("id")==user.roles[i].id){
										  $(this).attr("checked","checked");
									  } 
								  }
							  });
							
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
				},
			  btn2:function(){
					
				}
			});
	}
	
//添加用户
function addUser(){
	layer.open({
		  type: 1,
		  title:["用户添加","font-size:18px"],
		  skin: 'layui-layer-rim', //加上边框
		  area: ['600px', '500px'], //宽高
		  content: $('#updateUserDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		  success: function(){
			  $("#updateId").val("-1"); 
			  $("#updateUsername").val(""); 
			  $("#passwordDIV").show();
			  $("#updatePassword").val("");
			  $("#updateGender").val("");
			  $("#updateStation").val("");
			  $("#updateTelephone").val("");
			  $("#updateRemark").val("");
			  
			//发送ajax请求，获取所有的角色数据
				$.post('/role/roleList',function(data){
					//在ajax回调函数中，解析json数据，展示为checkbox
					$("#roleDIV").empty();
					for(var i=0;i<data.length;i++){
						var id = data[i].id;
						var name = data[i].name;

						$("#roleDIV").append('<input id="'+id+'" type="checkbox" name="roleIds" value="'+id+'"><label for="'+id+'">'+name+'</label>');
					}
				});
		  },
		  btn:['提交','取消'],
		  btn1:function(index){
				//发送ajax请求，获取所有的角色数据
				$.post('/user/checkUsername', {username:$("#updateUsername").val()},function(data){
					if(data==0){
							layer.msg('您用户已存在,请重新输入用户名!!!',{
								icon: 1,
								time:3000
							});
						}else{
							if($("#updatePassword").val()==""||$("#updatePassword").val()==" "){
								layer.msg('密码不能为空,请输入密码!!!',{
									icon: 1,
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


							//layer.close(index);
						}
				});
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
	    		url:"/user/batchDelete",
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