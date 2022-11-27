package piss.controller;

import piss.DTOs.TeacherDTO;
import piss.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;

    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacher) {

        return new ResponseEntity<>(teacherService.create(teacher), HttpStatus.CREATED);
    }


    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_Admin')")
    List<TeacherDTO> all() {
        return this.teacherService.getAllTeachers();
    }

    @DeleteMapping("")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public void deleteTeacher(@RequestParam int idOfTeacher){

        this.teacherService.delete(idOfTeacher);

    }




}
