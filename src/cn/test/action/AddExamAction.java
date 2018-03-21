package cn.test.action;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Exam;
import cn.test.domain.Teacher;
import cn.test.service.TeacherService;

public class AddExamAction extends ActionSupport implements ModelDriven<Exam>{

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
	
	public String execute() throws Exception {

        Teacher tea=(Teacher)ActionContext.getContext().getSession().get("teacher");
        exam.setEstatus("Î´·¢²¼");
        exam.setTname(tea.getTname());
        exam.setTnumber(tea.getTnumber());
		if(this.teacherService.addExam(exam)==true) {			
			return SUCCESS;
		}else {
			this.addActionError("");
			return INPUT;
		}
	}
	
}
