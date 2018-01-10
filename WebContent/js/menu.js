$(function(){
	//折叠菜单show时触发
	$('#collapseOne').on('show.bs.collapse', function () {
		$("#updateSpanOne").text("-");
	});
	//折叠菜单hide时触发
	$('#collapseOne').on('hide.bs.collapse', function () {
		$("#updateSpanOne").text("+");
	});
	//折叠菜单show时触发
	$('#collapseTwo').on('show.bs.collapse', function () {
		$("#updateSpanTwo").text("-");
	});
	//折叠菜单hide时触发
	$('#collapseTwo').on('hide.bs.collapse', function () {
		$("#updateSpanTwo").text("+");
	});
	//折叠菜单show时触发
	$('#collapseThree').on('show.bs.collapse', function () {
		$("#updateSpanThree").text("-");
	});
	//折叠菜单hide时触发
	$('#collapseThree').on('hide.bs.collapse', function () {
		$("#updateSpanThree").text("+");
	});
	//折叠菜单show时触发
	$('#collapseFour').on('show.bs.collapse', function () {
		$("#updateSpanFour").text("-");
	});
	//折叠菜单hide时触发
	$('#collapseFour').on('hide.bs.collapse', function () {
		$("#updateSpanFour").text("+");
	});
	//折叠菜单show时触发
	$('#collapseFive').on('show.bs.collapse', function () {
		$("#updateSpanFive").text("-");
	});
	//折叠菜单hide时触发
	$('#collapseFive').on('hide.bs.collapse', function () {
		$("#updateSpanFive").text("+");
	});
})