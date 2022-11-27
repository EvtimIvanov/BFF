package com.paysafe.service;

import com.paysafe.DTOs.RegisterUserDTO;
import com.paysafe.DTOs.UserDTO;
import com.paysafe.StudentRepository;
import com.paysafe.TeacherRepository;
import com.paysafe.UserRepository;
import com.paysafe.entities.Student;
import com.paysafe.entities.Teacher;
import com.paysafe.entities.Users;
import com.paysafe.entities.UserRole;
import com.paysafe.exceptions.EmailAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }


    public void register(RegisterUserDTO registerUserDTO) {
        boolean emailInUse = this.userRepository.existsByEmail(registerUserDTO.getEmail());

        if (emailInUse) {
            throw new EmailAlreadyExistsException();
        }

        Users user = toUser(registerUserDTO);

        userRepository.save(user);

    }

    private Users toUser(RegisterUserDTO registerUserDTO) {

        Users user = new Users();

        user.setEmail(registerUserDTO.getEmail());
        user.setUserRole(UserRole.Admin);

        String encodedPassword = passwordEncoder.encode(registerUserDTO.getPassword());

        user.setPassword(encodedPassword);

        return user;

    }

    public String getRoleByEmail(String email) {

        return this.userRepository.findByEmail(email).get().getUserRole().toString();
    }

    public UserDTO getInfoByEmail(String email) {
        if (this.userRepository.findByEmail(email).isEmpty()) {
            throw new RuntimeException("There is no user with such email");
        }
        Users user = this.userRepository.findByEmail(email).get();
        UserDTO userDTO = new UserDTO(user);

        if (this.studentRepository.findByEmail(email).isPresent()) {
            Student student = this.studentRepository.findByEmail(email).get();
            String name = student.getName();
            userDTO.setUserName(name);
        }else if(this.teacherRepository.findByEmail(email).isPresent()){
            Teacher teacher = this.teacherRepository.findByEmail(email).get();
            String name = teacher.getName();
            userDTO.setUserName(name);
        }


        return userDTO;

    }
}
