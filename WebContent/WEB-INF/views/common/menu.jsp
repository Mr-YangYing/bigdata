<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
      <div class="menu col-md-2" style="padding: 0;">
        <div class="panel-group" id="accordion"><!--大容器-->
		<!-- 主任 -->
		<shiro:hasPermission name="director">
          <div class="panel panel-default"><!--这个表示第一个整块儿的-->
              <div class="panel-heading"><!--第一部分，被点击部分-->
                <h4 class="panel-title">
                  <a href="#collapseOne" data-toggle="collapse" data-parent="#accordion">基础功能管理</a>
                </h4>
              </div>
              <div class="panel-collapse collapse in" id="collapseOne"><!--第二部分，折叠部分-->
                <div class="panel-body" style="padding-left: 30px;">
                 		<a href="${pageContext.request.contextPath}/teacher/list" style="width: 100%;height: 100px">教师管理</a> 
                </div>
                <div class="panel-body" style="padding-left: 30px;">
                		<a href="${pageContext.request.contextPath}/student/list">学生管理</a> 
                </div>
                <div class="panel-body" style="padding-left: 30px;">
                		<a href="${pageContext.request.contextPath}/course/list">实验课程管理</a> 
                </div>
                <div class="panel-body" style="padding-left: 30px;">
                		<a href="${pageContext.request.contextPath}/laboratory/list">实验室管理</a> 
                </div>
                <div class="panel-body" style="padding-left: 30px;">
                		<a href="${pageContext.request.contextPath}/schedule/list">课表管理</a> 
                </div>
              </div>
          </div>
          </shiro:hasPermission>
          <!-- 教师 -->
          <shiro:hasPermission name="teacher">
           <div class="panel panel-default"><!--这个表示第一个整块儿的-->
              <div class="panel-heading"><!--第一部分，被点击部分-->
                <h4 class="panel-title">
                  <a href="#collapseTwo" data-toggle="collapse" data-parent="#accordion">学习任务</a>
                </h4>
              </div>
              <div class="panel-collapse collapse in" id="collapseTwo"><!--第二部分，折叠部分-->
                <div class="panel-body" style="padding-left: 30px;">
                 		<a href="${pageContext.request.contextPath}/task/courseList">任务管理</a> 
                </div>
                <div class="panel-body" style="padding-left: 30px;">
                 		<a href="${pageContext.request.contextPath}/classes/getClassesByTeacherId?teacherId=1">成绩管理</a> 
                </div>
              </div>
          </div>
          <div class="panel panel-default"><!--这个表示第二个整块儿的-->
              <div class="panel-heading"><!--第一部分，被点击部分-->
                <h4 class="panel-title">
                  <a href="#collapseThree" data-toggle="collapse" data-parent="#accordion">教学资源</a>
                </h4>
              </div>
              <div class="panel-collapse collapse in" id="collapseThree"><!--第二部分，折叠部分-->
                <div class="panel-body" style="padding-left: 30px;">
                		<a href="${pageContext.request.contextPath}/resource/resourceList?courseType=其他课程">资源管理</a> 
                </div>
              </div>
          </div>
          </shiro:hasPermission>
          <!-- 学生 -->
          <shiro:hasPermission name="student">
          <div class="panel panel-default"><!--这个表示第一个整块儿的-->
              <div class="panel-heading"><!--第一部分，被点击部分-->
                <h4 class="panel-title">
                  <a href="#collapseFour" data-toggle="collapse" data-parent="#accordion">学习管理</a>
                </h4>
              </div>
              <div class="panel-collapse collapse in" id="collapseFour"><!--第二部分，折叠部分-->
                <div class="panel-body" style="padding-left: 30px;">
                 		<a href="${pageContext.request.contextPath}/student/courseList?studentId=1">课程管理</a> 
                </div>
              </div>
          </div>
          </shiro:hasPermission>
          <!-- 管理员 -->
          <shiro:hasPermission name="admin">
          <div class="panel panel-default"><!--这个表示第一个整块儿的-->
              <div class="panel-heading"><!--第一部分，被点击部分-->
                <h4 class="panel-title">
                  <a href="#collapseFive" data-toggle="collapse" data-parent="#accordion">系统管理</a>
                </h4>
              </div>
              <div class="panel-collapse collapse in" id="collapseFive"><!--第二部分，折叠部分-->
                <div class="panel-body" style="padding-left: 30px;">
                 		<a href="${pageContext.request.contextPath}/permission/list">权限管理</a> 
                </div>
                <div class="panel-body" style="padding-left: 30px;">
                 		<a href="${pageContext.request.contextPath}/role/list">角色管理</a> 
                </div>
                <div class="panel-body" style="padding-left: 30px;">
                 		<a href="${pageContext.request.contextPath}/user/list">用户管理</a> 
                </div>
              </div>
          </div>
          </shiro:hasPermission>
        </div>
      </div>