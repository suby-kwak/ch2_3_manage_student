package org.fastcampus.student_management.application.course;

import java.util.List;
import java.util.stream.Collectors;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.course.interfaces.CourseCommandRepository;
import org.fastcampus.student_management.application.course.interfaces.CourseQueryRepository;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.CourseList;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;

public class CourseService {
  private final CourseCommandRepository courseCommandRepository;
  private final CourseQueryRepository courseQueryRepository;
  private final StudentService studentService;

  public CourseService(CourseCommandRepository courseCommandRepository, CourseQueryRepository courseQueryRepository, StudentService studentService) {
    this.courseCommandRepository = courseCommandRepository;
    this.courseQueryRepository = courseQueryRepository;
    this.studentService = studentService;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentService.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(), courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
    courseCommandRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
    // TODO: 과제 구현 부분
    List<Course> courseDayOfWeek = courseQueryRepository.getCourseDayOfWeek(dayOfWeek);

    // 요일에 해당하는 수업들을 반환해야함. 단, 학생 상태가 비활성화 상태이면 수업을 반환해서는 안됨
    return courseDayOfWeek.stream()
            .map(CourseInfoDto::new)
            .collect(Collectors.toList());
  }

  public void changeFee(String studentName, int fee) {
    // TODO: 과제 구현 부분
    CourseList courseList = new CourseList(courseQueryRepository.getCourseListByStudent(studentName));
    courseList.changeAllCoursesFee(fee);
  }
}
