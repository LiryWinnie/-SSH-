package cn.test.dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.test.dao.TeacherDao;
import cn.test.domain.Bdegree;
import cn.test.domain.Course;
import cn.test.domain.Exam;
import cn.test.domain.Question;
import cn.test.domain.Teacher;

public class TeacherDaoImpl extends HibernateDaoSupport implements TeacherDao {
	
	@Override
	public Teacher findTeacher(Teacher teacher) {
		Teacher firstuser = new Teacher();  

        String hql = "from Teacher teacher where teacher.tnumber=? and teacher.tpassword=?";  

        List<Teacher> teacherlist = this.getHibernateTemplate().find(hql,teacher.getTnumber(),teacher.getTpassword());  

        if(teacherlist.size()>0){  

        firstuser = teacherlist.get(0);  
        return firstuser; 
        }  
        return null; 
	}
	
	public boolean addExam(Exam exam) {
		if(exam.getCourse()==""||exam.getEname()==""||exam.getTime()==0) {
			return false;
		}
		else {
		     this.getHibernateTemplate().save(exam); 
		     return true;
		}
	}

	public List<Exam> getExambyTnumber(String tnumber) {
		Exam exam = new Exam();
		String hql="from Exam exam where exam.tnumber=?";
		List<Exam> examlist=this.getHibernateTemplate().find(hql,tnumber);
		if(examlist.size()>0) {
			return examlist;
		}
		return null;
	}

	@Override
	public Exam findById(Integer eid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Exam.class, eid);
	}

	@Override
	public boolean savequestion(Question question) {
		if(question.getQtitle()==""||question.getQcontent()==""||question.getQanswer()==""||question.getQscore()==0) {
			return false;
		}
		else {
		this.getHibernateTemplate().save(question); 
		return true;
		}
		
	}

	public float GetByIdDegree(Integer eid,String qdegree) {
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
	public boolean saveDegree(Bdegree bdegree) {
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

	@Override
	public void ExamUpdate(Exam exam) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(exam);
	}

	@Override
	public List<Exam> getUnaudExam(String tnumber) {
		// TODO Auto-generated method stub
		Exam exam = new Exam();
		String hql="from Exam exam where exam.tnumber=? and exam.estatus=?";
		List<Exam> examlist=this.getHibernateTemplate().find(hql,tnumber,"待审核");
		if(examlist.size()>0) {
			return examlist;
		}
		return null;
	}

	@Override
	public List<Exam> getUnpassExam(String tnumber) {
		// TODO Auto-generated method stub
		Exam exam = new Exam();
		String hql="from Exam exam where exam.tnumber=? and exam.estatus=?";
		List<Exam> examlist=this.getHibernateTemplate().find(hql,tnumber,"未通过");
		if(examlist.size()>0) {
			return examlist;
		}
		return null;
	}

	@Override
	public Bdegree findBdegreeByEid(Integer eid) {
		// TODO Auto-generated method stub
		Bdegree bdegree=new Bdegree();
		String hql="from Bdegree bdegree where bdegree.eid=?";
		List<Bdegree> blist=this.getHibernateTemplate().find(hql,eid);
		if(blist.size()>0) {
			bdegree=blist.get(0);
			return bdegree;
		}		
		return null;
	}

	@Override
	public void BdegreeUpdate(Bdegree bdegree) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(bdegree);
	}

	@Override
	public List<Exam> getDoingExam(String tnumber) {
		// TODO Auto-generated method stub
		Exam exam = new Exam();
		String hql="from Exam exam where exam.tnumber=? and exam.estatus=?";
		List<Exam> examlist=this.getHibernateTemplate().find(hql,tnumber,"已发布");
		if(examlist.size()>0) {
			return examlist;
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
	public Course CourseBysumtnum(String course,String snumber, String tnumber) {
		// TODO Auto-generated method stub
		Course cou=new Course();
		String hql="from Course course where course.snumber=? and course.tnumber=?";
		List<Course> clist=this.getHibernateTemplate().find(hql,snumber,tnumber);
		if(clist.size()>0) {
			cou=clist.get(0);
			return cou;
		}		
		return null;
	}

	@Override
	public void UpdateCourse(Course course) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(course);
	}

	@Override
	public List<Exam> getEndExam(String tnumber) {
		// TODO Auto-generated method stub
		Exam exam = new Exam();
		String hql="from Exam exam where exam.tnumber=? and exam.estatus=?";
		List<Exam> examlist=this.getHibernateTemplate().find(hql,tnumber,"已结束");
		if(examlist.size()>0) {
			return examlist;
		}
		return null;
	}

	@Override
	public Teacher findTeacherbyID(Integer tid) {
		// TODO Auto-generated method stub
		Teacher firstuser = new Teacher();  
        String hql = "from Teacher teacher where teacher.tid=?";  
        List<Teacher> teacherlist = this.getHibernateTemplate().find(hql,tid);  
        if(teacherlist.size()>0){  
        firstuser = teacherlist.get(0);  
        return firstuser; 
        }  
        return null; 
	}

	@Override
	public void teacherUpdate(Teacher tea) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(tea);
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
