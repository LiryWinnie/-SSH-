<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
   <div class="ExamList">
   <form action="">
	<p align="center"><img src="<%=request.getContextPath()%>/images/historyexam.jpg" width="300px"></p>
	  <table border="1" cellspacing="0" align="center">
	  <tr>
	  <th width="250px">考试科目</th>
	  <th width="150px">考试名称</th>
	  <th width="150px">考试时间</th>
	  <th width="100px">考试状态</th>
	  <th width="100px">操作</th>
	  </tr>
	  <s:iterator value="#exams" var="e">
     <tr>
       <td><s:property value="#e.course"/></td>
       <td><s:property value="#e.ename"/></td>
       <td><s:property value="#e.time"/></td>
       <td><s:property value="#e.estatus"/></td>
       <td><a href="<%=request.getContextPath()%>/deanfind_lookhistory.action?course=<s:property value="#e.course"/>&ename=<s:property value="#e.ename"/>">查看</a></td>
     </tr>
	 </s:iterator>
	  
	  </table>
	  	</form>
	  </div>
</body>