<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.List" import="java.util.ArrayList"
    import="cn.test.domain.Question" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head> 
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
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
   <s:form action="coursedean_sub" method="post" namespace="/">
      <%
      List<Question> question=(List<Question>)session.getAttribute("question");
      int a=question.size();
      List<String> list=new ArrayList<String>(); 
      for(int i=0;i<a;i++){%>     
          <div class="qcontent">
          <a><%-- <%=question.get(i).getQid()%>. --%> 题目： </a><%=question.get(i).getQcontent()%></div>
          <div class="qanswer">
          <a>&emsp;&emsp;学生答案：  </a><%=question.get(i).getQanswer() %></div>
          <%char me=(char)(97+i);
            String na=me+""; %>
          <select name="<%=na%>">
               <option value ="0"> 0 </option>
               <option value ="1"> 1 </option>
               <option value ="2"> 2 </option>
               <option value ="3"> 3 </option>
               <option value ="4"> 4 </option>
               <option value ="5"> 5 </option>
               <option value ="6"> 6 </option>
               <option value ="7"> 7 </option>
               <option value ="8"> 8 </option>
               <option value ="9"> 9 </option>
               <option value ="10"> 10 </option>
           </select>
          <% 
          list.add(na);
          }
         %>
      <%session.setAttribute("qlist",list); %>
      <center><input type="submit" value="提交"></center>
      </s:form>
   </div>
</body>
</html>