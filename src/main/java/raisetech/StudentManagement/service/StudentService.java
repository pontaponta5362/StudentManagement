package raisetech.StudentManagement.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    return repository.search();
  }

  public List<StudentCourse> searchStudentCourseList() {

    return repository.searchCourses();
  }

  @Transactional
  public void registerStudent(StudentDetail studentDetail) {
    repository.registerStudent(studentDetail.getStudent());
    StudentCourse course = studentDetail.getStudentCourse().get(0);
    course.setStudentId(studentDetail.getStudent().getId());
    LocalDateTime now = LocalDateTime.now();
    course.setStartDate(now);
    course.setExpectedEndDate(now.plusYears(1));
    repository.registerStudentCourse(course);
  }

  public StudentDetail searchStudent(int id) {
    Student student = repository.searchStudent(id);
    List<StudentCourse> studentCourse = repository.searchStudentCourseList(id);
   StudentDetail studentDetail = new StudentDetail();
   studentDetail.setStudent(student);
   studentDetail.setStudentCourse(studentCourse);
    return studentDetail;
  }
  @Transactional
  public void updateStudent(StudentDetail studentDetail){
    repository.updateStudent(studentDetail.getStudent());
    StudentCourse course = studentDetail.getStudentCourse().get(0);
    course.setStudentId(studentDetail.getStudent().getId());
    repository.updateStudentCourse(course);
  }
}
