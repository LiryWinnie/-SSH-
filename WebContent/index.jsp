<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>在线考试系统</title>
		<link href="css/index.css" rel="stylesheet" type="text/css">
	</head>
	<body>
	<div class="form">
       <br>
       <center><h1>在线考试系统</h1>  
       <br>
           <table width="300" border="0" cellpadding="0" cellspacing="0">
				
				<tr>
					<td height="50" align="center" bgcolor="58afad">
						<a href="student/loginExam.jsp">学生登录</a>
					</td>
				</tr>
			</table>
			<br>
			<table width="300" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="50" align="center" bgcolor="58afad">
						<a href="teacher/tealogin.jsp">老师登录</a>
					</td>
				</tr>
			</table>
			<br>
			<table width="300" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="50" align="center" bgcolor="58afad">
						<a href="dean/deaLogin.jsp">院长登录</a>
					</td>
				</tr>
			</table>
			<br>
			<table width="300" border="0" cellpadding="0" cellspacing="0">
				<tr>
				    <td height="50" align="center" bgcolor="58afad">	
						<a href="admin/login.jsp">admin登录</a>
					</td>
				</tr>
			</table>
   </div>
	</body>
</html>
