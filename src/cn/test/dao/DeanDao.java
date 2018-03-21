package cn.test.dao;

import java.util.List;

import cn.test.domain.Bdegree;
import cn.test.domain.Course;
import cn.test.domain.Dean;
import cn.test.domain.Exam;
import cn.test.domain.Question;

public interface DeanDao {
     public Dean findDean(Dean dean);

	public List<Exam> getExambyDnumber(String dnumber);

	public boolean addExam(Exam exam);

	public Exam findById(Integer eid);

	public boolean savequestion(Question question);

	public List<Exam> getExambyStatus(String status);

	public Bdegree getBdegree(Integer eid);

	public void UpdateExam(Exam exam);

	public void UpdateBdegree(Bdegree bdegree);

	public boolean saveBdegree(Bdegree bdegree);

	public List<Exam> getDoingExam(String dnumber);

	public List<Course> CourseByExam(Exam exam);

	public List<Course> CourseDone1(Exam exam);

	public List<Course> CourseDone2(Exam exam);

	public Course CourseBysumtnum(String course,String snumber, String dnumber);

	public void CourseUpdate(Course course);

	public List<Exam> getEndExam(String dnumber);

	public void deanUpdate(Dean dean);

	public List<Question> getQuestions(Integer eid);

	public Question getQuestionById(Integer qid);

	public void updateQuestion(Question q);

	public void DeleteQuestion(Question q);
}
