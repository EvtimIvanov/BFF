package com.paysafe.services;

import com.paysafe.DTOs.StudentDTO;
import com.paysafe.UserRepository;
import com.paysafe.entities.Course;
import com.paysafe.entities.Student;
import com.paysafe.StudentRepository;
import com.paysafe.entities.UserRole;
import com.paysafe.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private UserRepository userRepository;
    private CourseService courseService;
    @Autowired
    public StudentService(StudentRepository studentRepository,
                          UserRepository userRepository,
                          CourseService courseService) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.courseService = courseService;
    }

    public Student create(String name, int age) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("Age must be between 0 and 100");
        }

        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }

        Student student = new Student(name, age);

        return this.studentRepository.save(student);

    }

    public StudentDTO create(StudentDTO studentDTO) {
        if (studentDTO.getAge() < 0 || studentDTO.getAge() > 100) {
            throw new IllegalArgumentException("Age must be between 0 and 100");
        }

        if (studentDTO.getName() == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        if(userRepository.findByEmail(studentDTO.getEmail()).isEmpty()){
            throw new IllegalArgumentException("there is no email");
        }

        Student student = new Student(studentDTO.getName(), studentDTO.getAge());
        Users user = userRepository.findByEmail(studentDTO.getEmail()).get();

        student.setEmail(user.getEmail());
        user.setUserRole(UserRole.Student);
        userRepository.save(user);
        return new StudentDTO(this.studentRepository.save(student));
    }

    public Student getById(int id) {
        if (id < 0) {
            throw new IllegalArgumentException();
        }

        return this.studentRepository.getReferenceById(id);
    }

    public List<StudentDTO> getAllStudents() {
        return this.studentRepository
                .findAll()
                .stream()
                .map(StudentDTO::new)
                .collect(Collectors.toList());
    }

    public Double getAverageGrade(String email){
        if(this.studentRepository.findByEmail(email).isEmpty()){
            throw new IllegalArgumentException("There is no user with such email");
        }

        Student student = this.studentRepository.findByEmail(email).get();

        return courseService.averageGradeForStudent(student.getId());
    }

}
