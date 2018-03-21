<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>发布考试</title>  	
<link href="<%=request.getContextPath()%>/css/dean.css" rel="stylesheet" type="text/css" >
<script type="text/javascript">
  function deanform_pub()
  {
   document.forms[0].action = "deanform_pub.action";
   document.forms[0].submit(); 
  }
  function deanform_unpub()
  {
   document.forms[0].action = "deanform_unpub.action";
   document.forms[0].submit(); 
  }
 </script>
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
    <div class="info">
       <s:form method="post" namespace="/">
           <s:actionerror/>
               <p>考试科目：${sessionScope.e.getCourse()}</p>
               <p>考试名称：${sessionScope.e.getEname()}</p>
               <p>考试时间：${sessionScope.e.getTime()}</p>
               <p>难： ${sessionScope.bdegree.getDifficult()}</p>
               <p>中： ${sessionScope.bdegree.getMiddle()}</p>
               <p>易： ${sessionScope.bdegree.getEasy()}</p>
               <textarea name="comment"></textarea>
               <input type="button" value="发布" onclick="javascript:deanform_pub()">
               <input type="button" value="不发布" onclick="javascript:deanform_unpub()">
       </s:form>
   </div>
</body>
</html>