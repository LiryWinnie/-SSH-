package cn.test.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Dean;
import cn.test.domain.Exam;
import cn.test.service.DeanService;

public class DeanAddExam extends ActionSupport implements ModelDriven<Exam>{
    private Exam exam= new Exam();
	@Override
	public Exam getModel() {
		// TODO Auto-generated method stub
		return exam;
	}

    private DeanService deanService;
	public void setDeanService(DeanService deanService) {
		this.deanService = deanService;
	}
    
	public String execute() throws Exception{
		Dean dean = (Dean)ActionContext.getContext().getSession().get("dean");
		exam.setEstatus("Î´·¢²¼");
        exam.setTname(dean.getDname());
        exam.setTnumber(dean.getDnumber());
        if(this.deanService.addExam(exam)==true) {			
			return SUCCESS;
		}else {
			this.addActionError("");
			return INPUT;
		}
	}
}
