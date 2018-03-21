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
       <h3><s:property value="#cour"/></h3><br><br>
           <table border="1" cellspacing="0" align="center">
           <tr>
           <th width="150px">考试名称</th>
           <th width="150px">考试科目</th>
           <th width="100px">学生学号</th>
           <th width="100px">学生姓名</th>
           <th width="100px">成绩</th>
           <th width="100px">状态</th>
           <th width="100px">批阅</th>
           </tr>
           <s:iterator value="#course" var="c">
           <tr>
              <td><s:property value="#cour"/></td>
              <td><s:property value="#c.course"/></td>
              <td><s:property value="#c.snumber"/></td>
              <td><s:property value="#c.sname"/></td>
              <td><s:property value="#c.grade"/></td>
              <td><s:property value="#c.status"/></td>
              <td><a href="coursedean_piy.action?snumber=<s:property value="#c.snumber"/>&course=<s:property value="#c.course"/>">批阅</a></td>
           </tr>
           </s:iterator>
           </table>
   </div>
</body>
</html>