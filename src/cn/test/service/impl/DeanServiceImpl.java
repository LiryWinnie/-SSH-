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

import cn.test.dao.DeanDao;
import cn.test.domain.Bdegree;
import cn.test.domain.Course;
import cn.test.domain.Dean;
import cn.test.domain.Exam;
import cn.test.domain.Question;
import cn.test.service.DeanService;

public class DeanServiceImpl implements DeanService {
	private DeanDao deanDao;
	

	public DeanDao getDeanDao() {
		return deanDao;
	}
	public void setDeanDao(DeanDao deanDao) {
		this.deanDao = deanDao;
	}


	@Override
	public Dean findDean(Dean dean) {
		// TODO Auto-generated method stub
		
		Dean firstuser = this.deanDao.findDean(dean);
		return firstuser;
	}
	@Override
	public List<Exam> getExambyDnumber(String dnumber) {
		// TODO Auto-generated method stub
		return this.deanDao.getExambyDnumber(dnumber);
	}
	@Override
	public boolean addExam(Exam exam) {
		// TODO Auto-generated method stub
		return this.deanDao.addExam(exam);
	}
	@Override
	public Exam finById(Integer eid) {
		// TODO Auto-generated method stub
		return this.deanDao.findById(eid);
	}
	@Override
	public boolean savequestion(Question question) {
		// TODO Auto-generated method stub
		return this.deanDao.savequestion(question);
	}
	@Override
	public List<Exam> getExambyStatus(String status) {
		// TODO Auto-generated method stub
		return this.deanDao.getExambyStatus(status);
	}
	@Override
	public Bdegree getBdegree(Integer eid) {
		// TODO Auto-generated method stub
		return this.deanDao.getBdegree(eid);
	}
	@Override
	public void UpdateExam(Exam exam) {
		// TODO Auto-generated method stub
		this.deanDao.UpdateExam(exam);
	}
	@Override
	public void UpdateBdegree(Bdegree bdegree) {
		// TODO Auto-generated method stub
		this.deanDao.UpdateBdegree(bdegree);
	}
	@Override
	public boolean saveBdegree(Bdegree bdegree) {
		// TODO Auto-generated method stub
		return this.deanDao.saveBdegree(bdegree);
	}
	@Override
	public List<Exam> getDoingExam(String dnumber) {
		// TODO Auto-generated method stub
		return this.deanDao.getDoingExam(dnumber);
	}
	@Override
	public void CourseByExam(List<Exam> dexam) {
		// TODO Auto-generated method stub
		List<Integer> m=new ArrayList<Integer>();
		List<Integer> n=new ArrayList<Integer>();
		for(int i = 0;i<dexam.size();i++) {
			  List<Course> c1=this.deanDao.CourseByExam(dexam.get(i));
			  List<Course> c2=this.deanDao.CourseDone1(dexam.get(i));
			  List<Course> c3=this.deanDao.CourseDone2(dexam.get(i));
			  m.add(c1.size());
			  n.add(c2.size()+c3.size());
		}
		ActionContext.getContext().getSession().put("number1", m);	
		ActionContext.getContext().getSession().put("number2", n);
	}
	@Override
	public List<Course> ListCourseby(String course, String dnumber) {
		// TODO Auto-generated method stub
		Exam exam=new Exam();
		exam.setCourse(course);
	    exam.setTnumber(dnumber);
	    List<Course> c1=this.deanDao.CourseDone1(exam);
	    List<Course> c2=this.deanDao.CourseDone2(exam);
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
    				 if(que.getTextTrim().equalsIgnoreCase("¼ò´ðÌâ")) {
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
	public void updateXML(String name, List<String> blist, List<Integer> dlist) {
		// TODO Auto-generated method stub
		Integer eid=(Integer)ActionContext.getContext().getSession().get("eid");
		//System.out.println(" eid: "+eid);
		//System.out.println(" name: "+name);
		String id=eid+"";
		SAXReader reader=new SAXReader();
    	Document document=null;
    	Question q=new Question();
    	try {
    		File file=new File("E://exam/"+name+".xml");
    		if(file.exists()) {
    			System.out.println(" name: "+name);
    			document =reader.read(file);
    			Node node=document.selectSingleNode("/exams/exam[@eid='"+id+"']");
    			Element root=(Element)node;
    			int m=0;
    			for (Iterator i = root.elementIterator(); i.hasNext(); ) {
    				 Element que = (Element)i.next();
    				 System.out.println(que.getTextTrim());
    				 if(que.getTextTrim().equalsIgnoreCase("¼ò´ðÌâ")) {
    			      Element qscore=que.addElement("qscore");
    			      qscore.setText(blist.get(m));
    			      m++;
    				 }
    			}
    			root.element("status").setText("ÒÑÔÄ¾í");
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
	public Course CourseBysumtnum(String course,String snumber, String dnumber) {
		// TODO Auto-generated method stub
		return this.deanDao.CourseBysumtnum(course,snumber,dnumber);
	}
	@Override
	public void CourseUpdate(Course course) {
		// TODO Auto-generated method stub
		this.deanDao.CourseUpdate(course);
	}
	@Override
	public List<Exam> getEndExam(String dnumber) {
		// TODO Auto-generated method stub
		return this.deanDao.getEndExam(dnumber);
	}
	@Override
	public void deanUpdate(Dean dean) {
		// TODO Auto-generated method stub
		this.deanDao.deanUpdate(dean);
	}
	@Override
	public List<Question> getQuestions(Integer eid) {
		// TODO Auto-generated method stub
		return this.deanDao.getQuestions(eid);
	}
	@Override
	public Question getQuestionById(Integer qid) {
		// TODO Auto-generated method stub
		return this.deanDao.getQuestionById(qid);
	}
	@Override
	public void updateQuestion(Question q) {
		// TODO Auto-generated method stub
		this.deanDao.updateQuestion(q);
	}
	@Override
	public void DeleteQuestion(Question q) {
		// TODO Auto-generated method stub
		this.deanDao.DeleteQuestion(q);
	}
	
}
