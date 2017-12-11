<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
      <div class="menu col-md-2" style="padding: 0;">
        <div class="panel-group" id="accordion"><!--大容器-->

          <div class="panel panel-default"><!--这个表示第一个整块儿的-->
              <div class="panel-heading"><!--第一部分，被点击部分-->
                <h4 class="panel-title">
                  <a href="#collapseOne" data-toggle="collapse" >学习任务</a>
                </h4>
              </div>
              <div class="panel-collapse collapse in" id="collapseOne"><!--第二部分，折叠部分-->
                <div class="panel-body" style="padding-left: 30px;">
                 		<a href="${pageContext.request.contextPath}/task/courseList">任务管理</a> 
                </div>
              </div>
          </div>
          <div class="panel panel-default"><!--这个表示第二个整块儿的-->
              <div class="panel-heading"><!--第一部分，被点击部分-->
                <h4 class="panel-title">
                  <a href="#collapseTwo" data-toggle="collapse" >教学资源</a>
                </h4>
              </div>
              <div class="panel-collapse collapse in" id="collapseTwo"><!--第二部分，折叠部分-->
                <div class="panel-body" style="padding-left: 30px;">
                		<a href="${pageContext.request.contextPath}/resource/resourceList?courseType=其他课程">资源管理</a> 
                </div>
<!--                 <div class="panel-body" style="padding-left: 30px;">
                 		<a href="#">资源上传</a> 
                </div> -->
              </div>
          </div>
        </div>
      </div>