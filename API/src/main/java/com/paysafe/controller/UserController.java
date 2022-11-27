package com.paysafe.controller;

import com.paysafe.DTOs.UserDTO;
import com.paysafe.service.UserService;
import com.paysafe.services.StudentService;
import com.paysafe.services.TeacherService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/user")
@CrossOrigin(origins = "*")
public class UserController {
    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }



    @GetMapping("/{email}")
    public UserDTO getUserInfo(@PathVariable String email){
        return this.userService.getInfoByEmail(email);

    }
}
