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

    <title>学生管理系统 - 作业情报</title>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/dashboard.css" rel="stylesheet" />
    <script src="/js/ie-emulation-modes-warning.js"></script>
    <script>
	function goModifyHomeworkForm(homeworkNo) {
		location.href="/teacher/modifyHomeworkForm?no=" + homeworkNo;
	}
	function deleteHomeworkForm(homeworkNo) {
		location.href="/teacher/deleteHomework?no=" + homeworkNo;
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
          <h2 class="sub-header">作业列表</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>班级名字</th>
                  <th>作业标题</th>
                  <th>已提交作业学生</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
              	<c:forEach items="${homeworkList}" var="homework">
              	  <tr>
              	    <td>${homework.no}</td>
              	    <td>${homework.className}</td>
                    <td>${homework.title}</td>
                    <td>
                    <c:choose>
                    	<c:when test="${homework.commitStudentCnt > 0}">
                    		<a href="/teacher/goCommitScoreForm?homeworkNo=${homework.no }">${homework.commitStudentCnt}</a>
                    	</c:when>
                    	<c:otherwise>
                    		${homework.commitStudentCnt}
                    	</c:otherwise>
                    </c:choose>
                    </td>
                    <td>
                    	<button type="button" class="btn btn-default" onclick="goModifyHomeworkForm(${homework.no})">作业编辑</button>
                    	<button type="button" class="btn btn-default" onclick="deleteHomeworkForm(${homework.no})">删除</button>
                    </td>
                  </tr>
              	</c:forEach>
              </tbody>
            </table>
          </div>

          <c:import url="/WEB-INF/jsp/common/pagination.jsp" charEncoding="UTF-8">
          	<c:param name="url">classList</c:param>
          </c:import>

        </div>
      </div>
    </div>

    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </body>
</html>
