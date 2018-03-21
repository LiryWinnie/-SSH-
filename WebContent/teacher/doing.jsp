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
       <s:form action="findexam_*" method="post">
           <h3>正在进行的考试</h3><br><br>
           <table border="1" cellspacing="0">
           <tr>
           <th width="150px">考试名称</th>
           <th width="150px">考试科目</th>
           <th width="100px">已考试人数</th>
           <th width="100px">查看考试</th>
           <th width="100px">结束考试</th>
           </tr>
           <%int i=0; %>
           <s:iterator value="#exams" var="e">
           <tr>
              <td><s:property value="#e.ename"/></td>
              <td><s:property value="#e.course"/></td>
              <td>${sessionScope.number2.get(i)}<a>/</a>${sessionScope.number1.get(i)}</td>
              <td><a href="<%=request.getContextPath()%>/findexam_piy.action?course=<s:property value="#e.course"/>&ename=<s:property value="#e.ename"/>&eid=<s:property value="#e.eid"/>">查看</a></td>
              <td><a href="<%=request.getContextPath()%>/findexam_end.action?eid=<s:property value="#e.eid"/>">结束</a></td>
              <%i++; %>
           </tr>
           </s:iterator>
           </table>
       </s:form>
   </div>
</body>
</html>