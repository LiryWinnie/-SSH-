package cn.test.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Bdegree;
import cn.test.domain.Exam;
import cn.test.service.DeanService;

public class DeanPubAction extends ActionSupport implements ModelDriven<Bdegree>{

	private Bdegree bdegree=new Bdegree(); 
	@Override
	public Bdegree getModel() {
		// TODO Auto-generated method stub
		return bdegree;
	}

	private DeanService deanService;
	public void setDeanService(DeanService deanService) {
		this.deanService = deanService;
	}
	
	public String exam() {
		Exam exam=(Exam)ActionContext.getContext().getSession().get("e");
		bdegree.setEid(exam.getEid());
		boolean result=this.deanService.saveBdegree(bdegree);
		if(result==true) {
			exam.setEstatus("已发布");
			this.deanService.UpdateExam(exam);
			return SUCCESS;
		}
		else
			this.addActionError("发布失败，请审查你的难度！");
		    return INPUT;
		
	}
}
