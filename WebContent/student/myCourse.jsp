<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
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
   <div class="search">
       <s:form action="course_search" method="post" namespace="/">
           <a>根据课程名称进行搜索：</a>
           <input type="text" name="course"/>
           <input type="submit" value="搜索"/>
       </s:form>
   </div>   
   <div class="ExamList">
      <h3>已选课程</h3>
       <table border="1" cellspacing=0>
       <tr>
           <th width="200px">课程</th>
           <th width="200px">老师</th>
       </tr>
       <s:iterator value="#course" var="c">
       <tr>
           <td width="200px"><s:property value="#c.course"/></td>
           <td width="200px"><s:property value="#c.tname"/></td>
       </tr>
       </s:iterator>
       </table>
   </div>
</body>
</html>