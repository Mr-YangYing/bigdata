//禁用将表单元素数组或者对象序列化
jQuery.ajaxSettings.traditional = true; 
$(function(){
    //处理批量删除
	//全选操作
    $("#all").on("click",function(){
		$(".currentCheckbox").prop("checked",this.checked);	    	
    });
   
})


//批量导入
function batchUpload(domainName){
	layer.open({
		  type: 2,
		  title:["Excel导入","font-size:18px"],
		  skin: 'layui-layer-rim', //加上边框
		  area: ['600px', '520px'], //宽高
		  content:'/uploadFile/loadImportExcel?domainName='+domainName, 
		  /*btn:['提交','取消'],
		  btn1:function(index){
			  
			layer.msg('修改成功',{
				icon: 1,
				time:1000
				});
			layer.close(index);
			},
		  btn2:function(){
				
			}*/
		});
}