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

    <title>学生管理系统 - 添加作业</title>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/dashboard.css" rel="stylesheet" />
    <script src="/js/ie-emulation-modes-warning.js"></script>
    <script>
    function addHomework() {
		document.addHomeworkForm.submit();
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
            <li><a href="/teacher/classList">班级管理</a></li>
            <li class="active"><a href="/teacher/homeworkList">作业管理 <span class="sr-only">(current)</span></a></li>
            <li><a href="/teacher/studentList">学生用户管理</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">添加作业</h2>
          <div class="table-responsive">
          <form name="addHomeworkForm" class="form-horizontal" action="/teacher/addHomework" method="post" target="submitfrm">
          <input type="hidden" name="classNo" value="${classInfo.no}">
          <table class="table table-bordered">
		    <tbody>
		      <tr>
		        <td><label class="control-label" for="input01">班级名称: </label></td>
		        <td><input type="text" class="input-xlarge" value="${classInfo.name}" disabled></td>
		      </tr>
		      <tr>
		        <td><label class="control-label" for="input01">标题： </label></td>
		        <td><input name="title" type="text" class="input-xlarge"></td>
		      </tr>
		      <tr>
		        <td><label class="control-label" for="input01">作业内容： </label></td>
		         <td><textarea name="task" rows="10" cols="80"> </textarea></td>
		      </tr>
		    </tbody>
		  </table>
		  </form>
		  </div>
		  <div class="text-left">
            <button type="button" class="btn btn-primary" onclick="addHomework()">添加</button>
          </div>
        </div>
      </div>
    </div>

    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <iframe name="submitfrm" style="display:none"></iframe>
  </body>
</html>
