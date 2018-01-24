<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="header container-fluid" style="height: 15%;background-color: #1364bd;color: white;">
	<div class="row">
		<div class="logo col-md-4" style="height: 15%;line-height: 62px;">
			<img alt="" src="/images/biaoti.png">
		</div>
		<div class="col-md-5">

		</div>
		<div class="col-md-3" style="height: 15%; line-height: 62px;padding-left: 15px">
			<div class="dropdown">
				<button type="button" class="btn dropdown-toggle" id="dropdownMenu1"
					data-toggle="dropdown" style="background-color: #3477CC; color: white; font-size: 14px">
					${loginUser.username} <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu" style="background-color: #3477CC; color: white; font-size: 14px;padding: 0">
					<li role="presentation" ><a role="menuitem" href="javascript:updatePassword()">修改密码</a></li>
				</ul>
				<a href="/user/loginOut">
					 <img alt="退出" src="/images/loginOut.png" style="margin-left: 15px;">
				</a>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function updatePassword(){
	layer.open({
		  type: 1,
		  title:["修改密码","font-size:18px"],
		  skin: 'layui-layer-rim', //加上边框
		  area: ['600px', '500px'], //宽高
		  content: $('#updatePasswordDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		  success: function(){
		  },
		  btn:['提交','取消'],
		  btn1:function(index){
				//表单校验通过，手动校验两次输入是否一致
				var v1 = $("#txtNewPass").val();
				var v2 = $("#txtRePass").val();
				if(v1 == v2){
					//两次输入一致，发送ajax请求
					$.post("/user/updatePassword",{"password":v1},function(data){
						if(data == '1'){
							//修改成功，关闭修改密码窗口
							layer.msg('修改成功',{
								icon: 1,
								time:1000
								},function(){
									window.location.href="/login.jsp";
								});
						}else{
							//修改密码失败，弹出提示
							layer.msg('修改失败',{
								icon: 1,
								time:1000
								});
							layer.close(index);
						}
					});
				}else{
					//两次输入不一致，弹出错误提示
					layer.msg('两次输入不一致',{
						icon: 1,
						time:1000
						});
				}

			},
		  btn2:function(){
				
			}
		});
}
</script>
<!-- 修改密码的表单 -->
<div id="updatePasswordDiv" style="display:none;overflow: hidden;padding-top: 60px">
	<!--水平表单-->
	<form class="form-horizontal" action="" method="post" name="updatePasswordForm">
		<!--当屏幕小于768时，变为两行-->
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>新密码：</label>
			</div>
			<div class="col-sm-7">
				<input type="text"  class="form-control" value="" id="txtNewPass">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3 control-label">
				<label>确认密码：</label>
			</div>
			<div class="col-sm-7">
				<input type="text"   class="form-control" value="" id="txtRePass">
			</div>
		</div>
	</form>
	
</div>