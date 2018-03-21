package cn.test.dao;

import java.util.List;

import cn.test.domain.Bdegree;
import cn.test.domain.Course;
import cn.test.domain.Exam;
import cn.test.domain.Question;
import cn.test.domain.Student;

public interface StudentDao {
    public Student findStudent(Student student);
    
    public void saveStudent(Student student);

	public List<Exam> findExam();

	public Bdegree findBdegreeByEid(Integer eid);

	public List<Question> getQuestion(Integer eid, String qdegree);

	public Exam findExamById(Integer eid);

	public String findQanswer(Integer qid);

	public List<Integer> ExamXML(List<String> dlist, List<Question> blist, Exam exam, Student stu);

	public List<Course> findCourseBySnumber(String snumber, String status);

	public List<Exam> findExamByCourse(Course course,String status);

	public List<Exam> findExamCourse(String course);

	public void saveCourse(Course cou);

	public List<Course> findCourseByStudent(String snumber, String course);

	public void CourseUpdate(Course course);

	public List<Exam> GetExamCourse(List<Course> course1);

	public List<Course> CourseBySnumber(String snumber);

}
