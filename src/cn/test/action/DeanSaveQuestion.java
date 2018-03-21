package cn.test.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Exam;
import cn.test.domain.Question;
import cn.test.service.DeanService;

public class DeanSaveQuestion extends ActionSupport implements ModelDriven<Question>{
	private Question question= new Question();
	@Override
	public Question getModel() {
		// TODO Auto-generated method stub
		return question;
	}

	private DeanService deanService;
	public void setDeanService(DeanService deanService) {
		this.deanService = deanService;
	}
	
	public String execute() throws Exception {
		Exam exam = (Exam)ActionContext.getContext().getSession().get("e");
		Integer eid=exam.getEid();
		question.setEid(eid);
		boolean save=this.deanService.savequestion(question);
		if(save==true) {
			return SUCCESS;
		}
		else {
			this.addActionError("填入数据不完整！");
			return INPUT;
		}
	}
}
