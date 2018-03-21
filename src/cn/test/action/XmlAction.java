package cn.test.action;

import java.util.ArrayList;
import java.util.List;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.test.domain.Course;
import cn.test.domain.Exam;
import cn.test.domain.Question;
import cn.test.domain.Student;
import cn.test.service.StudentService;

public class XmlAction extends ActionSupport {
    private StudentService studentService;

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	public String join() {
		List<String> alist=new ArrayList<String>();
		List<Question> blist=(List<Question>)ActionContext.getContext().getSession().get("question");
		
		alist=(List<String>)ActionContext.getContext().getSession().get("qlist");
	    for(int i=0;i<alist.size();i++) {
	    	String m=((String[]) ActionContext.getContext().getParameters().get(alist.get(i)))[0];
	    	blist.get(i).setQanswer(m);;
	    }
	    List<Integer> clist=new ArrayList<Integer>();
	    for(int i=0;i<blist.size();i++) {
	    	clist.add(blist.get(i).getQid());
	    }
	    List<String> dlist=this.studentService.findQanswer(clist);
	    for(int i=0;i<dlist.size();i++) {
	        System.out.println(dlist.get(i));
	    }
	    Exam exam=(Exam)ActionContext.getContext().getSession().get("e");
	    Student stu=(Student)ActionContext.getContext().getSession().get("student");
	    List<Integer> score=this.studentService.ExamXML(dlist,blist,exam,stu);
	    List<Course> course=new ArrayList<Course>();
	    course=this.studentService.findCourseByStudent(stu.getSnumber(), exam.getCourse());
	    if(score.size()==1) {
	          course.get(0).setStatus("ÒÑ¿¼ÊÔ");
	          this.studentService.CourseUpdate(course.get(0));
	    }
	    else {
	    	course.get(0).setStatus("ÒÑÅúÔÄ");
	    	course.get(0).setGrade(score.get(1));
	    	this.studentService.CourseUpdate(course.get(0));
	    }
		return "joinok";
	}
}
