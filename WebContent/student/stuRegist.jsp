<%@ page language="java" contentType="text/html;charset=UTF-8" errorPage="exception.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>在线考试系统</title>
		<link href="../css/index.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/md5.js"></script>
		 <script>  
    function jumpToLogin(){  
    document.location.href="loginExam.jsp";  
    }  
    </script>
	</head>
	<body>
	<div class="form">
       <br>
       <center><h1>在线考试系统</h1> 
       <br><br><br>
       <s:form action="student_regist" method="post" namespace="/">
       <h3><s:actionerror/></h3>
       <p>学号 ：<input type="text"  name="snumber" size="16"></p>
       <p>姓名 ：<input type="text" name="sname" size="16"></p>
       <p>性别 ：<input type="text" name="sex" size="16"></p>
       <p>班级 ：<input type="text" name="sclass" size="16"></p>
       <p>密码 ：<input type="password" name="spassword" size="16"></p>
       <p><input type="submit" name="B1" value="提交"  onclick="javascript:spassword.value=hex_md5(spassword.value);">&nbsp;&nbsp;&nbsp;
                <input type="reset" name="B2" value="重置"></p>
                <input type="button" value="返回登录" onclick="jumpToLogin()"/>
       
    </s:form>
  </div>
  </body>
</html>