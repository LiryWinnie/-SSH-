package cn.test.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Question;
import cn.test.service.DeanService;

public class deanQuestion extends ActionSupport implements ModelDriven<Question>{
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
	
	public String mod() {
		Question q=this.deanService.getQuestionById(question.getQid());
		ActionContext.getContext().getSession().put("q", q);
		return "mod";
	}
	public String update() {
		Question q=(Question)ActionContext.getContext().getSession().get("q");
		q.setQanswer(question.getQanswer());
		q.setQcontent(question.getQcontent());
		q.setQdegree(question.getQdegree());
		q.setQscore(question.getQscore());
		q.setQtitle(question.getQtitle());
		q.setQtype(question.getQtype());
		this.deanService.updateQuestion(q);
		return "update";
	}
	public String del() {
		Question q=this.deanService.getQuestionById(question.getQid());
		this.deanService.DeleteQuestion(q);
		return "del";
	}
}
