<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head> 
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>创建考试</title>  	
<link href="<%=request.getContextPath()%>/css/teacher.css" rel="stylesheet" type="text/css" >
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
   <div class="info">
   <s:form action="create_exam" method="post" namespace="/">
	<p align="center"><img src="../images/createxam.jpg" ></p>
	<s:actionerror/>
	  <table border="1" cellspacing="0" align="center">
	  <tr>
	  <th>考试科目</th>
	  <td><input type="text" name="course"></td>
	  </tr>
	  <tr>
	  <th>考试名称</th>
	   <td><input type="text" name="ename"></td>
	   </tr>
	   <tr>
	  <th>考试时间</th>
	  <td><input type="text" name="time">分钟</td>
	  </table>
	  <br>
	  <p align="center"><input type="submit" name="sub" value="创建"></p>
	  </s:form>
	  </div>
	  <br>
   </body>
</html>