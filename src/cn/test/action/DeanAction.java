package cn.test.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Dean;
import cn.test.service.DeanService;

public class DeanAction extends ActionSupport implements ModelDriven<Dean> {
    private Dean dean = new Dean();
	
	@Override
	public Dean getModel() {
		// TODO Auto-generated method stub
		return dean;
	}

	private DeanService deanService;

	public void setDeanService(DeanService deanService) {
		this.deanService = deanService;
	}
	
	public String execute() throws Exception {
		Dean d = this.deanService.findDean(dean);
		if(d==null) {
			this.addActionError("用户名或密码错误！");
	        return INPUT; 
		}
		else {
			ActionContext.getContext().getSession().put("dean", d);
			return SUCCESS;
		}
	}
	
}
