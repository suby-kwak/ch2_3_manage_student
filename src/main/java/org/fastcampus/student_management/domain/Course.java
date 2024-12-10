package org.fastcampus.student_management.domain;

public class Course {
  private final Student student;
  private final String courseName;
  private CourseFee fee;
  private final DayOfWeek dayOfWeek;
  private final Long courseTime;

  // 과연 Course에 CourseInfoDTO를 인자로 받는 게 옳은가?
  // answer : 코드 가독성이 올라가긴 하지만 의존성 역전이 일어나기 때문에 **DDD 관점으로 보면** 틀린 것으로 보인다. 생성을 해야한다면 Builder를 써보는건 어떤가 싶다.
  public Course(Student student, String courseName, int fee, DayOfWeek dayOfWeek, Long courseTime) {
    if (student == null) {
      throw new IllegalArgumentException("학생은 필수 입력값입니다.");
    }

    this.student = student;
    this.courseName = courseName;
    this.fee = new CourseFee(fee);
    this.dayOfWeek = dayOfWeek;
    this.courseTime = courseTime;
  }

  public String getCourseName() {
    return courseName;
  }

  public boolean isSameDay(DayOfWeek dayOfWeek) {
    return this.dayOfWeek.equals(dayOfWeek);
  }

  public boolean isActivateUser() {
    return student.isActivate();
  }

  public String getStudentName() {
    return student.getName();
  }

  public int getFee() {
    return this.fee.getFee();
  }

  public DayOfWeek getDayOfWeek() {
    return dayOfWeek;
  }

  public Long getCourseTime() {
    return courseTime;
  }

  public void changeFee(int fe) {
    this.fee.changeFee(fe);
  }
}
