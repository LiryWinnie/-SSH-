package cn.test.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Course;
import cn.test.domain.Exam;
import cn.test.domain.Question;
import cn.test.domain.Teacher;
import cn.test.service.TeacherService;

public class CourseTeaAction  extends ActionSupport implements ModelDriven<Course>{
 
	private Course course=new Course();
	@Override
	public Course getModel() {
		// TODO Auto-generated method stub
		return course;
	}
	private TeacherService teacherService;
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	public String piy() {
		Teacher tea=(Teacher)ActionContext.getContext().getSession().get("teacher");
		String name=course.getSnumber()+"_"+tea.getTnumber();
		ActionContext.getContext().getSession().put("snumber", course.getSnumber());
		this.teacherService.xmlaudit(name);
		return "piy";
	}
	
	@SuppressWarnings("unchecked")
	public String sub() {
		List<String> alist=new ArrayList<String>();
		List<String> blist=new ArrayList<String>();
		List<Question> clist=(List<Question>)ActionContext.getContext().getSession().get("question");
		List<Integer> dlist=new ArrayList<Integer>();
		for(int i=0;i<clist.size();i++) {
			Integer n=clist.get(i).getQid();
			dlist.add(n);
		}
		String snumber=(String)ActionContext.getContext().getSession().get("snumber");
		Teacher tea=(Teacher)ActionContext.getContext().getSession().get("teacher");
		String name=snumber+"_"+tea.getTnumber();
		alist=(List<String>)ActionContext.getContext().getSession().get("qlist");
		for(int i=0;i<alist.size();i++) {
	    	String m=((String[]) ActionContext.getContext().getParameters().get(alist.get(i)))[0];
			blist.add(m);
	    }
		this.teacherService.updateXML(name,blist,dlist);
		int result=this.teacherService.stuScore(name);
		Integer eid=(Integer)ActionContext.getContext().getSession().get("eid");
		Exam exam=this.teacherService.findById(eid);
		Course course=this.teacherService.CourseBysumtnum(exam.getCourse(),snumber,tea.getTnumber());
		course.setGrade(result);
		course.setStatus("ÒÑÅúÔÄ");
		this.teacherService.UpdateCourse(course);
		return "sub";
	}
}
