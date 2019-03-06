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

    <title>学生管理系统 - 添加学生用户</title>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/dashboard.css" rel="stylesheet" />
    <script src="/js/ie-emulation-modes-warning.js"></script>
    <script>
    function addStudent() {
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
            <li><a href="/admin/classList">教师用户管理</a></li>
            <li class="active"><a href="/admin/teacherList">学生用户管理 <span class="sr-only">(current)</span></a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">添加学生用户</h2>
          <div class="table-responsive">
          <form name="addStudentForm" class="form-horizontal" action="/admin/addTeacher" method="post" target="submitfrm">
          <div style="width:700px;">
          <table class="table table-bordered" >
            <tbody>
		      <tr>
		        <td><label class="control-label">用户名: </label></td>
		        <td style="width:20%"><input type="text" name="name" class="input-xlarge"></td>
		        <td><label class="control-label">班级: </label></td>
		        <td>
					<select name="classNo">
		        		<option value="11">计算机A班</option>
		        		<option value="222">计算机B班</option>
		        	</select>
				</td>
				<td rowspan="3" style="width:20%"><label class="control-label"> </label></td>
		      </tr>
		      <tr>
		        <td><label class="control-label">账号: </label></td>
		        <td><input type="text" name="id" placeholder="" class="input-xlarge"></td>
		        <td><label class="control-label">初始密码: </label></td>
		        <td><input type="text" name="password" placeholder="" class="input-xlarge"></td>
		      </tr>
		      <tr>
		        <td><label class="control-label">用户名: </label></td>
		        <td><input type="text" name="name" class="input-xlarge"></td>
		        <td><label class="control-label">性别: </label></td>
		        <td style="width:10%;padding-top:10px">
		        	<select name="sex">
		        		<option value="男">男</option>
		        		<option value="女">女</option>
		        	</select>
		        </td>
		      </tr>
		      <tr>
		        <td><label class="control-label">年龄: </label></td>
		        <td><input type="text" name="age" class="input-xlarge"></td>
		        <td><label class="control-label">身份证号: </label></td>
		        <td><input type="text" name="idcardNo" class="input-xlarge"></td>
		        <td rowspan="3" style="border-bottom-style:none"><label class="control-label"> </label></td>
		      </tr>
		      <tr>
		        <td><label class="control-label">学号: </label></td>
		        <td><input type="text" name="membershipNo" class="input-xlarge"></td>
		        <td><label class="control-label">邮箱: </label></td>
		        <td><input type="text" name="email" class="input-xlarge"></td>
		      </tr>
		      <tr>
		        <td><label class="control-label">电话号码: </label></td>
		        <td><input type="text" name="phoneNo" placeholder="" class="input-xlarge"></td>
				<td><label class="control-label">携带电话: </label></td>
		        <td><input type="text" name="telephoneNo" class="input-xlarge"></td>
		      </tr>
		      <tr>
		        <td><label class="control-label">QQ号码: </label></td>
		        <td><input type="text" name="qqNo" class="input-xlarge"></td>
		        <td><label class="control-label">地址: </label></td>
		        <td><input type="text" name="address" placeholder="" class="input-xlarge"></td>
		        <td style="border-top-style:none"> </td>
		      </tr>
		    </tbody>
		  </table>
		  </div>
		  </form>
		  </div>
		  <div class="text-left">
            <button type="button" class="btn btn-primary" onclick="addTeacher()">添加</button>
          </div>
        </div>
      </div>
    </div>

    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <iframe name="submitfrm" style="display:none"></iframe>
  </body>
</html>
