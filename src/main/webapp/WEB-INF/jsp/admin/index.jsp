<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>教务管理</title>
<link href="css/StudentStyle.css" rel="stylesheet" type="text/css" />
<link href="css/ks.css" rel="stylesheet" type="text/css" />
<link href="js/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
<script src="js/jquery1.8.1.min.js" type="text/javascript"></script>
<script src="js/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="js/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
<script src="js/Common.js" type="text/javascript"></script>
<script src="js/Data.js" type="text/javascript"></script>
<script src="js/changeOption.js" type="text/javascript"></script>
<script src="js/rl.js" type="text/javascript"></script>
<script type="text/javascript">
    //退出
    function loginOut() {
        if (confirm("确定退出吗？")) {
            StudentLogin.loginOut(function (data) {
                if (data == "true") {
                    window.location = "/Login.aspx";
                }
                else {
                    jBox.alert("退出失败！", "提示", new { buttons: { "确定": true} });
                }
            });
        }
    }
</script>

</head>
<body>
<div class="banner">
    <div class="bgh">
        <div class="page">910513201419学员：邹智，欢迎您！ <a href="MyInfo/Index.aspx.html">我的信息</a>
        <a onclick="loginOut()"href="javascript:">安全退出</a>
        </div>
    </div>
</div>
<div class="page">
    <div class="box mtop">
        <div class="leftbox">
            <div class="l_nav2">
                <div class="ta1">
                    <strong>管理中心</strong>
                    <div class="leftbgbt">
                    </div>
                </div>
                <div class="cdlist">
                    <div>
                        <a href="">教师信息</a>
                    </div>
                    <div>
                        <a href="">学生信息 </a>
                    </div>
                    <div>
                        <a href="">班级信息 </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="rightbox">
            
<h2 class="mbx">管理中心&nbsp;&nbsp;&nbsp;&nbsp;</h2>
</body>
</html>