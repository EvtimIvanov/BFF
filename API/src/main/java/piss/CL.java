package piss;

import piss.entities.*;
//import com.paysafe.service.UserService;
import piss.services.CourseService;
import piss.services.StudentService;
import piss.services.TeacherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CL implements CommandLineRunner {


    private final TeacherService teacherService;
    private final CourseService courseService ;
    private final StudentService studentService ;

    public CL(TeacherService teacherService, CourseService courseService,
              StudentService studentService/* UserService userService*/) {
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) throws Exception {
      //  this.courseService.getTeacherCoursesWithAverageGrade("dancho");
//        Student student = studentService.create("Mieln", 12);
//        Course course = courseService.create("Hard course", 12);
//        Teacher teacher = teacherService.create("Dany teachera", String.valueOf(Degree.MSc));
//
//        Course course2 = courseService.create("Hard Course2", 12);
//
//        Student student2 = studentService.create("Milko", 17);
//
//        courseService.addTeacherToCourseByCourseId(teacher.getId(),course.getId());
//        courseService.addTeacherToCourseByCourseId(teacher.getId(),course2.getId());
//        courseService.addStudentToCourseByCourseId(student2.getId(),course.getId());
//        courseService.addStudentToCourseByCourseId(student.getId(),course.getId());
//        courseService.addStudentToCourseByCourseId(student.getId(),course2.getId());
//
//        courseService.addGradeToStudentInSpecificCourse(student.getId(),3.0,course.getId());
//        courseService.addGradeToStudentInSpecificCourse(student2.getId(),3.0,course.getId());
//        courseService.addGradeToStudentInSpecificCourse(student.getId(),6.0,course.getId());
//        courseService.addGradeToStudentInSpecificCourse(student.getId(),4.0,course.getId());



    }

}
