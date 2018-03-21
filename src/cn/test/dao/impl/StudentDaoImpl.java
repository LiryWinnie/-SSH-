package cn.test.dao.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.test.dao.StudentDao;
import cn.test.domain.Bdegree;
import cn.test.domain.Course;
import cn.test.domain.Exam;
import cn.test.domain.Question;
import cn.test.domain.Student;

public class StudentDaoImpl extends HibernateDaoSupport implements StudentDao{
	
	@Override
	public Student findStudent(Student student) {
		Student firstuser = new Student();  
        //HQL查询语句   
        String hql = "from Student student where student.snumber=? and student.spassword=?";  
        //将查询出的结果放到List   
        List<Student> studentlist = this.getHibernateTemplate().find(hql,student.getSnumber(),student.getSpassword());  
        //判断是否有查询结果，换句话说就是判断用户是否存在   
        if(studentlist.size()>0){  
        //取出查询结果的第一个值，理论上数据库是没有重复的用户信息   
        firstuser = studentlist.get(0);  
        return firstuser; 
        }  
        return null; 
	}
    
	public void saveStudent(Student student) {
		this.getHibernateTemplate().save(student); 
	}

	@Override
	public List<Exam> findExam() {
		// TODO Auto-generated method stub
		String hql = "from Exam exam where exam.estatus=?";
		List<Exam> elist=this.getHibernateTemplate().find(hql,"已发布");
		if(elist.size()>0) {
			return elist;
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
	public List<Question> getQuestion(Integer eid,String qdegree) {
		// TODO Auto-generated method stub
		String hql = "from Question question where question.eid=? and question.qdegree=?";
		List<Question> qlist=this.getHibernateTemplate().find(hql,eid,qdegree);
		if(qlist.size()>0) {
			return qlist;
		}
		return null;
	}

	@Override
	public Exam findExamById(Integer eid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Exam.class, eid);
	}

	@Override
	public String findQanswer(Integer qid) {
		// TODO Auto-generated method stub
		String hql = "from Question question where question.qid=?";
		List<Question> qlist=this.getHibernateTemplate().find(hql,qid);
		if(qlist.size()>0) {
			Question m=qlist.get(0);
			return m.getQanswer();
		}
		return null;
	}

	@Override
	public List<Integer> ExamXML(List<String> dlist, List<Question> blist, Exam exam, Student stu) {
		// TODO Auto-generated method stub
		int value=0;
		List<Integer> slist=new ArrayList<Integer>();
    	File file =new File(path+stu.getSnumber()+"_"+exam.getTnumber()+".xml");
    	if(!file.exists()) {
    		createXML(stu.getSnumber()+"_"+exam.getTnumber());
    		file = new File(path+stu.getSnumber()+"_"+exam.getTnumber()+".xml");
    	}
    	Document document=null;
    	try {
    		SAXReader reader=new SAXReader();
    		document = reader.read(file);
    		Element rootElement = document.getRootElement();
    		Element cElement = rootElement.addElement("exam");
    		int result=0;
    		int a=0;
    		for(int i=0;i<dlist.size();i++) {
    			Element examElement = cElement.addElement("question");
    			examElement.addAttribute("qtype", blist.get(i).getQtype());
    			Element content=examElement.addElement("qcontent");
    			content.setText(blist.get(i).getQcontent());
    			Element degree=examElement.addElement("qdegree");
    			degree.setText(blist.get(i).getQdegree());
    			Element qid=examElement.addElement("qid");
    			qid.setText(blist.get(i).getQid()+"");
    			Element stuans=examElement.addElement("sanswer");
    			stuans.setText(blist.get(i).getQanswer());
    			char m=blist.get(i).getQtype().charAt(0);
    			if(m!="简答题".charAt(0)) {
    				Element qanswer=examElement.addElement("qanswer");
    				qanswer.setText(dlist.get(i));
    				Element qscore=examElement.addElement("qscore");
    				if(dlist.get(i).equalsIgnoreCase(blist.get(i).getQanswer())) {
    					qscore.setText(blist.get(i).getQscore()+"");
    					result=result+blist.get(i).getQscore();
    				}else {
    					qscore.setText(0+"");
    				}
    			}else {
    				a=1;
    			}
    			examElement.setText(blist.get(i).getQtitle());
    		}
    		cElement.addAttribute("eid",exam.getEid()+"");
    		Element status=cElement.addElement("status");
    		if(a==1) {
    		    status.setText("未阅卷");
    		    slist.add(0);
    		}
    		else {
    			status.setText("已阅卷");
    			Element score=cElement.addElement("score");
    			score.setText(result+"");
    			slist.add(1);
    			slist.add(result);    			
    		}
    		}catch(Exception e) {
        		e.printStackTrace();
        	}
        	
        	value=writeXML(document,file);
        	return slist;
	}
	
	public static String path = "E:/exam/";
	public static void createXML(String coursename) {
    	Document document = DocumentHelper.createDocument();
    	document.addElement("exams");
    	File file=new File(path+coursename+".xml");
    	writeXML(document,file);
    }

	private static int writeXML(Document document,File file) {
    	int value=0;
    	OutputFormat format=OutputFormat.createPrettyPrint();
    	format.setEncoding("UTF-8");
    	XMLWriter writer=null;
    	try {
    		writer =new XMLWriter(new FileOutputStream(file),format.createPrettyPrint());
    		writer.write(document);
    		value=1;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return value;
    }

	@Override
	public List<Course> findCourseBySnumber(String snumber,String status) {
		// TODO Auto-generated method stub
		String hql = "from Course course where course.snumber=? and course.status=?";
		List<Course> clist=this.getHibernateTemplate().find(hql,snumber,status);
		if(clist.size()>0) {
			return clist;
		}
		return null;
	}

	@Override
	public List<Exam> findExamByCourse(Course course,String status) {
		// TODO Auto-generated method stub
		String hql = "from Exam exam where exam.course=? and exam.tnumber=? and exam.estatus=?";
		List<Exam> elist=this.getHibernateTemplate().find(hql,course.getCourse(),course.getTnumber(),status);
		if(elist.size()>0) {
			return elist;
		}
		return null;
	}

	@Override
	public List<Exam> findExamCourse(String course) {
		// TODO Auto-generated method stub
		String hql = "from Exam exam where exam.course=?";
		List<Exam> elist=this.getHibernateTemplate().find(hql,course);
		if(elist.size()>0) {
			return elist;
		}
		return null;
	}

	@Override
	public void saveCourse(Course cou) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(cou);
	}

	@Override
	public List<Course> findCourseByStudent(String snumber, String course) {
		// TODO Auto-generated method stub
		String hql = "from Course course where course.snumber=? and course.course=?";
		List<Course> clist=this.getHibernateTemplate().find(hql,snumber,course);
		if(clist.size()>0) {
			return clist;
		}
		return null;
	}

	@Override
	public void CourseUpdate(Course course) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(course);
	}

	@Override
	public List<Exam> GetExamCourse(List<Course> course1) {
		// TODO Auto-generated method stub
		List<Exam> ex=new ArrayList<Exam>();
		if(course1.size()!=0)
		for(int i=0;i<course1.size();i++) {
		String hql = "from Exam exam where exam.course=? and exam.tnumber=?";
		List<Exam> elist=this.getHibernateTemplate().find(hql,course1.get(i).getCourse(),course1.get(i).getTnumber());
		  if(elist.size()>0) {
			ex.add(elist.get(0));
		  }
		}
		return ex;
	}

	@Override
	public List<Course> CourseBySnumber(String snumber) {
		// TODO Auto-generated method stub
		String hql = "from Course course where course.snumber=?";
		List<Course> clist=this.getHibernateTemplate().find(hql,snumber);
		if(clist.size()>0) {
			return clist;
		}
		return null;
	}

}
