package raisetech.StudentManagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    List<Student> studentList = repository.search();
    List<Student> resultList = new ArrayList<>();
    for (Student student : studentList) {
      if (student.getAge() >= 30 && student.getAge() < 40){
        resultList.add(student);
    }
    }
    return resultList;
  }

  public List<StudentCourse> searchStudentCourseList() {
   List<StudentCourse> studentCourseList = repository.searchCourses();
   List<StudentCourse> resultCourseList = new ArrayList<>();
   for (StudentCourse studentCourse : studentCourseList) {
     if (studentCourse.getCourseName().equals("Javaプログラミングコース")){
       resultCourseList.add(studentCourse);
     }
   }
    return resultCourseList;
  }}
