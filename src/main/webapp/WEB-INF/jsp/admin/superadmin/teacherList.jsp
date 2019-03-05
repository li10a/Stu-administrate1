<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://v3.bootcss.com/favicon.ico">

    <title>学生管理系统 - 教师情报</title>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/dashboard.css" rel="stylesheet" />
    <script src="/js/ie-emulation-modes-warning.js"></script>
    <script>
	function goAddTeacherForm() {
		location.href="/admin/addTeacherForm";
	}
	function deleteTeacher(teacherNo) {
		if (confirm("确认删除吗?")) {
			location.href="/admin/deleteTeacher?no=" + teacherNo;
		}
	}
	function goModifyTeacherForm(teacherNo) {
		location.href="/admin/modifyTeacherForm?no=" + teacherNo;
	}
    </script>
  </head>

  <body>

	<!-- nav -->
	<c:import url="/WEB-INF/jsp/common/nav.jsp" charEncoding="UTF-8"/>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="/admin/classList">班级管理</a></li>
            <li class="active"><a href="/admin/teacherList">教师用户管理<span class="sr-only">(current)</span></a></li>
            <li><a href="#">学生用户管理</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">教师列表<div class="pull-right"><button type="button" class="btn btn-primary" onclick="goAddTeacherForm()">教师添加</button></div></h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>教师名字</th>
                  <th>教师ID</th>
                  <th>班级管理</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
              	<c:forEach items="${teacherList}" var="teacher">
              	  <tr>
              	    <td>${teacher.no }</td>
              	    <td>${teacher.name }</td>
              	    <td>${teacher.id }</td>
                    <td>${teacherClasses[teacher.id] }</td>
                    <td>
                    	<button type="button" class="btn btn-default" onclick="goModifyTeacherForm(${teacher.no})">编辑</button>
                    	<button type="button" class="btn btn-default" onclick="deleteTeacher(${teacher.no})">删除</button>
                    </td>
                  </tr>
              	</c:forEach>
              </tbody>
            </table>
          </div>

          <c:import url="/WEB-INF/jsp/common/pagination.jsp" charEncoding="UTF-8">
          	<c:param name="url">teacherList</c:param>
          </c:import>

        </div>
      </div>
    </div>

    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </body>
</html>
