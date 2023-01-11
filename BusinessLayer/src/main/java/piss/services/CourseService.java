package piss.services;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import piss.CourseRepository;
import piss.DTOs.CourseTeacherAverageGradeDTO;
import piss.DTOs.CourseTeacherDTO;
import piss.DTOs.CourseWithAverageGradeDTO;
import piss.DTOs.CoursesDTO;
import piss.StudentRepository;
import piss.TeacherRepository;
import piss.entities.Course;
import piss.entities.Grade;
import piss.entities.Student;
import piss.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService {


    private CourseRepository courseRepository;
    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    public Course create(String courseName, int totalHours) {

        if (totalHours < 0 || totalHours > 300) {
            throw new IllegalArgumentException("Total hours must be between 0 and 300");
        }

        if (courseName == null) {
            throw new IllegalArgumentException("Course name must not be null");
        }

        Course course = new Course(courseName, totalHours);


        return this.courseRepository.save(course);
    }

    public CoursesDTO create(CoursesDTO coursesDTO) {

        int totalHours = coursesDTO.getTotalHours();

        if (totalHours < 0 || totalHours > 300) {
            throw new IllegalArgumentException("Total hours must be between 0 and 300");
        }

        if (coursesDTO.getCourseName() == null) {
            throw new IllegalArgumentException("Course name must not be null");
        }

        Course course = new Course(coursesDTO.getCourseName(), totalHours);


        return new CoursesDTO(this.courseRepository.save(course));
    }

    @Transactional
    public CourseTeacherDTO addTeacherToCourseDTO(int teacherId, int courseId) {

        CourseTeacherDTO course = new CourseTeacherDTO(
                this.addTeacherToCourseByCourseId(teacherId, courseId));
        return course;
    }

    @Transactional
    public Course addTeacherToCourseByCourseId(int teacherId, int courseId) {
        Course course = this.courseRepository.getReferenceById(courseId);
        Teacher teacher = this.teacherRepository.getReferenceById(teacherId);
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher must not be null");
        }

        course.setTeacherOfTheCourse(teacher);

        return this.courseRepository.save(course);
    }

    @Transactional
    public Course addStudentToCourseByCourseId(Integer studentId, int courseId) {

        Course course = this.courseRepository.getReferenceById(courseId);
        Student student = this.studentRepository.getReferenceById(studentId);


        student.addCourse(course);
        course.addStudentToTheCourse(student);
        this.studentRepository.save(student);

        return this.courseRepository.save(course);
    }

    @Transactional
    public Course addGradeToStudentInSpecificCourse(int studentId, Double newGrade, int courseId) {

        Course course = this.courseRepository.findById(courseId).get();
        Student student = this.studentRepository.findById(studentId).get();

        if (Double.compare(newGrade, 2.0) < 0 || Double.compare(newGrade, 6.0) > 0) {
            throw new IllegalArgumentException("Grade must be between 2.0 and 6.0");

        }
        Grade grade = new Grade(student, course, newGrade);
        student.addGrade(grade);
        course.addGradeToStudent(student, grade);


        return this.courseRepository.save(course);


    }

    @Transactional
    public String showStudentsGroupedByCourseAlphabeticallyThenByGrade() {


        List<Course> allCourses = new ArrayList<>(this.courseRepository.findAll());

        Collections.sort(allCourses);

        StringBuilder resultAfterSort = new StringBuilder();
        resultAfterSort.append("\n");

        for (Course course : allCourses) {

            resultAfterSort.append(course.getName() + "\n");
            resultAfterSort.append(course.getAllStudentsSortedByAverageGrade());
        }
        return resultAfterSort.toString();
    }

    @Transactional
    public String showAllCoursesStudentsAndTeachers() {
        StringBuilder result = new StringBuilder("\n");

        List<Course> allCourses = new ArrayList<>(this.courseRepository.findAll());
        Collections.sort(allCourses);

        for (Course course : allCourses) {

            result.append(course.getName()).append("\n");
            String nameOfTeacher = course.getTeacherOfTheCourse().getName();
            result.append(nameOfTeacher + "\n");

            Set<Student> sortedSetByName = new TreeSet<>(Comparator.comparing(Student::getName));

            sortedSetByName.addAll(course.getAllStudents());
            for (Student student : sortedSetByName) {

                result.append(student.getName() + "\n");
            }


        }
        return result.toString();
    }

    @Transactional
    public double averageGradeForSpecificCourse(int courseId) {

        Course course = this.courseRepository.getReferenceById(courseId);

        int studentsInCourse = course.getAllStudents().size();

        if (studentsInCourse != 0) {
            return course.getAverageGradeForAll();
        }

        return 0;
    }

    @Transactional
    public double averageGradeForStudent(int studentId) {
        Student student = this.studentRepository.getReferenceById(studentId);
        List<Course> courses = student.getStudentCourses();

        double average = 0;
        int counterToDivide = 0;

        for (Course course : courses) {
            if (course.getAverageGradeByStudent(student) != 0) {
                average += course.getAverageGradeByStudent(student);
                counterToDivide++;
            }

        }

        if (counterToDivide != 0) {
            average /= counterToDivide;
        }

        return average;

    }


    public List<CourseTeacherAverageGradeDTO> getAllCourses() {
        return this.courseRepository.findAll()
                .stream()
                .map(CourseTeacherAverageGradeDTO::new)
                .collect(Collectors.toList());
    }

    public List<CourseTeacherDTO> getAllCoursesWithTeachers() {
        return this.courseRepository
                .findAll()
                .stream()
                .map(CourseTeacherDTO::new)
                .collect(Collectors.toList());
    }

    public List<CourseWithAverageGradeDTO> getTeacherCoursesWithAverageGrade(String teacherEmail) {
        Teacher teacher = teacherRepository.findByEmail(teacherEmail).get();
        List<CourseWithAverageGradeDTO> list =
                this.courseRepository.getAllTeacherCourse(teacher.getId());

        for (CourseWithAverageGradeDTO course: list) {

            Double averageGrade = course.getAverageGrade();
            course.setAverageGrade(averageGrade);
        }

        return list;
    }

    public Course getCourseByName(String name){
        if(this.courseRepository.getCourseByName(name).isEmpty()){
            throw new RuntimeException("There is no such course");
        }
        return this.courseRepository.getCourseByName(name).get();
    }

    public CourseTeacherDTO removeTeacher(int courseId){
        Course course = this.courseRepository.getReferenceById(courseId);
        course.removeTeacher();

        return new CourseTeacherDTO(course);
    }

    public static void sendEmail(String to, String subject, String body) {
        final String username = "your-email-address@example.com";
        final String password = "your-email-password";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.example.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
