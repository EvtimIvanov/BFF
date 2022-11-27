package piss.service;

import piss.DTOs.RegisterUserDTO;
import piss.DTOs.UserDTO;
import piss.StudentRepository;
import piss.TeacherRepository;
import piss.UserRepository;
import piss.entities.Student;
import piss.entities.Teacher;
import piss.entities.UserRole;
import piss.entities.Users;
import piss.exceptions.EmailAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
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
