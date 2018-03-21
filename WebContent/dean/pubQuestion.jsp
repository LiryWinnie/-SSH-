<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html;charset=gbk">
<title>发布考试</title>  	
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
   <div class="info">
       <s:form action="deanpub_exam" method="post" namespace="/">
           <s:actionerror/>
               <p>考试科目：${sessionScope.e.getCourse()}</p>
               <p>考试名称：${sessionScope.e.getEname()}</p>
               <p>考试时间：${sessionScope.e.getTime()}</p>
               <a>易：<select name="easy">
                       <option value ="0.1"> 10% </option>
                       <option value ="0.2"> 20% </option>
                       <option value ="0.3"> 30% </option>
                       <option value ="0.4"> 40% </option>
                       <option value ="0.5"> 50% </option>
                       <option value ="0.6"> 60% </option>
                       <option value ="0.7"> 70% </option>
                       <option value ="0.8"> 80% </option>
                       <option value ="0.9"> 90% </option>
                       <option value ="1.0"> 100% </option>
                   </select></a> &emsp;
               <a>中：<select name="middle">
                       <option value ="0.1"> 10% </option>
                       <option value ="0.2"> 20% </option>
                       <option value ="0.3"> 30% </option>
                       <option value ="0.4"> 40% </option>
                       <option value ="0.5"> 50% </option>
                       <option value ="0.6"> 60% </option>
                       <option value ="0.7"> 70% </option>
                       <option value ="0.8"> 80% </option>
                       <option value ="0.9"> 90% </option>
                       <option value ="1.0"> 100% </option>
                   </select></a> &emsp;
               <a>难：<select name="difficult">
                       <option value ="0.1"> 10% </option>
                       <option value ="0.2"> 20% </option>
                       <option value ="0.3"> 30% </option>
                       <option value ="0.4"> 40% </option>
                       <option value ="0.5"> 50% </option>
                       <option value ="0.6"> 60% </option>
                       <option value ="0.7"> 70% </option>
                       <option value ="0.8"> 80% </option>
                       <option value ="0.9"> 90% </option>
                       <option value ="1.0"> 100% </option>
                   </select></a> 
             <br><br><br><input type="submit" value="提交"/>
       </s:form>
   </div>
   <div class="right">
       <center><h3>tips</h3></center>
       <p>易、中、难加起来为100%，才可以发布题目哦！</p>
   </div>
</body>
</html>