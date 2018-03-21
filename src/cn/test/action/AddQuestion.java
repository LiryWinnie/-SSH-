package cn.test.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Bdegree;
import cn.test.domain.Course;
import cn.test.domain.Exam;
import cn.test.domain.Question;
import cn.test.domain.Teacher;
import cn.test.service.TeacherService;

public class AddQuestion extends ActionSupport implements ModelDriven<Exam>{

    private Exam exam=new Exam();
	
	@Override
	public Exam getModel() {
		// TODO Auto-generated method stub
		return exam;
	}
	private TeacherService teacherService;
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	public String edit(){
		exam = teacherService.findById(exam.getEid());
		ActionContext.getContext().getSession().put("e", exam);
		return "editsuccess";
	}
	
	public String pub(){
		exam = teacherService.findById(exam.getEid());
		ActionContext.getContext().getSession().put("e", exam);
		return "pubsuccess";
	}
	
	public String unaud() {
		Teacher tea=(Teacher)ActionContext.getContext().getSession().get("teacher");
		String tnumber=tea.getTnumber();
		List<Exam> unaud = this.teacherService.getUnaudExam(tnumber);
		ActionContext.getContext().put("exams", unaud);
		return "unaud";
	}
	
	public String unpass() {
		Teacher tea=(Teacher)ActionContext.getContext().getSession().get("teacher");
		String tnumber=tea.getTnumber();
		List<Exam> unaud = this.teacherService.getUnpassExam(tnumber);
		ActionContext.getContext().put("exams", unaud);
		return "unpass";
	}
	
	public String look() {
		exam = teacherService.findById(exam.getEid());
		ActionContext.getContext().getSession().put("e", exam);
		Bdegree bdegree=teacherService.findBdegreeByEid(exam.getEid());
		ActionContext.getContext().getSession().put("b", bdegree);
		return "look";
	}
	
	public String doing() {
		Teacher tea=(Teacher)ActionContext.getContext().getSession().get("teacher");
		String tnumber=tea.getTnumber();
		List<Exam> unaud = this.teacherService.getDoingExam(tnumber);
		ActionContext.getContext().put("exams", unaud);
		if(unaud!=null)
		    this.teacherService.CourseByExam(unaud);
		return "doing";
	}
	
	public String piy() {
		Teacher tea=(Teacher)ActionContext.getContext().getSession().get("teacher");
		String tnumber=tea.getTnumber();
		List<Course> co=this.teacherService.ListCourseby(exam.getCourse(),tea.getTnumber());
		String cour=exam.getEname();
		Integer eid=exam.getEid();
		ActionContext.getContext().getSession().put("eid", eid);
		ActionContext.getContext().put("cour",cour);
		ActionContext.getContext().put("course",co);
		return "piy";
	}
	
	public String end() {
		Exam e=this.teacherService.findById(exam.getEid());
		e.setEstatus("ÒÑ½áÊø");
		this.teacherService.ExamUpdate(e);
		return "end";
	}
	
	public String history() {
		Teacher tea=(Teacher)ActionContext.getContext().getSession().get("teacher");
		String tnumber=tea.getTnumber();
		List<Exam> unaud = this.teacherService.getEndExam(tnumber);
		ActionContext.getContext().put("exams", unaud);
		return "history";
	}
	
	public String lookhistory() {
		Teacher tea=(Teacher)ActionContext.getContext().getSession().get("teacher");
		String tnumber=tea.getTnumber();
		List<Course> course=this.teacherService.ListCourseby(exam.getCourse(), tnumber);
		ActionContext.getContext().put("cour",exam.getEname());
		ActionContext.getContext().put("course", course);
		return "lookhistory";
	}
	
	public String que() {
		exam = teacherService.findById(exam.getEid());
		ActionContext.getContext().getSession().put("e", exam);
		List<Question> q=this.teacherService.getQuestions(exam.getEid());
		ActionContext.getContext().put("questions", q);
		return "que";	
	}
}
