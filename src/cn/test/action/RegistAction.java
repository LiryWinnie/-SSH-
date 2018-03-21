package cn.test.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Student;
import cn.test.service.StudentService;

public class RegistAction extends ActionSupport implements ModelDriven<Student>{
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
     
	 @Override  
	public String execute() throws Exception { 
	       this.studentService.saveStudent(student);
	       Student stu = studentService.findStudent(student);
	        if(stu.getSname()==""||stu.getSclass()==""||stu.getSex()=="") {
	    	this.addActionError("×¢²áÊ§°Ü£¡");
	        return INPUT;  
	        }
	        else{  
	    	return SUCCESS; 
            } 
		} 
}
