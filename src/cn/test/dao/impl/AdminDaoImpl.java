package cn.test.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.test.dao.AdminDao;
import cn.test.domain.Admin;


public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {

	@Override
	public Admin findAdmin(Admin admin) {
		Admin firstuser = new Admin();  
        //HQL查询语句   
        String hql = "from Admin admin where admin.anumber=? and admin.apassword=?";  
        //将查询出的结果放到List   
        List<Admin> adminlist = this.getHibernateTemplate().find(hql,admin.getAnumber(),admin.getApassword());  
        //判断是否有查询结果，换句话说就是判断用户是否存在   
        if(adminlist.size()>0){  
        //取出查询结果的第一个值，理论上数据库是没有重复的用户信息   
        firstuser = adminlist.get(0);  
        return firstuser; 
        }  
		return null;
	}

}
