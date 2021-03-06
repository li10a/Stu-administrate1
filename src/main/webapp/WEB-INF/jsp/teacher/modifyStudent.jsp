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

    <title>学生管理系统 - 编辑学生用户</title>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/dashboard.css" rel="stylesheet" />
    <script src="/js/ie-emulation-modes-warning.js"></script>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script>
    $(function(){
		$('#choose-file').change(function(){
			var file = $('#choose-file').get(0).files[0];
			var reader = new FileReader();
			reader.readAsDataURL(file);
			reader.onload=function(e){
				$('#show-img').attr('src', e.target.result);
			}
		});
		$("#choose-file").hide();
		$("#box-1").bind('click', function(){
			$("#choose-file").click();
		});
    });
    function modifyStudent() {
		document.modifyStudentForm.submit();
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
            <li><a href="/teacher/homeworkList">作业管理</a></li>
            <li class="active"><a href="/teacher/studentList">学生用户管理 <span class="sr-only">(current)</span></a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">编辑学生用户</h2>
          <div class="table-responsive">
          <form name="modifyStudentForm" class="form-horizontal" action="/teacher/modifyStudent" method="post" target="submitfrm" enctype="multipart/form-data">
          <input name="no" type="hidden" value="${studentInfo.no }"/>
          <input name="name" type="hidden" value="${studentInfo.name }"/>
          <input name="id" type="hidden" value="${studentInfo.id }"/>
          <div style="width:800px;">
          <table class="table table-bordered" >
            <tbody>
		      <tr>
		        <td><label class="control-label">用户名: </label></td>
		        <td style="width:20%"><input type="text" class="input-xlarge" value="${studentInfo.name }" disabled></td>
		        <td><label class="control-label">班级: </label></td>
		        <td>
					<select name="classNo">
						<c:forEach items="${classList}" var="class1">
		        		<option value="${class1.no}" <c:if test="${class1.no eq studentInfo.classNo }">selected</c:if>>${class1.name}</option>
		        		</c:forEach>
		        	</select>
				</td>
				<c:set var="imageName">/images/noname.jpg</c:set>
				<c:if test="${studentInfo.image ne '' and not empty studentInfo.image}">
					<c:set var="imageName">${studentInfo.image}</c:set>
				</c:if>
				<td rowspan="3" style="width:20%" align="center"><div id="box-1"><img id="show-img" src="${imageName }" width="120px" height="120px"></div><input id="choose-file" type="file" name="file" ></td>
		      </tr>
		      <tr>
		        <td><label class="control-label">账号: </label></td>
		        <td><input type="text" placeholder="" class="input-xlarge" value="${studentInfo.id }" disabled></td>
		        <td><label class="control-label">初始密码: </label></td>
		        <td><input type="text" name="password" placeholder="" class="input-xlarge" value="${studentInfo.password }"></td>
		      </tr>
		      <tr>
		        <td><label class="control-label">年龄: </label></td>
		        <td><input type="text" name="age" class="input-xlarge" value="${studentInfo.age }"></td>
		        <td><label class="control-label">性别: </label></td>
		        <td style="width:10%;padding-top:10px">
		        	<select name="sex">
		        		<option value="男" <c:if test="${studentInfo.sex eq '男'}">selected</c:if>>男</option>
		        		<option value="女" <c:if test="${studentInfo.sex eq '女'}">selected</c:if>>女</option>
		        	</select>
		        </td>
		      </tr>
		      <tr>
		        <td><label class="control-label">学号: </label></td>
		        <td><input type="text" name="membershipNo" class="input-xlarge" value="${studentInfo.membershipNo }"></td>
		        <td><label class="control-label">身份证号: </label></td>
		        <td><input type="text" name="idcardNo" class="input-xlarge" value="${studentInfo.idcardNo }"></td>
		        <td rowspan="3" style="border-bottom-style:none"><label class="control-label"> </label></td>
		      </tr>
		      <tr>
		        <td><label class="control-label">QQ号码: </label></td>
		        <td><input type="text" name="qqNo" class="input-xlarge" value="${studentInfo.qqNo }"></td>
		        <td><label class="control-label">邮箱: </label></td>
		        <td><input type="text" name="email" class="input-xlarge" value="${studentInfo.email }"></td>
		      </tr>
		      <tr>
		        <td><label class="control-label">电话号码: </label></td>
		        <td><input type="text" name="phoneNo" placeholder="" class="input-xlarge" value="${studentInfo.phoneNo }"></td>
				<td><label class="control-label">携带电话: </label></td>
		        <td><input type="text" name="telephoneNo" class="input-xlarge" value="${studentInfo.telephoneNo }"></td>
		      </tr>
		      <tr>
		        <td><label class="control-label">地址: </label></td>
		        <td colspan="4"><input type="text" name="address" placeholder="" class="input-xlarge" size="80" value="${studentInfo.address }"></td>
		      </tr>
		    </tbody>
		  </table>
		  </div>
		  </form>
		  </div>
		  <div class="text-left">
            <button type="button" class="btn btn-primary" onclick="modifyStudent()">更新</button>
          </div>
        </div>
      </div>
    </div>

    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <iframe name="submitfrm" style="display:none"></iframe>
  </body>
</html>
