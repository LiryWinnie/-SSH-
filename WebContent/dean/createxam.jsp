<%@ page language="java" contentType="text/html;charset=utf-8" errorPage="exception.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head> 
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>在线考试系统</title>  	
<link href="<%=request.getContextPath()%>/css/dean.css" rel="stylesheet" type="text/css" >
</head> 
<body>
   <div class="top">
      <img src="../images/logo.png" /> &nbsp;<a>在线考试系统</a>
      <div class="top_left">
         <a>${sessionScope.dean.getDname()}</a>&nbsp;
         <a>|&nbsp;</a>
         <a href="<%=request.getContextPath()%>">退出</a>
      </div>
   </div>
   <div class="menu_left">
     <nav id="main-navigation" class="clearfix">
       <ul>
           <li><a href="<%=request.getContextPath()%>/dean/dean_index.jsp">首页</a></li>
           <li><a href="<%=request.getContextPath()%>/dean/createxam.jsp">创建考试</a></li>
           <li><a href="<%=request.getContextPath()%>/deanfind_list.action">创建题库</a></li>
           <li><a href="<%=request.getContextPath()%>/deanfind_unaud.action">未发布考试</a></li>
           <li><a href="<%=request.getContextPath()%>/deanfind_doing.action">正进行的考试</a></li>
           <li><a href="<%=request.getContextPath()%>/deanfind_history.action">历史考试</a></li>
           <li><a href="<%=request.getContextPath()%>/dean/deanRekey.jsp">修改密码</a></li>
       </ul>
     </nav>
   </div>
   <div class="info">
   <s:form action="dean_create" method="post" namespace="/">
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