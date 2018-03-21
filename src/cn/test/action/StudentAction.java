package cn.test.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Student;
import cn.test.service.StudentService;

public class StudentAction extends ActionSupport implements ModelDriven<Student> {
	//模型驱动使用的对象
	private Student student=new Student();
	@Override
	public Student getModel() {
		// TODO Auto-generated method stub
		return student;
	}
	
	private StudentService studentService;
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	public String execute() throws Exception {  
	
	    Student stu = studentService.findStudent(student);
	    if(stu==null) {
	    	this.addActionError("用户名或密码错误！");
	        return INPUT;  
	    }
	    else{  
	    	ActionContext.getContext().getSession().put("student", stu);
	    	return SUCCESS; 
        } 
	}  
	

}
