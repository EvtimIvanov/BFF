package piss.controller;

import piss.DTOs.StudentDTO;
import piss.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
@CrossOrigin(origins = "*")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        return new ResponseEntity<>(studentService.create(studentDTO), HttpStatus.CREATED);
    }


    @GetMapping("")
    @PreAuthorize("hasAnyRole('ROLE_Student','ROLE_Admin')")
    List<StudentDTO> all() {
        return this.studentService.getAllStudents();
    }

    @GetMapping("/averageGrade/{email}")
    @PreAuthorize("hasAnyRole('ROLE_Student')")
    Double getAverage(@PathVariable String email){
        return this.studentService.getAverageGrade(email);
    }


}
