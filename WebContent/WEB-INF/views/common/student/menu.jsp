<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
      <div class="menu col-md-2" style="padding: 0;">
        <div class="panel-group" id="accordion"><!--大容器-->

          <div class="panel panel-default"><!--这个表示第一个整块儿的-->
              <div class="panel-heading"><!--第一部分，被点击部分-->
                <h4 class="panel-title">
                  <a href="#collapseOne" data-toggle="collapse" >学习管理</a>
                </h4>
              </div>
              <div class="panel-collapse collapse in" id="collapseOne"><!--第二部分，折叠部分-->
                <div class="panel-body" style="padding-left: 30px;">
                 		<a href="${pageContext.request.contextPath}/student/courseList?studentId=1">课程管理</a> 
                </div>
              </div>
          </div>
        </div>
      </div>