package cn.test.service.impl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import cn.test.dao.StudentDao;
import cn.test.domain.Bdegree;
import cn.test.domain.Course;
import cn.test.domain.Exam;
import cn.test.domain.Question;
import cn.test.domain.Student;
import cn.test.service.StudentService;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
     
	 public void saveStudent(Student student){  
	        this.studentDao.saveStudent(student); 
	 }  
	    //查找验证用户信息   
	 public Student findStudent(Student student){  
	        //   
	        Student firstuser = this.studentDao.findStudent(student);  
	        //在UserDAO查询中已经判断了只有当用户名和密码都存在时才返回firstuser   
	        //所以在这里只用判断firstuser里面用户名或者密码中的一个是否存在就可以了   
	        return firstuser;
	    }

	@Override
	public List<Exam> findExam() {
		// TODO Auto-generated method stub
		return this.studentDao.findExam();
	}

	@Override
	public void joinExam(Student stu, Integer eid) {
		// TODO Auto-generated method stub
		Bdegree bdegree=new Bdegree();
		System.out.print(eid);
		bdegree=this.studentDao.findBdegreeByEid(eid);
		List<Question> qeasy= this.studentDao.getQuestion(eid,"易");
		List<Question> qmiddle= this.studentDao.getQuestion(eid,"中");
		List<Question> qdiff= this.studentDao.getQuestion(eid,"难");
	
		int a=(int)(bdegree.getEasy()*10);
		int b=(int)(bdegree.getMiddle()*10);
		int c=(int)(bdegree.getDifficult()*10);
		if(a<qeasy.size()){
			Collections.shuffle(qeasy);
			List<Question> qea = new ArrayList<Question>();
			for(int i=0;i<a;i++) {
				qea.add(qeasy.get(i));
			}
			qeasy=qea;
		}
		if(b<qmiddle.size()){
			Collections.shuffle(qmiddle);
			List<Question> qmi = new ArrayList<Question>();
			for(int i=0;i<b;i++) {
				qmi.add(qmiddle.get(i));
			}
			qmiddle=qmi;
		}
		if(c<qdiff.size()) {
			Collections.shuffle(qdiff);
			List<Question> qdi = new ArrayList<Question>();
			for(int i=0;i<c;i++) {
				qdi.add(qdiff.get(i));
			}
			qdiff=qdi;
		}
		qeasy.addAll(qmiddle);
		qeasy.addAll(qdiff);
		System.out.println(qeasy.size());		
		ActionContext.getContext().getSession().put("question", qeasy);
	}

	@Override
	public Exam findExamById(Integer eid) {
		// TODO Auto-generated method stub
		return this.studentDao.findExamById(eid);
	}

	@Override
	public List<String> findQanswer(List<Integer> clist) {
		// TODO Auto-generated method stub
		List<String> m=new ArrayList<String>();
		for(int i=0;i<clist.size();i++) {
			m.add(this.studentDao.findQanswer(clist.get(i)));
		}
		return m;
	}

	@Override
	public List<Integer> ExamXML(List<String> dlist, List<Question> blist, Exam exam, Student stu) {
		// TODO Auto-generated method stub
		return this.studentDao.ExamXML(dlist,blist,exam,stu);
	}

	@Override
	public List<Course> findCourseBySnumber(String snumber,String status) {
		// TODO Auto-generated method stub
		return this.studentDao.findCourseBySnumber(snumber,status);
	}

	@Override
	public List<Exam> findExamByCourse(List<Course> cou,String status) {
		// TODO Auto-generated method stub
		List<Exam> exam=new ArrayList<Exam>();
		for(int i=0;i<cou.size();i++) {
			List<Exam> e=this.studentDao.findExamByCourse(cou.get(i),status);
			if(e!=null)
			   exam.addAll(e);
		}
		if(exam.size()>0) {
			return exam;
		}
		return null;
	}

	@Override
	public List<Exam> findExamCourse(String course) {
		// TODO Auto-generated method stub
		List<Exam> exam=this.studentDao.findExamCourse(course);
		return exam;
	}

	@Override
	public void saveCourse(Course cou) {
		// TODO Auto-generated method stub
		this.studentDao.saveCourse(cou);
	}

	@Override
	public List<Course> findCourseByStudent(String snumber, String course) {
		// TODO Auto-generated method stub
		return this.studentDao.findCourseByStudent(snumber,course);
	}

	@Override
	public void CourseUpdate(Course course) {
		// TODO Auto-generated method stub
		this.studentDao.CourseUpdate(course);
	}

	@Override
	public List<Exam> GetExamCourse(List<Course> course1) {
		// TODO Auto-generated method stub
		return this.studentDao.GetExamCourse(course1);
	}

	@Override
	public List<Course> CourseBySnumber(String snumber) {
		// TODO Auto-generated method stub
		return this.studentDao.CourseBySnumber(snumber);
	}




}
