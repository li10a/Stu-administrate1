<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学生管理系统用户登录</title>
<link href="css/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery1.8.1.min.js"></script>
<!-- <script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script> -->
<script type="text/javascript">
$(document).ready(function(){
	var $tab_li = $('#tab ul li');
	$tab_li.hover(function(){
		$(this).addClass('selected').siblings().removeClass('selected');
		var index = $tab_li.index(this);
		$('div.tab_box > div').eq(index).show().siblings().hide();
	});	
});
</script>
<script type="text/javascript">
function submit() {
	if ($('#sec_username_hide').val() == null || $('#sec_username_hide').val() == '') {
		alert('请输入账号');
		return false;
	}
	if ($('#sec_password_hide').val() == null || $('#sec_username_hide').val() == '') {
		alert('请输入密码');
		return false;
	}
	document.sec_login_form.submit();
}
</script>
</head>

<body>
<div id="tab">
  <ul class="tab_menu">
    <li class="selected">学生登录</li>
    <li>导师登录</li>
    <li>教务登录</li>
  </ul>
  <div class="tab_box"> 
    <div>
      <div class="stu_error_box"></div>
      <form id="stu_login_form" action="/doLogin" method="post" class="sec_login_error" target="loginfrm" onsubmit="return false;">
      	<input type="hidden" name="type" value="S" />
        <div id="username">
          <label>学&nbsp;&nbsp;&nbsp;号：</label>
          <input type="text" id="stu_username_hide" name="id"/>
        </div>
        <div id="password">
          <label>密&nbsp;&nbsp;&nbsp;码：</label>
          <input type="password" id="stu_password_hide" name="password"/>
        </div>
        <div id="remember">
          <input type="checkbox" name="remember" />
          <label>记住密码</label>
        </div>
        <div id="login">
          <button type="submit">登录</button>
        </div>
      </form>
    </div>
    <div class="hide">
     <div class="tea_error_box"></div>
      <form id="tea_login_form" action="/doLogin" method="post" class="sec_login_error" target="loginfrm" onsubmit="return false;">
        <input type="hidden" name="type" value="T" />
        <div id="username">
          <label>教工号：</label>
          <input type="text" id="tea_username_hide" name="id" />
        </div>
        <div id="password">
          <label>密&nbsp;&nbsp;&nbsp;码：</label>
          <input type="password" id="tea_password_hide" name="password" />
        </div>
        <div id="remember">
          <input type="checkbox" name="remember" />
          <label>记住密码</label>
        </div>
        <div id="login">
          <button type="submit">登录</button>
        </div>
      </form>
    </div>
    <div class="hide">
    <div class="sec_error_box"></div>
      <form id="sec_login_form" action="/doLogin" method="post" class="sec_login_error" target="loginfrm" onsubmit="return false;">
      	<input type="hidden" name="type" value="A" />
        <div id="username">
          <label>教务号：</label>
          <input type="text" id="sec_username_hide" name="id"/>
        </div>
        <div id="password">
          <label>密&nbsp;&nbsp;&nbsp;码：</label>
          <input type="password" id="sec_password_hide" name="password"/>
        </div>
        <div id="remember">
          <input type="checkbox" name="remember" />
          <label>记住密码</label>
        </div>
        <div id="login">
          <button type="submit" onclick="submit()">登录</button>
        </div>
      </form>
    </div>
  </div>
</div>
<iframe name="loginfrm" style="display:none"></iframe>
</body>
</html>
