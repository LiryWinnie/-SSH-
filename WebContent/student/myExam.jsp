<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
   <div class="ExamList">
       <p align="center"><img src="<%=request.getContextPath()%>/images/myexam.jpg" width="250px"></p>
       <table border="1" cellspacing=0>
           <tr>
              <th width="250px">考试科目</th>
              <th width="100px">考试名称</th>
              <th width="150px">考试时间</th>
              <th width="100px">考试状态</th>
              <th width="100px">出题老师</th>
              <th width="100px">参加考试</th>
           </tr>
           <s:iterator value="#exams" var="e">
           <tr>
              <td><s:property value="#e.course"/></td>
              <td><s:property value="#e.ename"/></td>
              <td><s:property value="#e.time"/></td>
              <td><s:property value="#e.estatus"/></td>
              <td><s:property value="#e.tname"/></td>
              <td><a href="<%=request.getContextPath()%>/studentexam_join.action?eid=<s:property value="#e.eid"/>">参加考试</td>
          </tr>
          </s:iterator>
       </table>
   </div>

</body>
</body>
</html>