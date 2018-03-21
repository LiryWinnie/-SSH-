package cn.test.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Bdegree;
import cn.test.domain.Exam;
import cn.test.service.DeanService;

public class DeanForm extends ActionSupport implements ModelDriven<Bdegree>{
 
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
	
	public String pub() {
		Exam e=(Exam)ActionContext.getContext().getSession().get("e");
		Bdegree b=(Bdegree)ActionContext.getContext().getSession().get("bdegree");
		b.setComment(bdegree.getComment());
		e.setEstatus("已发布");
		this.deanService.UpdateExam(e);
		this.deanService.UpdateBdegree(b);
		return "pub";
	}
	
	public String unpub() {
		Exam e=(Exam)ActionContext.getContext().getSession().get("e");
		Bdegree b=(Bdegree)ActionContext.getContext().getSession().get("bdegree");
		b.setComment(bdegree.getComment());
		e.setEstatus("未通过");
		this.deanService.UpdateExam(e);
		this.deanService.UpdateBdegree(b);
		return "unpub";
	}
	
}
