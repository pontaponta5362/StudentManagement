package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;


@Mapper

public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<StudentCourse> searchCourses();

  @Insert("INSERT INTO students (id, name, kana, nickname, email, region, age, gender, remark) " +
      "VALUES (#{id}, #{name}, #{kana}, #{nickname}, #{email}, #{region}, #{age}, #{gender}, #{remark})")
  void registerStudent(Student student);

  @Insert("INSERT INTO students_courses (student_id, course_name) " +
      "VALUES (#{studentId}, #{courseName})")
  void registerStudentCourse(StudentCourse studentCourse);
}
