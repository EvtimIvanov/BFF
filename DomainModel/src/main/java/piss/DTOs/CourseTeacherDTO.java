package piss.DTOs;

import piss.entities.Course;
import piss.entities.Teacher;

public class CourseTeacherDTO {
    String course;
    String teacher;

    public CourseTeacherDTO(Course course ){
        this.course = course.getName();
        if(course.getTeacherOfTheCourse()!=null){
        Teacher teacher = course.getTeacherOfTheCourse();

        this.teacher = teacher.getName();
        }else{
            this.teacher="No teacher";
        }
    }

    public CourseTeacherDTO(String course, String teacher) {
        this.course = course;
        this.teacher = teacher;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
