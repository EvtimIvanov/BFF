//package com.paysafe.services;
//
//import com.paysafe.*;
//
//import com.paysafe.entities.Course;
//import com.paysafe.entities.Student;
//import com.paysafe.entities.Teacher;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Set;
//import java.util.TreeSet;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class CourseServiceTest {
//
//    private final CourseService courseService = new CourseService();
//    private final StudentService studentService = new StudentService();
//    private final TeacherService teacherService = new TeacherService();
//
//
//    @BeforeEach
//    public void setup() {
//
////        courseService.clearDataStore();
////        studentService.clearDataStore();
////        teacherService.clearDataStore();
//    }
//
//    @Test
//    public void shouldCreateCourseWithValidData() {
//
//
//        String name = "Math";
//        int totalHours = 130;
//
//        Course course = courseService.create(name, totalHours);
//
//       // assertEquals(1, course.getId());
//        assertEquals(name, course.getName());
//        assertEquals(totalHours, course.getTotalHours());
//
//    }
//
//    @Test
//    public void shouldThrowExceptionWithNegativeHours() {
//
//        assertThrows(IllegalArgumentException.class,
//                () -> courseService.create("Course", -5));
//
//    }
//
//    @Test
//    public void shouldThrowExceptionWithHoursMoreThan300() {
//
//        assertThrows(IllegalArgumentException.class,
//                () -> courseService.create("Course", 301));
//    }
//
//    @Test
//    public void shouldThrowExceptionWithNullName() {
//
//        assertThrows(IllegalArgumentException.class,
//                () -> courseService.create(null, 301));
//    }
//
//    @Test
//    public void shouldAddTeacherToExistingCourse() {
//        Course course = courseService.create("First", 200);
//        Teacher teacher = teacherService.create("Ivan", "BSc");
//        courseService.addTeacherToCourseByCourseId(teacher.getId(), course.getId());
//
//    }
//
////    @Test
////    public void shouldThrowExceptionWithNullpointerTeacher() {
////        Course course = courseService.create("CourseWithNullPointerTeacher", 200);
////
////        assertThrows(IllegalArgumentException.class,
////                () -> courseService.addTeacherToCourseByCourseId(null, course.getId()));
////    }
//
////    @Test
////    public void shouldThrowExceptionWithInvalidCourseIdWhenAddingTeacher() {
////        Teacher teacher = teacherService.create("Ivan", "BSc");
////        assertThrows(NotExistingDataException.class,
////                () -> courseService.addTeacherToCourseByCourseId(teacher, -1));
////    }
//
////    @Test
////    public void shouldThrowExceptionWhenAddingStudentWithNotExistingCourseId() {
////        Student student = studentService.create("Ivan", 15);
////        assertThrows(NotExistingDataException.class,
////                () -> courseService.addStudentToCourseByCourseId(student, -1));
////    }
//
////    @Test
////    public void shouldThrowExceptionWhenAddingNullStudentToCourse() {
////        Course course = courseService.create("Course1", 130);
////        assertThrows(IllegalArgumentException.class,
////                () -> courseService.addStudentToCourseByCourseId(null, course.getId()));
////    }
//
//    @Test
//    public void shouldAddStudentToCourseWithValidData() {
//        Course course = courseService.create("Course1", 130);
//        Student student = studentService.create("Ivan", 15);
//        courseService.addStudentToCourseByCourseId(student.getId(), course.getId());
//
//        int sizeOfCourse = course.getAllStudents().size();
//
//        Set<Student> allStudents = new TreeSet<>(course.getAllStudents());
//       // assertEquals(1, sizeOfCourse);
//        assertTrue(allStudents.contains(student));
//    }
//
////    @Test
////    public void shouldThrowExceptionWhenAddGradeWithNotExistingCourseId() {
////        Student ivan = studentService.create("Ivan", 13);
////        assertThrows(NotExistingDataException.class,
////                () -> courseService.addGradeToStudentInSpecificCourse(ivan, 4.0, -1));
////
////    }
//
//
//    @Test
//    public void shouldThrowExceptionWhenAddingGradeLessThan2() {
//        Course course = courseService.create("Course1", 130);
//        Student ivan = studentService.create("Ivan", 13);
//        assertThrows(IllegalArgumentException.class,
//                () -> courseService.addGradeToStudentInSpecificCourse(ivan, 1.0, course.getId()));
//    }
//
//    @Test
//    public void shouldThrowExceptionWhenAddingGradeGreaterThan6() {
//        Course course = courseService.create("Course1", 130);
//        Student ivan = studentService.create("Ivan", 13);
//        assertThrows(IllegalArgumentException.class,
//                () -> courseService.addGradeToStudentInSpecificCourse(ivan, 7.0, course.getId()));
//    }
//
//    @Test
//    public void shouldThrowExceptionWhenAddingGradeToNullpointerStudent() {
//        Course course = courseService.create("Course1", 130);
//        assertThrows(IllegalArgumentException.class,
//                () -> courseService.addGradeToStudentInSpecificCourse(null,
//                        4.0, course.getId()));
//    }
//
////    @Test
////    public void shouldThrowExpectionWhenAddingGradeToStudentNotInCourse() {
////        Course course = courseService.create("Course1", 130);
////        Student studen1 = studentService.create("Student1", 13);
////
////        assertThrows(NotExistingStudentInCourseException.class,
////                () -> courseService.addGradeToStudentInSpecificCourse(studen1, 4.0, course.getId()));
////    }
////
////
//////
//////    @Test
//////    public void shouldAddGradeToStudentWithValidData() {
//////
//////        Course course = courseService.create("Course1", 130);
//////        Student student = studentService.create("Ivan", 15);
//////        courseService.addStudentToCourseByCourseId(student, course.getId());
//////
//////        courseService.addGradeToStudentInSpecificCourse(student, 4.0, course.getId());
//////
//////        List<Double> gradesOfTheStudent = student.get(student);
//////        Double grade = gradesOfTheStudent.get(0);
//////
//////        assertEquals(4.0, grade);
//////        assertEquals(1, gradesOfTheStudent.size());
//////
//////    }
////
////    @Test
////    public void shouldShowAllStudentsGroupedAlphabeticallyThenByGrade() {
////        Course course1 = courseService.create("Course1", 130);
////        Course course2 = courseService.create("Course2", 150);
////
////        Student student1 = studentService.create("Student1", 14);
////        Student student2 = studentService.create("Student2", 14);
////        Student student3 = studentService.create("Student3", 14);
////
////        courseService.addStudentToCourseByCourseId(student1, course1.getId());
////        courseService.addStudentToCourseByCourseId(student2, course1.getId());
////        courseService.addStudentToCourseByCourseId(student3, course2.getId());
////
////        courseService.addGradeToStudentInSpecificCourse(student1, 5.0, course1.getId());
////        courseService.addGradeToStudentInSpecificCourse(student1, 6.0, course1.getId());
////        courseService.addGradeToStudentInSpecificCourse(student2, 5.0, course1.getId());
////        courseService.addGradeToStudentInSpecificCourse(student3, 4.0, course2.getId());
////
////        String expected = "\nCourse1\n" +
////                "Student2 5.0\n" +
////                "Student1 5.5\n" +
////                "Course2\n" +
////                "Student3 4.0" + "\n";
////        assertEquals(expected, courseService.showStudentsGroupedByCourseAlphabeticallyThenByGrade());
////
////    }
////
////    @Test
////    public void shouldReturnEmptyStringWhenNoStudents() {
////        assertEquals("\n", courseService.showStudentsGroupedByCourseAlphabeticallyThenByGrade());
////    }
////
////    @Test
////    public void shouldShowAllTeachersAndStudentsWithoutGrades() {
////        Course course1 = courseService.create("Course1", 130);
////        Course course2 = courseService.create("Course2", 150);
////
////        Teacher teacher1 = teacherService.create("Teacher1", "BSc");
////        Teacher teacher2 = teacherService.create("Teacher2", "BSc");
////
////        courseService.addTeacherToCourseByCourseId(teacher1, course1.getId());
////        courseService.addTeacherToCourseByCourseId(teacher2, course2.getId());
////
////        Student student1 = studentService.create("Student1", 14);
////        Student student2 = studentService.create("Student2", 14);
////        Student student3 = studentService.create("Student3", 14);
////
////        courseService.addStudentToCourseByCourseId(student1, course1.getId());
////        courseService.addStudentToCourseByCourseId(student2, course1.getId());
////        courseService.addStudentToCourseByCourseId(student3, course2.getId());
////
////        courseService.addGradeToStudentInSpecificCourse(student1, 5.0, course1.getId());
////        courseService.addGradeToStudentInSpecificCourse(student1, 6.0, course1.getId());
////        courseService.addGradeToStudentInSpecificCourse(student2, 5.0, course1.getId());
////        courseService.addGradeToStudentInSpecificCourse(student3, 4.0, course2.getId());
////
////
////        String expected = "\n" +
////                "Course1\n" +
////                "Teacher1\n" +
////                "Student1\n" +
////                "Student2\n" +
////                "Course2\n" +
////                "Teacher2\n" +
////                "Student3\n";
////        assertEquals(expected, courseService.showAllCoursesStudentsAndTeachers());
////    }
////
////    @Test
////    public void shouldThrowExceptionWhenNoTeacherInShowinAllTeachersStudents() {
////        Course course1 = courseService.create("Course1", 130);
////        Course course2 = courseService.create("Course2", 150);
////
////        Student student1 = studentService.create("Student1", 14);
////        Student student2 = studentService.create("Student2", 14);
////        Student student3 = studentService.create("Student3", 14);
////
////        courseService.addStudentToCourseByCourseId(student1, course1.getId());
////        courseService.addStudentToCourseByCourseId(student2, course1.getId());
////        courseService.addStudentToCourseByCourseId(student3, course2.getId());
////
////        courseService.addGradeToStudentInSpecificCourse(student1, 5.0, course1.getId());
////        courseService.addGradeToStudentInSpecificCourse(student1, 6.0, course1.getId());
////        courseService.addGradeToStudentInSpecificCourse(student2, 5.0, course1.getId());
////        courseService.addGradeToStudentInSpecificCourse(student3, 4.0, course2.getId());
////
////        assertThrows(IllegalArgumentException.class,
////                courseService::showAllCoursesStudentsAndTeachers);
////
////    }
////
////
////    @Test
////    public void shouldShowOnlyTeachersWhenThereIsNoStudents() {
////        Course course1 = courseService.create("Course1", 130);
////        Course course2 = courseService.create("Course2", 150);
////
////        Teacher teacher1 = teacherService.create("Teacher1", "BSc");
////        Teacher teacher2 = teacherService.create("Teacher2", "BSc");
////
////        courseService.addTeacherToCourseByCourseId(teacher1, course1.getId());
////        courseService.addTeacherToCourseByCourseId(teacher2, course2.getId());
////
////        String expected = "\nCourse1\n" +
////                "Teacher1\n" +
////                "Course2\n" +
////                "Teacher2\n";
////
////        assertEquals(expected, courseService.showAllCoursesStudentsAndTeachers());
////
////    }
////
////    @Test
////    public void shouldShowAverageGradeWithValidData() {
////        Course course = courseService.create("Course1", 130);
////
////        Student student1 = studentService.create("Student1", 13);
////        Student student2 = studentService.create("Student2", 13);
////        Student student3 = studentService.create("Student3", 13);
////
////        courseService.addStudentToCourseByCourseId(student1, course.getId());
////        courseService.addStudentToCourseByCourseId(student2, course.getId());
////        courseService.addStudentToCourseByCourseId(student3, course.getId());
////
////        courseService.addGradeToStudentInSpecificCourse(student1, 4.0, course.getId());
////        courseService.addGradeToStudentInSpecificCourse(student1, 5.0, course.getId());
////        courseService.addGradeToStudentInSpecificCourse(student2, 5.0, course.getId());
////        courseService.addGradeToStudentInSpecificCourse(student3, 4.0, course.getId());
////
////        assertEquals(4.5, courseService.averageGradeForSpecificCourse(course.getId()));
////    }
////
////    @Test
////    public void shouldReturn0ForAverageGradeWhenNoStudentsInCourse() {
////        Course course = courseService.create("Course1", 130);
////
////        assertEquals(0, courseService.averageGradeForSpecificCourse(course.getId()));
////    }
////
////    @Test
////    public void shouldCalculateAverageGradeForStudents() {
////        Course course1 = courseService.create("Course1", 130);
////        Course course2 = courseService.create("Course2", 120);
////
////        Student student = studentService.create("Student1", 13);
////
////        courseService.addStudentToCourseByCourseId(student, course1.getId());
////        courseService.addStudentToCourseByCourseId(student, course2.getId());
////
////        courseService.addGradeToStudentInSpecificCourse(student, 4.0, course1.getId());
////        courseService.addGradeToStudentInSpecificCourse(student, 5.0, course1.getId());
////        courseService.addGradeToStudentInSpecificCourse(student, 6.0, course1.getId());
////        courseService.addGradeToStudentInSpecificCourse(student, 6.0, course2.getId());
////
////        assertEquals(5.5, courseService.averageGradeForStudent(student));
////
////
////    }
////
////    @Test
////    public void shouldReturn0AverageGradeWhenNoCourses() {
////        Student student = studentService.create("Student1", 13);
////        assertEquals(0, courseService.averageGradeForStudent(student));
////    }
//
//
//}
