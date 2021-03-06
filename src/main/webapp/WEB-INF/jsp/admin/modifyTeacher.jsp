<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://v3.bootcss.com/favicon.ico">

    <title>学生管理系统 - 编辑教师用户</title>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/dashboard.css" rel="stylesheet" />
    <script src="/js/ie-emulation-modes-warning.js"></script>
    <script>
    function modifyTeacher() {
		document.addTearcherForm.submit();
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
            <li class="active"><a href="/admin/teacherList">教师用户管理 <span class="sr-only">(current)</span></a></li>
            <li><a href="/admin/studentList">学生用户管理</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">编辑教师用户</h2>
          <div class="table-responsive">
          <form name="addTearcherForm" class="form-horizontal" action="/admin/modifyTeacher" method="post" target="submitfrm">
          <input type="hidden" name="no" value="${teacherInfo.no }" />
          <table class="table table-bordered">
		    <tbody>
		      <tr>
		        <td><label class="control-label">用户名: </label></td>
		        <td><input type="text" name="name" class="input-xlarge" value="${teacherInfo.name }"></td>
		      </tr>
		      <tr>
		        <td><label class="control-label">账号: </label></td>
		        <td><input type="text" name="id" placeholder="" class="input-xlarge" value="${teacherInfo.id }"></td>
		      </tr>
		      <tr>
		        <td><label class="control-label">初始密码: </label></td>
		        <td><input type="text" name="password" placeholder="" class="input-xlarge" value="${teacherInfo.password }"></td>
		      </tr>
		    </tbody>
		  </table>
		  </form>
		  </div>
		  <div class="text-left">
            <button type="button" class="btn btn-primary" onclick="modifyTeacher()">更新</button>
          </div>
        </div>
      </div>
    </div>

    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <iframe name="submitfrm" style="display:none"></iframe>
  </body>
</html>
