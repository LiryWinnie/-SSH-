<%@ page language="java" contentType="text/html;charset=utf-8" errorPage="exception.jsp" import="java.util.List"
    import="cn.test.domain.Question" import="cn.test.domain.Exam" import="java.util.ArrayList"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>在线考试系统</title>  	
<link href="<%=request.getContextPath()%>/css/test.css" rel="stylesheet" type="text/css" >
<link href="<%=request.getContextPath()%>/css/time.css" rel="stylesheet" type="text/css" >
</head> 
<body>
<h1>${sessionScope.e.getEname()}</h1>
<%Exam e=(Exam)session.getAttribute("e");
     Integer time=e.getTime()-1;%>
<div class="Exam">
<s:form action="studentEx_join" method="post" namespace="/">
   
    <%List<Question> question=(List<Question>)session.getAttribute("question");
      int a=question.size();
      List<String> list=new ArrayList<String>();
      for(int i=0;i<a;i++){
    %>
    <div class="ques">
        <%char m=question.get(i).getQtype().charAt(0);
        if(m=="选择题".charAt(0)||m=="简答题".charAt(0)) {%>
        <p><%=question.get(i).getQtitle() %></p>
        <%} %>
        <p><%=question.get(i).getQcontent() %></p>
        <%if(m=="简答题".charAt(0)){ 
           char me=(char)(97+i);
           String na=me+"";%>
           <textarea name="<%=na%>"></textarea>
        <%list.add(na);
        }
        else if(m=="选择题".charAt(0)){ 
        	char me=(char)(97+i);
            String na=me+"";%>   
           <select name="<%=na%>">
               <option value ="A"> A </option>
               <option value ="B"> B </option>
               <option value ="C"> C </option>
               <option value ="D"> D </option>
           </select>
        <%list.add(na);
        } 
        else{ 
        	char me=(char)(97+i);
            String na=me+"";%>   
           <input name="<%=na%>" type="text"/>
        <%list.add(na);
        } %>
        <br>
    </div>
    <%} session.setAttribute("qlist", list);
    %>
    <br><br>
   <center><input type="submit" value="交卷"></center>
</s:form>
</div>
<div class="questions">
   <div class="countdown">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(function() {
    //设置时间倒计时
    setCountDown_time();
})

/*时间倒计时*/
var sec = 60;
    min =<%=time%>;
var idt;
var format = function(str) {
    if(parseInt(str) < 10) {
        return "0" + str;
    }
    return str;
};
function setCountDown_time() {
    idt = window.setInterval("ls();", 1000);
}
function ls() {
    sec--;
    if(sec == 0) {
        if(parseInt(min) == 0 && parseInt(sec) == 0) {
            document.getElementById("countdown_time").innerHTML = format(min) + ":" + format(sec);
            window.clearInterval(idt);
            alert('考试时间已到，试卷已提交，感谢您的使用！');
        } else {
            min--;
            sec = 59;
        }
    }
    document.getElementById("countdown_time").innerHTML = format(min) + ":" + format(sec);
}
//以上JS修改完成后

</script>
    <p class="mtp"><span class="countdown_text">答题倒计时</span></p>
    <p class="line_height34"><span id="countdown_time"></span><span class="countdown_text">分钟</span></p>
</div>
   </div>
</body>
</html>