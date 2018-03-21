package cn.test.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Course;
import cn.test.domain.Exam;
import cn.test.domain.Student;
import cn.test.service.StudentService;

public class StudentExam extends ActionSupport implements ModelDriven<Exam>{

	private Exam exam=new Exam();
	@Override
	public Exam getModel() {
		// TODO Auto-generated method stub
		return exam;
	}
	private StudentService studentService;
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
    
	public String list() {
		Student stu = (Student)ActionContext.getContext().getSession().get("student");
		List<Course> cou=this.studentService.findCourseBySnumber(stu.getSnumber(),"未考试");
		if(cou!=null){
		    List<Exam> exam=this.studentService.findExamByCourse(cou,"已发布");
		    ActionContext.getContext().put("exams", exam);
		}
		return "list";
	}
	
	public String join() {
		Integer eid=exam.getEid();
		Exam ex =this.studentService.findExamById(eid);
		ActionContext.getContext().getSession().put("e", ex);
		Student stu = (Student)ActionContext.getContext().getSession().get("student");
		this.studentService.joinExam(stu,eid);
		return "join";
	}
	
	public String add() {
		Integer eid=exam.getEid();
		Exam ex =this.studentService.findExamById(eid);
		Student stu = (Student)ActionContext.getContext().getSession().get("student");
		List<Course> cc=this.studentService.findCourseByStudent(stu.getSnumber(),ex.getCourse());
		if(cc==null) {
		Course cou=new Course();
		cou.setCourse(ex.getCourse());
		cou.setTname(ex.getTname());
		cou.setTnumber(ex.getTnumber());
		System.out.println(stu.getSname());
		cou.setSname(stu.getSname());
		cou.setSnumber(stu.getSnumber());
		cou.setStatus("未考试");
		this.studentService.saveCourse(cou);
		}
		return "add";
		
	}
	public String history() {
		Student stu = (Student)ActionContext.getContext().getSession().get("student");
		String snumber=stu.getSnumber();
		List<Course> course1=this.studentService.findCourseBySnumber(snumber,"已考试");
		List<Course> course2=this.studentService.findCourseBySnumber(snumber,"已批阅");
		if(course2==null && course1==null) {
			System.out.print("1");
			return "history";
		}
		//System.out.print(course1.size()+" a:"+course2.size());
		if(course1==null && course2!=null) {
			System.out.print("3");
		    ActionContext.getContext().put("course", course2);
		    List<Exam> exam=this.studentService.GetExamCourse(course2);
		    ActionContext.getContext().getSession().put("exam",exam);
		    return "history";
		}
		System.out.print("4");
		if(course2==null && course1!=null) {
		    ActionContext.getContext().put("course", course1);
		    List<Exam> exam=this.studentService.GetExamCourse(course1);
		    ActionContext.getContext().getSession().put("exam",exam);
		    return "history";
		 }
		if(course1!=null && course2!=null) {
			   course1.addAll(course2);
			   ActionContext.getContext().put("course", course1);
			   List<Exam> exam=this.studentService.GetExamCourse(course1);
			   ActionContext.getContext().getSession().put("exam",exam);
			   return "history";
			}
		return "history";
	}
}
