//package com.paysafe.services;
//
//import com.paysafe.entities.Degree;
//import com.paysafe.entities.Teacher;
//import org.junit.Assert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TeacherServiceTest {
//
//    TeacherService teacherService = new TeacherService();
//
//    @BeforeEach
//    public void setup() {
////        teacherService.clearDataStore();
//    }
//
//    @Test
//    public void shouldReturnExceptionWithNullName() {
//        assertThrows(IllegalArgumentException.class,
//                () -> teacherService.create(null, "BSc"));
//    }
//
//    @Test
//    public void shouldCreateTeacherWithValidData() {
//        String name = "Teacher name";
//        String degree = "BSc";
//
//        Teacher teacher = teacherService.create(name, degree);
//
//       // assertEquals(1, teacher.getId());
//        assertEquals(name, teacher.getName());
//        assertEquals(Degree.valueOf(degree), teacher.getDegree());
//    }
//
//    @Test
//    public void shouldThrowExceptionWithInvalidDegree() {
//        assertThrows(IllegalArgumentException.class,
//                () -> teacherService.create("Ivan", "invalidDegree"));
//    }
//}
