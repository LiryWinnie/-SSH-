<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
   <div class="ques">
   <s:form action="teaQuestion_update" method="post" namespace="/">
        <s:actionerror/>
        <p>考试科目：${sessionScope.e.getCourse()}</p>
        <p>考试名称：${sessionScope.e.getEname()}</p>
        <p>考试时间：${sessionScope.e.getTime()}</p>
        <a>题目类型：${sessionScope.q.getQtype()}&emsp;<select name="qtype">
                       <option value ="填空题"> 填空题 </option>
                       <option value ="选择题"> 选择题 </option>
                       <option value ="判断题"> 判断题 </option>
                       <option value ="简答题"> 简答题 </option>
                   </select></a>       
        <a>&emsp;题目难易：${sessionScope.q.getQdegree()}&emsp;<select name="qdegree">
                       <option value ="难"> 难 </option>
                       <option value ="中"> 中 </option>
                       <option value ="易"> 易 </option>
                   </select></a><br>
        <p>题目：${sessionScope.q.getQtitle()}</p><a><textarea name="qtitle" onkeypress="newline()"></textarea></a><br>
        <p>题目内容：${sessionScope.q.getQcontent()}</p><p><textarea name="qcontent" onkeypress="newline()"></textarea></p>
        <p>题目答案：${sessionScope.q.getQanswer()}</p><p><input type="text" name="qanswer"></p>
        <p>题目分值：${sessionScope.q.getQscore()}</p><p><input type="text" name="qscore"></p>
        <input type="submit" value="提交">
   </s:form>
   </div>
</body>
</html>