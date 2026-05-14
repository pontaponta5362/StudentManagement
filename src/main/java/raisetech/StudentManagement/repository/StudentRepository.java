package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;


@Mapper

public interface StudentRepository {

  @Select("SELECT * FROM students WHERE isDeleted = 0")
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<StudentCourse> searchCourses();

  @Insert("INSERT INTO students (name, kana, nickname, email, region, age, gender, remark) " +
      "VALUES (#{name}, #{kana}, #{nickname}, #{email}, #{region}, #{age}, #{gender}, #{remark})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);



  @Select("SELECT * FROM students WHERE id = #{id}")
  Student searchStudent(int id);

  @Select("SELECT  * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentCourse> searchStudentCourseList(int studentId);

  @Update("UPDATE students SET name = #{name}, kana = #{kana}, nickname = #{nickname}, email = #{email}, region = #{region}, age = #{age}, gender = #{gender}, remark = #{remark}, isDeleted = #{isDeleted} WHERE id = #{id}")
  void updateStudent(Student student);

  @Update("UPDATE students_courses SET course_name = #{courseName} WHERE student_id = #{studentId}")
  void updateStudentCourse(StudentCourse studentCourse);

  @Insert("INSERT INTO students_courses (student_id, course_name, start_date, expected_end_date)" +
  "VALUES (#{studentId}, #{courseName}, #{startDate}, #{expectedEndDate})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudentCourse(StudentCourse studentCourse);
}
