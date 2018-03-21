<%@ page language="java" contentType="text/html;charset=gbk" errorPage="exception.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head> 
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>在线考试系统</title>  	
<link href="../css/student.css" rel="stylesheet" type="text/css" >
</head> 
<body>
   <div class="top">
      <img src="../images/logo.png" /> &nbsp;<a>在线考试系统</a>
      <div class="top_left">
         <a>${sessionScope.student.getSname()}</a>&nbsp;
         <a>|&nbsp;</a>
         <a href="<%=request.getContextPath()%>">退出</a>
      </div>
   </div>
   <div class="menu_left">
     <nav id="main-navigation" class="clearfix">
       <ul>
           <li><a href="<%=request.getContextPath()%>/student/stuindex.jsp">首页</a></li>
           <li><a href="<%=request.getContextPath()%>/studentexam_list.action">考试列表</a></li>
           <li><a href="<%=request.getContextPath()%>/studentexam_history.action">历史考试</a></li>
           <li><a href="<%=request.getContextPath()%>/course_list.action">我的课程</a></li>
           <li><a href="<%=request.getContextPath()%>/student/stuRekey.jsp">修改密码</a></li>
           
       </ul>
     </nav>
   </div>
</body>
</html>