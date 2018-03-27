<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
      <div class="menu col-md-2" style="padding: 0;">
        <div class="panel-group" id="accordion"><!--大容器-->
		<!-- 主任 -->
		<shiro:hasPermission name="BasicFunctionManagement ">
          <div class="panel panel-default"><!--这个表示第一个整块儿的-->
              <div class="panel-heading"><!--第一部分，被点击部分-->
                <h4 class="panel-title">
                  <a href="#collapseOne" data-toggle="collapse" data-parent="#accordion">
                  	基础功能<span id="updateSpanOne" style="margin-left: 90px;font-size: 18px">-</span>
                  </a>
                </h4>
              </div>
              <div class="panel-collapse collapse in" id="collapseOne"><!--第二部分，折叠部分-->
              <shiro:hasPermission name="teacherManagement">
                <div class="panel-body" style="padding-left: 30px;">
                 		<a href="${pageContext.request.contextPath}/teacher/list">教师管理</a> 
                </div>
                </shiro:hasPermission>
                <shiro:hasPermission name="studentManagement">
                <div class="panel-body" style="padding-left: 30px;">
                		<a href="${pageContext.request.contextPath}/student/list">学生管理</a> 
                </div>
                </shiro:hasPermission>
                <shiro:hasPermission name="courseManagement">
                <div class="panel-body" style="padding-left: 30px;">
                		<a href="${pageContext.request.contextPath}/course/list">实验课程管理</a> 
                </div>
                </shiro:hasPermission>
                <shiro:hasPermission name="laboratoryManagement">
                <div class="panel-body" style="padding-left: 30px;">
                		<a href="${pageContext.request.contextPath}/laboratory/list">实验室管理</a> 
                </div>
                </shiro:hasPermission>
                <!-- 课表管理还没有添加 -->
<%--                 <shiro:hasPermission name="">
                <div class="panel-body" style="padding-left: 30px;">
                		<a href="${pageContext.request.contextPath}/schedule/list">课表管理</a> 
                </div>
                </shiro:hasPermission> --%>
              </div>
          </div>
          </shiro:hasPermission>
          <!-- 教师 -->
          <shiro:hasPermission name="studyTask">
           <div class="panel panel-default"><!--这个表示第一个整块儿的-->
              <div class="panel-heading"><!--第一部分，被点击部分-->
                <h4 class="panel-title">
                  <a href="#collapseTwo" data-toggle="collapse" data-parent="#accordion">
                  	学习任务<span id="updateSpanTwo" style="margin-left: 98px;font-size: 18px">-</span>
                  </a>
                </h4>
              </div>
              <div class="panel-collapse collapse in" id="collapseTwo"><!--第二部分，折叠部分-->
              <shiro:hasPermission name="taskManagement">
                <div class="panel-body" style="padding-left: 30px;">
                 		<a href="${pageContext.request.contextPath}/task/courseList?courseOpen=1">任务管理</a> 
                </div>
                </shiro:hasPermission>
                <shiro:hasPermission name="markManagement">
                <div class="panel-body" style="padding-left: 30px;">
                 		<a href="${pageContext.request.contextPath}/course/getCourseListByTeacherId?teacherId=${loginUser.id}">成绩管理</a> 
                </div>
                </shiro:hasPermission>
              </div>
          </div>
            </shiro:hasPermission>
            <shiro:hasPermission name="teachResource">
          <div class="panel panel-default"><!--这个表示第二个整块儿的-->
              <div class="panel-heading"><!--第一部分，被点击部分-->
                <h4 class="panel-title">
                  <a href="#collapseThree" data-toggle="collapse" data-parent="#accordion">
                  	教学资源<span id="updateSpanThree" style="margin-left: 98px;font-size: 18px">-</span>
                  </a>
                </h4>
              </div>
              <div class="panel-collapse collapse in" id="collapseThree"><!--第二部分，折叠部分-->
              <shiro:hasPermission name="resourceManagement">
                <div class="panel-body" style="padding-left: 30px;">
                		<a href="${pageContext.request.contextPath}/resource/resourceList?courseType=other">资源管理</a> 
                </div>
                </shiro:hasPermission>
              </div>
          </div>
          </shiro:hasPermission>
          <!-- 学生 -->
          <shiro:hasPermission name="studyManagement">
          <div class="panel panel-default"><!--这个表示第一个整块儿的-->
              <div class="panel-heading"><!--第一部分，被点击部分-->
                <h4 class="panel-title">
                  <a href="#collapseFour" data-toggle="collapse" data-parent="#accordion">
                  		学习管理<span id="updateSpanFour" style="margin-left: 98px;font-size: 18px">-</span>
                  </a>
                </h4>
              </div>
              <div class="panel-collapse collapse in" id="collapseFour"><!--第二部分，折叠部分-->
              <shiro:hasPermission name="studentsCourseManagement">
                <div class="panel-body" style="padding-left: 30px;">
                 		<a href="${pageContext.request.contextPath}/student/courseList?studentId=${loginUser.id}">课程管理</a> 
                </div>
                </shiro:hasPermission>
              </div>
          </div>
          </shiro:hasPermission>
          <!-- 管理员 -->
          <shiro:hasPermission name="systemManagement">
          <div class="panel panel-default"><!--这个表示第一个整块儿的-->
              <div class="panel-heading"><!--第一部分，被点击部分-->
                <h4 class="panel-title">
                  <a href="#collapseFive" data-toggle="collapse" data-parent="#accordion">
                  	系统管理<span id="updateSpanFive" style="margin-left: 98px;font-size: 18px">-</span>
                  </a>
                </h4>
              </div>
              <div class="panel-collapse collapse in" id="collapseFive"><!--第二部分，折叠部分-->
              	<shiro:hasPermission name="permissionManagement">
                <div class="panel-body" style="padding-left: 30px;">
                 		<a href="${pageContext.request.contextPath}/permission/list">权限管理</a> 
                </div>
                </shiro:hasPermission>
                <shiro:hasPermission name="roleManagement">
                <div class="panel-body" style="padding-left: 30px;">
                 		<a href="${pageContext.request.contextPath}/role/list">角色管理</a> 
                </div>
                </shiro:hasPermission>
                <shiro:hasPermission name="userManagement">
                <div class="panel-body" style="padding-left: 30px;">
                 		<a href="${pageContext.request.contextPath}/user/list">用户管理</a> 
                </div>
                </shiro:hasPermission>
              </div>
          </div>
          </shiro:hasPermission>
        </div>
      </div>
 