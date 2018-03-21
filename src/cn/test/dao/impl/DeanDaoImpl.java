package cn.test.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.test.dao.DeanDao;
import cn.test.domain.Bdegree;
import cn.test.domain.Course;
import cn.test.domain.Dean;
import cn.test.domain.Exam;
import cn.test.domain.Question;


public class DeanDaoImpl extends HibernateDaoSupport implements DeanDao {

	@Override
	public Dean findDean(Dean dean) {
		// TODO Auto-generated method stub
		Dean firstDean = new Dean();
		String hql = "from Dean dean where dean.dnumber=? and dean.dpassword=?";  
        //将查询出的结果放到List   
        List<Dean> deanlist = this.getHibernateTemplate().find(hql,dean.getDnumber(),dean.getDpassword()); 
        if(deanlist.size()>0){  
            //取出查询结果的第一个值，理论上数据库是没有重复的用户信息   
            firstDean = deanlist.get(0);  
            return firstDean; 
         }  
        return null;
	}

	@Override
	public List<Exam> getExambyDnumber(String dnumber) {
		Exam exam = new Exam();
		String hql="from Exam exam where exam.tnumber=?";
		List<Exam> examlist=this.getHibernateTemplate().find(hql,dnumber);
		if(examlist.size()>0) {
			return examlist;
		}
		return null;
	}

	@Override
	public boolean addExam(Exam exam) {
		if(exam.getCourse()==""||exam.getEname()==""||exam.getTime()==0) {
			return false;
		}
		else {
		     this.getHibernateTemplate().save(exam); 
		     return true;
		}
	}

	@Override
	public Exam findById(Integer eid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Exam.class, eid);
	}

	@Override
	public boolean savequestion(Question question) {
		// TODO Auto-generated method stub
		if(question.getQtitle()==""||question.getQcontent()==""||question.getQanswer()==""||question.getQscore()==0) {
			return false;
		}
		else {
		this.getHibernateTemplate().save(question); 
		return true;
		}
	}

	@Override
	public List<Exam> getExambyStatus(String status) {
		Exam exam = new Exam();
		String hql="from Exam exam where exam.estatus=?";
		List<Exam> examlist=this.getHibernateTemplate().find(hql,status);
		if(examlist.size()>0) {
			return examlist;
		}
		return null;
	}

	@Override
	public Bdegree getBdegree(Integer eid) {
		// TODO Auto-generated method stub
		Bdegree first = new Bdegree();
		String hql = "from Bdegree bdegree where bdegree.eid=?";  
        //将查询出的结果放到List   
        List<Bdegree> deanlist = this.getHibernateTemplate().find(hql,eid); 
        if(deanlist.size()>0){  
            //取出查询结果的第一个值，理论上数据库是没有重复的用户信息   
            first = deanlist.get(0);  
            return first; 
         }  
        return null;
	}

	@Override
	public void UpdateExam(Exam exam) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(exam);
	}

	@Override
	public void UpdateBdegree(Bdegree bdegree) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(bdegree);
	}

	@Override
	public boolean saveBdegree(Bdegree bdegree) {
		// TODO Auto-generated method stub
		if((int)(bdegree.getEasy()+bdegree.getMiddle()+bdegree.getDifficult())!=1)
		    return false;
		
		Integer eid=bdegree.getEid();	
		Exam exam=findById(eid);
		System.out.println(exam.getEstatus());
		if(exam.getEstatus().equalsIgnoreCase("未发布")) {
		    float easy=this.GetByIdDegree(eid,"易");
		    float middle=this.GetByIdDegree(eid,"中");
		    float difficult=this.GetByIdDegree(eid,"难");
		    if(easy>=bdegree.getEasy()&&middle>=bdegree.getMiddle()&&difficult>=bdegree.getDifficult()) {
			    this.getHibernateTemplate().save(bdegree);
			    return true;			
		    }
		    else {
			    return false;
		    }
		}else {
			return false;
		}
	}

	private float GetByIdDegree(Integer eid, String qdegree) {
		// TODO Auto-generated method stub
		String hql="from Question question where question.eid=? and question.qdegree=?";
		List<Question> quelist=this.getHibernateTemplate().find(hql,eid,qdegree);
		int a=0;
		if(quelist.size()>0) {
			for(int i=0;i<quelist.size();i++) {
				a=a+quelist.get(i).getQscore();
				System.out.println(a);
			}
			return ((float)a/100);
		}
		else 
		    return 0;
	}

	@Override
	public List<Exam> getDoingExam(String dnumber) {
		// TODO Auto-generated method stub
		String hql="from Exam exam where exam.tnumber=? and exam.estatus=?";
		List<Exam> elist=this.getHibernateTemplate().find(hql,dnumber,"已发布");
		if(elist!=null) {
			return elist;
		}
		return null;
	}

	@Override
	public List<Course> CourseByExam(Exam exam) {
		// TODO Auto-generated method stub
		String hql="from Course course where course.course=? and course.tnumber=?";
		List<Course> clist=this.getHibernateTemplate().find(hql,exam.getCourse(),exam.getTnumber());
		return clist;
	}

	@Override
	public List<Course> CourseDone1(Exam exam) {
		// TODO Auto-generated method stub
		String hql="from Course course where course.course=? and course.tnumber=? and course.status=?";
		List<Course> clist=this.getHibernateTemplate().find(hql,exam.getCourse(),exam.getTnumber(),"已考试");
		return clist;
	}
	@Override
	public List<Course> CourseDone2(Exam exam) {
		// TODO Auto-generated method stub
		String hql="from Course course where course.course=? and course.tnumber=? and course.status=?";
		List<Course> clist=this.getHibernateTemplate().find(hql,exam.getCourse(),exam.getTnumber(),"已批阅");
		return clist;
	}

	@Override
	public Course CourseBysumtnum(String course,String snumber, String dnumber) {
		// TODO Auto-generated method stub
		Course cou=new Course();
		String hql="from Course course where course.course=? and course.snumber=? and course.tnumber=?";
		List<Course> clist=this.getHibernateTemplate().find(hql,course,snumber,dnumber);
		if(clist.size()>0) {
			cou=clist.get(0);
			return cou;
		}		
		return null;
	}

	@Override
	public void CourseUpdate(Course course) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(course);
	}

	@Override
	public List<Exam> getEndExam(String dnumber) {
		// TODO Auto-generated method stub
		Exam exam = new Exam();
		String hql="from Exam exam where exam.tnumber=? and exam.estatus=?";
		List<Exam> examlist=this.getHibernateTemplate().find(hql,dnumber,"已结束");
		if(examlist.size()>0) {
			return examlist;
		}
		return null;
	}

	@Override
	public void deanUpdate(Dean dean) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(dean);
	}

	@Override
	public List<Question> getQuestions(Integer eid) {
		// TODO Auto-generated method stub
		String hql="from Question question where question.eid=?";
		List<Question> quelist=this.getHibernateTemplate().find(hql,eid);
		if(quelist.size()>0) {
			return quelist;
		}
		return null;
	}

	@Override
	public Question getQuestionById(Integer qid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Question.class, qid);
	}

	@Override
	public void updateQuestion(Question q) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(q);
	}

	@Override
	public void DeleteQuestion(Question q) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(q);
	}

}
