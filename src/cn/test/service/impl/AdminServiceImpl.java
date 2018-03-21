package cn.test.service.impl;

import cn.test.dao.AdminDao;
import cn.test.domain.Admin;

import cn.test.service.AdminService;

public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao;
	

	public AdminDao getAdminDao() {
		return adminDao;
	}


	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}


	@Override
	public Admin findAdmin(Admin admin) {
		// TODO Auto-generated method stub
		Admin firstuser = this.adminDao.findAdmin(admin);
		return firstuser;
	}

}
