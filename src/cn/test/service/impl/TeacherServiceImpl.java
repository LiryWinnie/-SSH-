package cn.test.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.opensymphony.xwork2.ActionContext;

import cn.test.dao.TeacherDao;
import cn.test.domain.Bdegree;
import cn.test.domain.Course;
import cn.test.domain.Exam;
import cn.test.domain.Question;
import cn.test.domain.Student;
import cn.test.domain.Teacher;
import cn.test.service.TeacherService;

public class TeacherServiceImpl implements TeacherService {
    private TeacherDao teacherDao;

	public TeacherDao getTeacherDao() {
		return teacherDao;
	}

	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}
    
	@Override
	public Teacher findTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		Teacher firstuser = this.teacherDao.findTeacher(teacher);
		
		return firstuser;
	}

	@Override
	public boolean addExam(Exam exam) {
		// TODO Auto-generated method stub
		return this.teacherDao.addExam(exam);
	}
 
	public List<Exam> getExambyTnumber(String tnumber) {
		return this.teacherDao.getExambyTnumber(tnumber);
	}

	@Override
	//业务层根据试卷id查询试卷
	public Exam findById(Integer eid) {
		// TODO Auto-generated method stub
		return teacherDao.findById(eid);
	}

	@Override
	public boolean savequestion(Question question) {
		// TODO Auto-generated method stub
		boolean save=teacherDao.savequestion(question);
		return save;
	}

	@Override
	public boolean saveDegree(Bdegree bdegree) {
		// TODO Auto-generated method stub
		return this.teacherDao.saveDegree(bdegree);
	}

	@Override
	public void ExamUpdate(Exam exam) {
		// TODO Auto-generated method stub
		this.teacherDao.ExamUpdate(exam);
	}

	@Override
	public List<Exam> getUnaudExam(String tnumber) {
		// TODO Auto-generated method stub
		return this.teacherDao.getUnaudExam(tnumber);
	}

	@Override
	public List<Exam> getUnpassExam(String tnumber) {
		// TODO Auto-generated method stub
		return this.teacherDao.getUnpassExam(tnumber);
	}

	@Override
	public Bdegree findBdegreeByEid(Integer eid) {
		// TODO Auto-generated method stub
		return this.teacherDao.findBdegreeByEid(eid);
	}

	@Override
	public void BdegreeUpdate(Bdegree bdegree) {
		// TODO Auto-generated method stub
		this.teacherDao.BdegreeUpdate(bdegree);
	}

	@Override
	public List<Exam> getDoingExam(String tnumber) {
		// TODO Auto-generated method stub
		return this.teacherDao.getDoingExam(tnumber);
	}

	@Override
	public void CourseByExam(List<Exam> unaud) {
		// TODO Auto-generated method stub
		List<Integer> m=new ArrayList<Integer>();
		List<Integer> n=new ArrayList<Integer>();
		for(int i = 0;i<unaud.size();i++) {
			  List<Course> c1=this.teacherDao.CourseByExam(unaud.get(i));
			  List<Course> c2=this.teacherDao.CourseDone1(unaud.get(i));
			  List<Course> c3=this.teacherDao.CourseDone2(unaud.get(i));
			  m.add(c1.size());
			  n.add(c2.size()+c3.size());
		}
		ActionContext.getContext().getSession().put("number1", m);	
		ActionContext.getContext().getSession().put("number2", n);	
	}


	@Override
	public List<Course> ListCourseby(String course, String tnumber) {
		// TODO Auto-generated method stub
		Exam exam=new Exam();
		exam.setCourse(course);
	    exam.setTnumber(tnumber);
	    List<Course> c1=this.teacherDao.CourseDone1(exam);
	    List<Course> c2=this.teacherDao.CourseDone2(exam);
	    c1.addAll(c2);
		return c1;
	}

	@Override
	public void xmlaudit(String name) {
		// TODO Auto-generated method stub
		Integer eid=(Integer)ActionContext.getContext().getSession().get("eid");
		String id=eid+"";
		SAXReader reader=new SAXReader();
    	Document document=null;
    	List<Question> question=new ArrayList<Question>();
    	Question q=new Question();
    	try {
    		File file=new File("E://exam/"+name+".xml");
    		if(file.exists()) {
    			document =reader.read(file);
    			Node node=document.selectSingleNode("/exams/exam[@eid='"+id+"']");
    			Element root=(Element)node;
    			for (Iterator i = root.elementIterator(); i.hasNext(); ) {
    				 Element que = (Element)i.next();
    				 //System.out.println(que.getTextTrim());
    				 if(que.getTextTrim().equalsIgnoreCase("简答题")) {
    				     //System.out.println(que.getQualifiedName());
    			          q.setQcontent(que.elementText("qcontent"));
    			          q.setQanswer(que.elementText("sanswer"));
    			          q.setQid(Integer.parseInt(que.elementText("qid")));
    			          question.add(q);
    				 }
    			}
    			writeXML(document, file);
    		}
    	}catch(DocumentException e) {
    		e.printStackTrace();
    	}
    	ActionContext.getContext().getSession().put("question", question);
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
	public void updateXML(String name,List<String> blist, List<Integer> dlist) {
		// TODO Auto-generated method stub
		Integer eid=(Integer)ActionContext.getContext().getSession().get("eid");
		String id=eid+"";
		SAXReader reader=new SAXReader();
    	Document document=null;
    	Question q=new Question();
    	try {
    		File file=new File("E://exam/"+name+".xml");
    		if(file.exists()) {
    			document =reader.read(file);
    			Node node=document.selectSingleNode("/exams/exam[@eid='"+id+"']");
    			Element root=(Element)node;
    			int m=0;
    			for (Iterator i = root.elementIterator(); i.hasNext(); ) {
    				 Element que = (Element)i.next();
    				 //System.out.println(que.getTextTrim());
    				 if(que.getTextTrim().equalsIgnoreCase("简答题")) {
    			      Element qscore=que.addElement("qscore");
    			      qscore.setText(blist.get(m));
    			      m++;
    				 }
    			}
    			root.element("status").setText("已阅卷");
    			writeXML(document, file);
    		}
    	}catch(DocumentException e) {
    		e.printStackTrace();
    	}
	}

	@Override
	public int stuScore(String name) {
		// TODO Auto-generated method stub
		Integer eid=(Integer)ActionContext.getContext().getSession().get("eid");
		String id=eid+"";
		SAXReader reader=new SAXReader();
    	Document document=null;
    	int result=0;
    	List<String> score=new ArrayList<String>();
    	Question q=new Question();
    	try {
    		File file=new File("E://exam/"+name+".xml");
    		if(file.exists()) {
    			document =reader.read(file);
    			Node node1=document.selectSingleNode("/exams/exam[@eid='"+id+"']");
    			Element root=(Element)node1;
    			for (Iterator i =root.elementIterator(); i.hasNext(); ) {
    				Element element = (Element)i.next();
    				if(element.getQualifiedName().equalsIgnoreCase("question"))
                         score.add(element.elementText("qscore"));
    			}
    			System.out.println(score.size());
    	    	for(int k=0;k<score.size();k++) {
    	    		int re=Integer.parseInt(score.get(k));
    	    		result=re+result;
    	    	}
    	    	Node node2=document.selectSingleNode("/exams/exam[@eid='"+id+"']");
    	    	Element root2=(Element)node1;
    	    	Element score1=root2.addElement("score");
    	    	score1.setText(result+"");
    	    	writeXML(document, file);
    		}
    	}catch(DocumentException e) {
    		e.printStackTrace();
    	}
		return result;
	}

	@Override
	public Course CourseBysumtnum(String course,String snumber, String tnumber) {
		// TODO Auto-generated method stub
		return this.teacherDao.CourseBysumtnum(course,snumber,tnumber);
	}

	@Override
	public void UpdateCourse(Course course) {
		// TODO Auto-generated method stub
		this.teacherDao.UpdateCourse(course);
	}

	@Override
	public List<Exam> getEndExam(String tnumber) {
		// TODO Auto-generated method stub
		return this.teacherDao.getEndExam(tnumber);
	}

	@Override
	public String findKeybyID(Integer tid) {
		// TODO Auto-generated method stub
		Teacher tea=this.teacherDao.findTeacherbyID(tid);
		return tea.getTpassword();
	}

	@Override
	public void teacherUpdate(Teacher tea) {
		// TODO Auto-generated method stub
		this.teacherDao.teacherUpdate(tea);
	}

	@Override
	public List<Question> getQuestions(Integer eid) {
		// TODO Auto-generated method stub
		return this.teacherDao.getQuestions(eid);
	}

	@Override
	public Question getQuestionById(Integer qid) {
		// TODO Auto-generated method stub
		return this.teacherDao.getQuestionById(qid);
	}

	@Override
	public void updateQuestion(Question q) {
		// TODO Auto-generated method stub
		this.teacherDao.updateQuestion(q);
	}

	@Override
	public void DeleteQuestion(Question q) {
		// TODO Auto-generated method stub
		this.teacherDao.DeleteQuestion(q);
	}
	

}
