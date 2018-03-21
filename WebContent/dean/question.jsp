<%@ page language="java" contentType="text/html;charset=gbk" errorPage="exception.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head> 
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>在线考试系统</title>  	
<link href="<%=request.getContextPath()%>/css/dean.css" rel="stylesheet" type="text/css" >
</head> 
<body>
   <div class="top">
      <img src="<%=request.getContextPath()%>/images/logo.png" /> &nbsp;<a>在线考试系统</a>
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
   <s:iterator value="#questions" var="q">
       <s:property value="#q.qtype"/>&emsp;&emsp;
       <s:property value="#q.qscore"/>&emsp;&emsp;
       <s:property value="#q.qdegree"/>
       <p>题目：<s:property value="#q.qtitle"/></p>
       <p>内容：<s:property value="#q.qcontent"/></p>
       <p>答案：<s:property value="#q.qanswer"/></p>
       <a href="<%=request.getContextPath()%>/deanQuestion_mod.action?qid=<s:property value="#q.qid"/>">修改题目</a>&emsp;&emsp;
       <a href="<%=request.getContextPath()%>/deanQuestion_del.action?qid=<s:property value="#q.qid"/>">删除题目</a><br><br>
   </s:iterator>
   </div>
</body>
</html>