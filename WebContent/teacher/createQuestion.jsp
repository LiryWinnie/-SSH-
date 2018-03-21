<%@ page language="java" contentType="text/html; charset=utf-8" import="cn.test.action.TeacherAction"
    import="java.util.List" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head> 
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>创建题库</title>  	
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
   <div class="ExamList">
   <h3 align="center">创建题库</h3>
   <table border="1" cellspacing="0" align="center">
     <tr>
       <th width="250px">考试科目</th>
	   <th width="150px">考试名称</th>
	   <th width="150px">考试时间</th>
       <th width="100px">添加题目</th>
       <th width="100px">提交考试</th>
       <th width="100px">查看题库</th>
     </tr>
     <s:iterator value="#exams" var="e">
     <tr>
       <td><s:property value="#e.course"/></td>
       <td><s:property value="#e.ename"/></td>
       <td><s:property value="#e.time"/></td>
       <td><a href="<%=request.getContextPath()%>/findexam_edit.action?eid=<s:property value="#e.eid"/>">添加题目</a></td>
       <td><a href="<%=request.getContextPath()%>/findexam_pub.action?eid=<s:property value="#e.eid"/>">发布</a></td>
       <td><a href="<%=request.getContextPath()%>/findexam_que.action?eid=<s:property value="#e.eid"/>">查看题库</a></td>
     </tr>
     </s:iterator>
   </table>
   </div>
</body>
</html>