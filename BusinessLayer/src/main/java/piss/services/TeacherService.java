package piss.services;

import piss.DTOs.TeacherDTO;
import piss.TeacherRepository;
import piss.UserRepository;
import piss.entities.Degree;
import piss.entities.Teacher;
import piss.entities.UserRole;
import piss.entities.Users;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;
    private UserRepository userRepository;
    public TeacherService(TeacherRepository teacherRepository,
                          UserRepository userRepository) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
    }

    public Teacher create(String name, String degree) {

        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }

        Degree degreeOfTeacher = Degree.valueOf(degree);

        Teacher teacher = new Teacher(name, degreeOfTeacher);

        return this.teacherRepository.save(teacher);
    }

    public TeacherDTO create(TeacherDTO teacherDTO){
        if (teacherDTO.getName() == null) {
            throw new IllegalArgumentException("Name must not be null");
        }

        Degree degreeOfTeacher = Degree.valueOf(teacherDTO.getDegree());

        Teacher teacher = new Teacher(teacherDTO.getName(), degreeOfTeacher);

        if(userRepository.findByEmail(teacher.getEmail()).isEmpty()){
            throw new IllegalArgumentException("there is no email");
        }

        Users user = userRepository.findByEmail(teacher.getEmail()).get();

        teacher.setEmail(user.getEmail());
        user.setUserRole(UserRole.Teacher);
        this.userRepository.save(user);

        return new TeacherDTO(this.teacherRepository.save(teacher));
    }

    public void delete(Integer id ){

        Optional<Teacher> teacher = Optional.of(this.teacherRepository.getReferenceById(id));

        if(teacher.isEmpty()){
            throw new RuntimeException("There is no such user");
        }

        this.teacherRepository.delete(teacher.get());
    }

    public List<TeacherDTO> getAllTeachers(){
        return this.teacherRepository.findAll().stream().map(TeacherDTO::new).collect(Collectors.toList());
    }
}
