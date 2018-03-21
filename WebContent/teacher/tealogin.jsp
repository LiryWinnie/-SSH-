<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>教师登录</title>
		<link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" type="text/css">
		 
	</head>
	<body>
	<div class="form">
       <br>
       <center><h1>在线考试系统</h1> 
       <br><br><br>
       <s:form action="teacher_login" method="post" namespace="/">
       <s:actionerror/>
       <p>工号 ：<input type="text"  name="tnumber" size="16"></p>
       <p>密码 ：<input type="password" name="tpassword" size="16"></p>
       <p><input type="submit" name="B1" value="提交">&nbsp;&nbsp;&nbsp;
                <input type="reset" name="B2" value="重置"></p>
       
    </s:form>
  </div>
  </body>
</html>