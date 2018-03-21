package cn.test.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Exam;
import cn.test.domain.Question;
import cn.test.domain.Teacher;
import cn.test.service.TeacherService;

public class SaveQuestion extends ActionSupport implements ModelDriven<Question>{
    
	private Question question= new Question();
	@Override
	public Question getModel() {
		// TODO Auto-generated method stub
		return question;
	}
	
	private  TeacherService teacherService;
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	public String execute() throws Exception {
		Exam exam = (Exam)ActionContext.getContext().getSession().get("e");
		Integer eid=exam.getEid();
		question.setEid(eid);
		boolean save=teacherService.savequestion(question);
		if(save==true) {
			return SUCCESS;
		}
		else {
			this.addActionError("填入数据不完整！");
			return INPUT;
		}
	
	}

}
