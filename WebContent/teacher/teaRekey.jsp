<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>修改密码</title>  	
<link href="<%=request.getContextPath()%>/css/student.css" rel="stylesheet" type="text/css" >
</head>
<body>
<div class="top">
      <img src="<%=request.getContextPath()%>/images/logo.png" /> &nbsp;<a>在线考试系统</a>
      <div class="top_left">
         <a>${sessionScope.teacher.getTname()}</a>&nbsp;
         <a>|&nbsp;</a>
         <a href="<%=request.getContextPath()%>">退出</a>
      </div>
   </div>
   <div class="menu_left">
     <nav id="main-navigation" class="clearfix">
       <ul>
           <li><a href="<%=request.getContextPath()%>/teacher/teaindex.jsp">首页</a></li>
           <li><a href="<%=request.getContextPath()%>/teacher/create_exam.jsp">创建考试</a></li>
           <li><a href="<%=request.getContextPath()%>/findexam.action">创建题库</a></li>
           <li><a href="<%=request.getContextPath()%>/findexam_unaud.action">待审核考试</a></li>
           <li><a href="<%=request.getContextPath()%>/findexam_doing.action">在进行的考试</a></li>
           <li><a href="<%=request.getContextPath()%>/findexam_history.action">历史考试</a></li>
           <li><a href="<%=request.getContextPath()%>/findexam_unpass.action">未通过考试</a></li>
           <li><a href="<%=request.getContextPath()%>/teacher/teaRekey.jsp">修改密码</a></li>
   
       </ul>
     </nav>
   </div>
   <div class="tu"><a><img src="<%=request.getContextPath()%>/images/rekey.png" width="430px"></a></div>
   
   <div class="mima">
   <s:form action="rekey_tea" method="post" namespace="/">
      <s:actionerror/>
       <p>&emsp;&ensp;原密码：&emsp;<input type="password" name="psone" /></p>
       <p>&emsp;&ensp;新密码：&emsp;<input type="password" name="pstwo" /></p>
       <p>&ensp;确认密码：&emsp;<input type="password" name="psthree" /></p>
       <p align="center"><input type="submit" value="确认"/></p>
       
   </s:form>
   </div>
</body>
</html>