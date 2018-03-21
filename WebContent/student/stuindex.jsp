<%@ page language="java" contentType="text/html;charset=utf-8" errorPage="exception.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head> 
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>在线考试系统</title>  	
<link href="<%=request.getContextPath()%>/css/student.css" rel="stylesheet" type="text/css" >
</head> 
<body>
   <div class="top">
      <img src="<%=request.getContextPath()%>/images/logo.png" /> &nbsp;<a>在线考试系统</a>
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
	<div class="info">
	    <p align="center"><img src="<%=request.getContextPath()%>/images/info.jpg" width="300px"></p>
	    <table border="1" cellspacing=0>
	    <tr>
	       <th>学号</th>
	       <td align="center">${sessionScope.student.getSnumber()}</td>
	    </tr>
	    <tr>
	       <th>姓名</th>
	       <td align="center">${sessionScope.student.getSname()}</td>
	    </tr>
	    <tr>
	       <th>性别</th>
	       <td align="center">${sessionScope.student.getSex()}</td>
	    </tr>
	    <tr>
	       <th>班级</th>
	       <td align="center">${sessionScope.student.getSclass()}</td>
	    </tr>
	    </table>
	</div>	
   </body>
</html>