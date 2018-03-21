package cn.test.service;

import java.util.List;

import cn.test.domain.Course;
import cn.test.domain.Exam;
import cn.test.domain.Question;
import cn.test.domain.Student;

public interface StudentService {
	 public void saveStudent(Student student);  
     
	 public Student findStudent(Student student);

	public List<Exam> findExam();

	public void joinExam(Student stu, Integer eid);

	public Exam findExamById(Integer eid);

	public List<String> findQanswer(List<Integer> clist);

	public List<Integer> ExamXML(List<String> dlist, List<Question> blist, Exam exam, Student stu);

	public List<Course> findCourseBySnumber(String snumber, String string);

	public List<Exam> findExamByCourse(List<Course> cou,String status);

	public List<Exam> findExamCourse(String course);

	public void saveCourse(Course cou);

	public List<Course> findCourseByStudent(String snumber, String course);

	public void CourseUpdate(Course course);

	public List<Exam> GetExamCourse(List<Course> course1);

	public List<Course> CourseBySnumber(String snumber);

}
