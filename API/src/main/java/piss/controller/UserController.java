package piss.controller;

import piss.DTOs.UserDTO;
import piss.service.UserService;
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
