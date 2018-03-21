package cn.test.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Question;
import cn.test.service.TeacherService;

public class teaQuestion extends ActionSupport implements ModelDriven<Question>{
    
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
	
	public String mod() {
		Question q=this.teacherService.getQuestionById(question.getQid());
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
		this.teacherService.updateQuestion(q);
		return "update";
	}
	
	public String del() {
		Question q=this.teacherService.getQuestionById(question.getQid());
		this.teacherService.DeleteQuestion(q);
		return "del";
	}
}
