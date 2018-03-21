package cn.test.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.test.domain.Admin;
import cn.test.service.AdminService;


public class AdminAction extends ActionSupport implements ModelDriven<Admin>{

	private Admin admin= new Admin();
	
	@Override
	public Admin getModel() {
		// TODO Auto-generated method stub
		return admin;
	}

	private AdminService adminService;

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public String execute() throws Exception {
		Admin A = this.adminService.findAdmin(admin);
		if(A==null) {
			this.addActionError("用户名或密码错误！");
	        return INPUT; 
		}
		else {
			ActionContext.getContext().getSession().put("admin", A);
			return SUCCESS;
		}
	}
}
