
//配置班级课程表
function configureSchedule(){
	layer.open({
		  type: 2,//iframe
		  title:["课程表创建","font-size:18px"],
		  skin: 'layui-layer-rim', //加上边框
		  area: ['800px', '600px'], //宽高
		  content:'/schedule/configureSchedule', 
		  success: function(){
		  },
		  btn:['提交','取消'],
		  btn1:function(index){
			layer.msg('创建成功',{
				icon: 1,
				time:1000
				});
			layer.close(index);
			},
		  btn2:function(){
				
			}
		});
}

