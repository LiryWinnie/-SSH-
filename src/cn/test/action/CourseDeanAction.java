package cn.test.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Course;
import cn.test.domain.Dean;
import cn.test.domain.Exam;
import cn.test.domain.Question;
import cn.test.service.DeanService;

public class CourseDeanAction  extends ActionSupport implements ModelDriven<Course>{

	private Course course=new Course();
	@Override
	public Course getModel() {
		// TODO Auto-generated method stub
		return course;
	}
	private DeanService deanService;
	public void setDeanService(DeanService deanService) {
		this.deanService = deanService;
	}
	
	public String piy() {
		Dean dean=(Dean)ActionContext.getContext().getSession().get("dean");
		String name=course.getSnumber()+"_"+dean.getDnumber();
		ActionContext.getContext().getSession().put("snumber", course.getSnumber());
		this.deanService.xmlaudit(name);
		return "piy";
	}
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
		Dean dean=(Dean)ActionContext.getContext().getSession().get("dean");
		String name=snumber+"_"+dean.getDnumber();
		alist=(List<String>)ActionContext.getContext().getSession().get("qlist");
		for(int i=0;i<alist.size();i++) {
	    	String m=((String[]) ActionContext.getContext().getParameters().get(alist.get(i)))[0];
			blist.add(m);
	    }
		this.deanService.updateXML(name,blist,dlist);
		int result=this.deanService.stuScore(name);
		Integer eid=(Integer)ActionContext.getContext().getSession().get("eid");
		Exam exam=this.deanService.finById(eid);
		Course course=this.deanService.CourseBysumtnum(exam.getCourse(),snumber,dean.getDnumber());
		course.setGrade(result);
		course.setStatus("ÒÑÅúÔÄ");
		this.deanService.CourseUpdate(course);
		return "sub";
		
	}

}
