package org.fastcampus.student_management.application.course;

import java.util.List;
import java.util.stream.Collectors;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.CourseList;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.CourseRepository;

public class CourseService {
  private final CourseRepository courseRepository;
  private final StudentService studentService;

  public CourseService(CourseRepository courseRepository, StudentService studentService) {
    this.courseRepository = courseRepository;
    this.studentService = studentService;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentService.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(), courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
    courseRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
    // TODO: 과제 구현 부분
    List<Course> courseDayOfWeek = courseRepository.getCourseDayOfWeek(dayOfWeek);

    // 요일에 해당하는 수업들을 반환해야함. 단, 학생 상태가 비활성화 상태이면 수업을 반환해서는 안됨
    return courseDayOfWeek.stream()
            .map(CourseInfoDto::new)
            .collect(Collectors.toList());
  }

  public void changeFee(String studentName, int fee) {
    // TODO: 과제 구현 부분
    CourseList courseList = new CourseList(courseRepository.getCourseListByStudent(studentName));
    courseList.changeAllCoursesFee(fee);
  }
}
