package cn.test.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Course;
import cn.test.domain.Exam;
import cn.test.domain.Student;
import cn.test.service.StudentService;

public class CourseAction extends ActionSupport implements ModelDriven<Course>{

	private Course course=new Course();
	@Override
	public Course getModel() {
		// TODO Auto-generated method stub
		return course;
	}
	
	private StudentService studentService;
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	public String list() {
		Student student=(Student)ActionContext.getContext().getSession().get("student");
		List<Course> cou1=this.studentService.CourseBySnumber(student.getSnumber());
		ActionContext.getContext().put("course", cou1);
		return "list";
		
	}
	
	public String search() {
		List<Exam> exam=this.studentService.findExamCourse(course.getCourse());
		ActionContext.getContext().put("exams", exam);
		return "search";
	}
	
}
