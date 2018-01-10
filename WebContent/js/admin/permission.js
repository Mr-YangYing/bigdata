//删除权限	
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
			  window.location.href = "/permission/delete?id="+id;
		  });
		}, function(){
		});
	}
//更改权限
function getPermissionById(id){
		layer.open({
			  type: 1,
			  title:["权限修改","font-size:18px"],
			  skin: 'layui-layer-rim', //加上边框
			  area: ['600px', '500px'], //宽高
			  content: $('#updatePermissionDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
			  success: function(){
				  $.ajax({
					  type: 'get',
					  url: '/permission/get',
					  data: "id="+id,
					  success: function(permission){
						  //将返回permission的值写入表单  
						  $("#updateId").val(permission.id);  
						  $("#updateCode").val(permission.code);  
						  $("#updateName").val(permission.name);  
						  $("#updatePage").val(permission.page);  
						  $("#updateGeneratemenu").val(permission.generatemenu);  
						  $("#updateZindex").val(permission.zindex);  
						  $.ajax({
							  type: 'get',
							  url: '/permission/permissionList',
							  success: function(permissionList){
								  $("#permissionSelect").empty();
								  for(var i = 0 ;i < permissionList.length ; i++){
									  	if(permissionList[i].id==permission.parentPermission.id){
									  		$("#permissionSelect").append("<option selected value = '"+permissionList[i].id+"'>"+permissionList[i].name+"</option>");
									  	}
										$("#permissionSelect").append("<option value = '"+permissionList[i].id+"'>"+permissionList[i].name+"</option>");
									}
							  }
							});
						  $("#updateDescription").val(permission.description);  

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
	
//添加权限
function addPermission(){
	layer.open({
		  type: 1,
		  title:["权限添加","font-size:18px"],
		  skin: 'layui-layer-rim', //加上边框
		  area: ['600px', '500px'], //宽高
		  content: $('#updatePermissionDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		  success: function(){
			  $("#updateId").val("-1"); 
			  $("#updateCode").val("");  
			  $("#updateName").val("");  
			  $("#updatePage").val("");  
			  $("#updateGeneratemenu").val("");  
			  $("#updateZindex").val("");  
			  $("#updateDescription").val(""); 
			  
			  $.ajax({
				  type: 'post',
				  url: '/permission/permissionList',
				  success: function(permissionList){
					  $("#permissionSelect").empty();
						$("#permissionSelect").append("<option value='0'>------请选择------</option>");
						for(var i = 0 ;i < permissionList.length ; i++){
							$("#permissionSelect").append("<option value = '"+permissionList[i].id+"'>"+permissionList[i].name+"</option>");
						}
				  }
				});
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
	    	alert(1);
	    	$.ajax({
	    		type:"get",
	    		url:"/permission/batchDelete",
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
/*    	
    	$.ajax({
    		type:"get",
    		url:"/permission/batchDelete",
    		data:{ids:ids},//数组参数会多一个方括号,此时需要禁用将表单元素数组或者对象序列化
    		success:function(){
    			layer.msg('批量删除成功',{
					icon: 1,
					time:2000
					},function(){
						window.location.reload();//重新加载
					});
    			}
    		});*/
    	});
});