package cn.test.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Exam;
import cn.test.domain.Teacher;
import cn.test.service.TeacherService;

public class TeacherAction extends ActionSupport implements ModelDriven<Teacher>{

	private Teacher teacher= new Teacher();
	@Override
	public Teacher getModel() {
		// TODO Auto-generated method stub
		return teacher;
	}

	private TeacherService teacherService;
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	public String execute() throws Exception { 
		Teacher tea = this.teacherService.findTeacher(teacher);
		if(tea==null) {
			this.addActionError("用户名或密码错误！");
	        return INPUT;  
		}
		else {
			ActionContext.getContext().getSession().put("teacher", tea);
			return SUCCESS;
		}
	}
	
}
