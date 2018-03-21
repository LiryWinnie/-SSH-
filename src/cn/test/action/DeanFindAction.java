package cn.test.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Bdegree;
import cn.test.domain.Course;
import cn.test.domain.Dean;
import cn.test.domain.Exam;
import cn.test.domain.Question;
import cn.test.domain.Teacher;
import cn.test.service.DeanService;

public class DeanFindAction extends ActionSupport implements ModelDriven<Exam>{
    
	private Exam exam=new Exam();
	
	@Override
	public Exam getModel() {
		// TODO Auto-generated method stub
		return exam;
	}

	private DeanService deanService;
	
	public void setDeanService(DeanService deanService) {
		this.deanService = deanService;
	}
	
	public String list() {
		Dean dean = (Dean)ActionContext.getContext().getSession().get("dean");
		String dnumber = dean.getDnumber();
		List<Exam> exa = this.deanService.getExambyDnumber(dnumber);
		ActionContext.getContext().put("exams", exa);
	    return "listsuccess";
		
	}
	
	public String add() {
		exam=this.deanService.finById(exam.getEid());
		ActionContext.getContext().getSession().put("e", exam);
		return "addsuccess";
	}
	public String pub() {
		exam=this.deanService.finById(exam.getEid());
		ActionContext.getContext().getSession().put("e", exam);
		return "pubsuccess";
	}
	public String unaud() {
		List<Exam> exa = this.deanService.getExambyStatus("´ýÉóºË");
		ActionContext.getContext().put("exams", exa);
	    return "unaudsuccess";
	}
	public String find() {
		exam=this.deanService.finById(exam.getEid());
		ActionContext.getContext().getSession().put("e", exam);
		Bdegree bdegree=this.deanService.getBdegree(exam.getEid());
		ActionContext.getContext().getSession().put("bdegree",bdegree);
		return "findsuccess";
	}
    public String doing() {
    	Dean dean=(Dean)ActionContext.getContext().getSession().get("dean");
    	String dnumber=dean.getDnumber();
    	List<Exam> dexam=this.deanService.getDoingExam(dnumber);
    	ActionContext.getContext().put("exams", dexam);
    	if(dexam!=null)
		    this.deanService.CourseByExam(dexam);
		return "doing";
    }
    
    public String piy() {
    	Dean dean=(Dean)ActionContext.getContext().getSession().get("dean");
    	String dnumber=dean.getDnumber();
    	System.out.println(exam.getCourse());
    	System.out.println(exam.getEname());
    	List<Course> co=this.deanService.ListCourseby(exam.getCourse(),dnumber);
    	String cour=exam.getEname();
		Integer eid=exam.getEid();
		ActionContext.getContext().getSession().put("eid", eid);
		ActionContext.getContext().put("cour",cour);
		ActionContext.getContext().put("course",co);
    	return "piy";
    }
    
    public String end() {
    	Exam e=this.deanService.finById(exam.getEid());
		e.setEstatus("ÒÑ½áÊø");
		this.deanService.UpdateExam(e);
		return "end";
    }
    
    public String history() {
    	Dean dean=(Dean)ActionContext.getContext().getSession().get("dean");
    	String dnumber=dean.getDnumber();
    	List<Exam> unaud = this.deanService.getEndExam(dnumber);
    	ActionContext.getContext().put("exams", unaud);
    	return "history";
    	
    }
    
    public String lookhistory() {
		Dean dean=(Dean)ActionContext.getContext().getSession().get("dean");
		String dnumber=dean.getDnumber();
		List<Course> course=this.deanService.ListCourseby(exam.getCourse(), dnumber);
		ActionContext.getContext().put("cour",exam.getEname());
		ActionContext.getContext().put("course", course);
		return "lookhistory";
	}
    
    public String que() {
    	exam=this.deanService.finById(exam.getEid());
    	ActionContext.getContext().getSession().put("e", exam);
		List<Question> q=this.deanService.getQuestions(exam.getEid());
		ActionContext.getContext().put("questions", q);
    	return "que";
    }
}
