package raisetech.StudentManagement.service;

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
    String newId = UUID.randomUUID().toString();
    studentDetail.getStudent().setId(newId);
    repository.registerStudent(studentDetail.getStudent());
    studentDetail.getStudentCourse().get(0).setStudentId(newId);
    repository.registerStudentCourse(studentDetail.getStudentCourse().get(0));
  }
}
