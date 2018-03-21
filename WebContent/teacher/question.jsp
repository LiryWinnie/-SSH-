<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>在线考试系统</title>  	
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
   <s:iterator value="#questions" var="q">
       <s:property value="#q.qtype"/>&emsp;&emsp;
       <s:property value="#q.qscore"/>&emsp;&emsp;
       <s:property value="#q.qdegree"/>
       <p>题目：<s:property value="#q.qtitle"/></p>
       <p>内容：<s:property value="#q.qcontent"/></p>
       <p>答案：<s:property value="#q.qanswer"/></p>
       <a href="<%=request.getContextPath()%>/teaQuestion_mod.action?qid=<s:property value="#q.qid"/>">修改题目</a>&emsp;&emsp;
       <a href="<%=request.getContextPath()%>/teaQuestion_del.action?qid=<s:property value="#q.qid"/>">删除题目</a><br><br>
   </s:iterator>
   </div>
</body>
</html>