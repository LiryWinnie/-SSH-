package cn.test.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.test.domain.Dean;
import cn.test.domain.Student;
import cn.test.domain.Teacher;
import cn.test.service.DeanService;
import cn.test.service.TeacherService;

public class RekeyAction extends ActionSupport{
	
	private TeacherService teacherService;
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	private DeanService deanService;
	public void setDeanService(DeanService deanService) {
		this.deanService = deanService;
	}

	public String tea() {
		String key1=((String[])ActionContext.getContext().getParameters().get("psone"))[0];
		String key2=((String[])ActionContext.getContext().getParameters().get("pstwo"))[0];
		String key3=((String[])ActionContext.getContext().getParameters().get("psthree"))[0];
		int a=0;
		if(key2.equalsIgnoreCase(key3))
			a=1;
		if(a==0) {
			this.addActionError("两次输入的密码不一致！请确认后输入");
	        return "tea";
		}
		Teacher tea=(Teacher)ActionContext.getContext().getSession().get("teacher");
		String the=this.teacherService.findKeybyID(tea.getTid());
		if(the.equalsIgnoreCase(key1))
			a=2;
		if(a==1) {
			this.addActionError("原密码输入错误！请确认后输入");
	        return "tea";
		}
		else {
			tea.setTpassword(key2);
			this.teacherService.teacherUpdate(tea);
			this.addActionError("修改成功");
	        return "tea";
		}
	}
	public String dean(){
		String key1=((String[])ActionContext.getContext().getParameters().get("psone"))[0];
		String key2=((String[])ActionContext.getContext().getParameters().get("pstwo"))[0];
		String key3=((String[])ActionContext.getContext().getParameters().get("psthree"))[0];
		int a=0;
		if(key2.equalsIgnoreCase(key3))
			a=1;
		if(a==0) {
			this.addActionError("两次输入的密码不一致！请确认后输入");
	        return "dean";
		}
		Dean dean=(Dean)ActionContext.getContext().getSession().get("dean");
		String the=dean.getDpassword();
		if(the.equalsIgnoreCase(key1))
			a=2;
		if(a==1) {
			this.addActionError("原密码输入错误！请确认后输入");
	        return "dean";
		}
		else {
			dean.setDpassword(key2);
			this.deanService.deanUpdate(dean);
			this.addActionError("修改成功");
	        return "dean";
		}
	}
}
