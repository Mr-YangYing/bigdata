 // 授权树初始化
var setting = {
		data : {
			key : {
				title : "t"
			},
			simpleData : {
				enable : true
			}
		},
		check : {
			enable : true//使用ztree的勾选效果
		}
	};

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
    	$.ajax({
    		type:"get",
    		url:"/role/batchDelete",
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
    });
	// 点击保存
	/*$('#save').click(function(){
		//表单校验
		var v = $("#roleForm").form("validate");
		if(v){
			//根据ztree的id获取ztree对象
			var treeObj = $.fn.zTree.getZTreeObj("functionTree");
			//获取ztree上选中的节点，返回数组对象
			var nodes = treeObj.getCheckedNodes(true);
			var array = new Array();
			for(var i=0;i<nodes.length;i++){
				var id = nodes[i].id;
				array.push(id);
			}
			var functionIds = array.join(",");
			//为隐藏域赋值（权限的id拼接成的字符串）
			$("input[name=functionIds]").val(functionIds);
			$("#roleForm").submit();
		}
	});*/
});
//删除角色
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
			  window.location.href = "/role/delete?id="+id;
		  });
		}, function(){
		});
	}
//更改角色
function getRoleById(id){
		layer.open({
			  type: 1,
			  title:["角色修改","font-size:18px"],
			  skin: 'layui-layer-rim', //加上边框
			  area: ['600px', '500px'], //宽高
			  content: $('#updateRoleDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
			  success: function(){
				  $.ajax({
					  type: 'get',
					  url: '/role/get',
					  data: "id="+id,
					  success: function(role){
						  //将返回rolet的值写入表单
						  $("#updateId").val(role.id);  
						  $("#updateCode").val(role.code);  
						  $("#updateName").val(role.name);
						  $("#updateDescription").val(role.description);
							//根据角色获取权限
						  var rolePermission;
							$.ajax({
								url : '/permission/getPermissionByRoleId',
								type : 'get',
								data : "id="+role.id,
								async: false,
								success : function(data) {
									rolePermission = data;
									console.log(data);
								}
							});
							//加载权限树
							$.ajax({
								url : '/permission/permissionListZtree',
								type : 'POST',
								dataType : 'json',
								success : function(data) {
									$.fn.zTree.init($("#permissionTree"), setting, data);
									if(rolePermission.length>0){
										//获取ztree对象
										var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
										//遍历勾选角色关联的菜单数据
										for(var i=0;i<rolePermission.length;i++){
											//根据角色菜单节点数据的属性搜索，获取与完整菜单树完全匹配的节点JSON对象集合
											var nodes = treeObj.getNodesByParam("id", rolePermission[i].id, null);
											//勾选当前选中的节点
											treeObj.checkNode(nodes[0],true,true);
										}
									}
								},
								error : function(msg) {
									alert('树加载异常!');
								}
							});
					  	}
					});
			  },
			  btn:['提交','取消'],
			  btn1:function(index){
					//根据ztree的id获取ztree对象
					var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
					//获取ztree上选中的节点，返回数组对象
					var nodes = treeObj.getCheckedNodes(true);
					var array = new Array();
					for(var i=0;i<nodes.length;i++){
						var id = nodes[i].id;
						array.push(id);
					}
					var permissionIds = array.join(",");
					//为隐藏域赋值（权限的id拼接成的字符串）
					$("input[name=permissionIds]").val(permissionIds);
					//$("#roleForm").submit();
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
	
//添加角色
function addRole(){
	layer.open({
		  type: 1,
		  title:["角色添加","font-size:18px"],
		  skin: 'layui-layer-rim', //加上边框
		  area: ['600px', '500px'], //宽高
		  content: $('#updateRoleDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		  success: function(){
			  $("#updateId").val("-1");
			  $("#updateCode").val("");  
			  $("#updateName").val("");
			  $("#updateDescription").val("");
				//加载权限树
				$.ajax({
					url : '/permission/permissionListZtree',
					type : 'POST',
					dataType : 'json',
					success : function(data) {
						console.log(data);
						$.fn.zTree.init($("#permissionTree"), setting, data);
					},
					error : function(msg) {
						alert('树加载异常!');
					}
				});
		  },
		  btn:['提交','取消'],
		  btn1:function(index){
			//根据ztree的id获取ztree对象
				var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
				//获取ztree上选中的节点，返回数组对象
				var nodes = treeObj.getCheckedNodes(true);
				var array = new Array();
				for(var i=0;i<nodes.length;i++){
					var id = nodes[i].id;
					array.push(id);
				}
				var permissionIds = array.join(",");
				//为隐藏域赋值（权限的id拼接成的字符串）
				$("input[name=permissionIds]").val(permissionIds);
				//$("#roleForm").submit();
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
