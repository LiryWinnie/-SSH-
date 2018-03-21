package cn.test.service;

import java.util.List;

import cn.test.domain.Course;
import cn.test.domain.Bdegree;
import cn.test.domain.Exam;
import cn.test.domain.Question;
import cn.test.domain.Teacher;

public interface TeacherService {
	 public Teacher findTeacher(Teacher teacher);
	 
	 public boolean addExam(Exam exam);
	 
	 public List<Exam> getExambyTnumber(String tnumber);

	public Exam findById(Integer eid);

	public boolean savequestion(Question question);

	public boolean saveDegree(Bdegree bdegree);

	public void ExamUpdate(Exam exam);

	public List<Exam> getUnaudExam(String tnumber);

	public List<Exam> getUnpassExam(String tnumber);

	public Bdegree findBdegreeByEid(Integer eid);

	public void BdegreeUpdate(Bdegree bdegree);

	public List<Exam> getDoingExam(String tnumber);

	public void CourseByExam(List<Exam> unaud);

	public List<Course> ListCourseby(String course, String tnumber);

	public void xmlaudit(String name);

	public void updateXML(String name, List<String> blist, List<Integer> dlist);

	public int stuScore(String name);

	public Course CourseBysumtnum(String course,String snumber, String tnumber);

	public void UpdateCourse(Course course);

	public List<Exam> getEndExam(String tnumber);

	public String findKeybyID(Integer tid);

	public void teacherUpdate(Teacher tea);

	public List<Question> getQuestions(Integer eid);

	public Question getQuestionById(Integer qid);

	public void updateQuestion(Question q);

	public void DeleteQuestion(Question q); 
	 
}
