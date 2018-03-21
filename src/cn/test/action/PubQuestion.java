package cn.test.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Bdegree;
import cn.test.domain.Exam;
import cn.test.service.TeacherService;

public class PubQuestion extends ActionSupport implements ModelDriven<Bdegree>{
    private Bdegree bdegree= new Bdegree();
	@Override
	public Bdegree getModel() {
		// TODO Auto-generated method stub
		return bdegree;
	}

	private TeacherService teacherService;
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	public String first(){
		Exam exam = (Exam)ActionContext.getContext().getSession().get("e");
		bdegree.setEid(exam.getEid());
		boolean result= this.teacherService.saveDegree(bdegree);
		if(result==true) {
			exam.setEstatus("´ýÉóºË");
			this.teacherService.ExamUpdate(exam);
			return SUCCESS;
		}
		else
			this.addActionError("·¢²¼Ê§°Ü£¬ÇëÉó²éÄãµÄÄÑ¶È£¡");
		    return INPUT;
	}
	public String second() {
		Exam exam = (Exam)ActionContext.getContext().getSession().get("e");
		Bdegree bd=this.teacherService.findBdegreeByEid(exam.getEid());
		bdegree.setBid(bd.getBid());
		bdegree.setEid(exam.getEid());
		bdegree.setComment(null);
		exam.setEstatus("´ýÉóºË");
		this.teacherService.ExamUpdate(exam);
		this.teacherService.BdegreeUpdate(bdegree);
		return "second";
	}
		
}
