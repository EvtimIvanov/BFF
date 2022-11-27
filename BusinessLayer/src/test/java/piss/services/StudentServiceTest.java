//package com.paysafe.services;
//
//
//import com.paysafe.entities.Student;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class StudentServiceTest {
//
//    private StudentService studentService=new StudentService();
//
//
//    @BeforeEach
//    public void setup(){
//       //studentService.clearDataStore();
//
//    }
//
//
//
//    @Test
//    public void shouldCreateStudentWithValidData() {
//        String name = "Ivan";
//        int age = 10;
//
//        Student current = studentService.create(name, age);
//        System.out.println(current.getId());
//
//        //assertEquals(1, current.getId());
//        assertEquals(name, current.getName());
//        assertEquals(age, current.getAge());
//
//    }
//
//    @Test
//    public void shouldThrowExceptionWithNegativeAge() {
//        assertThrows(IllegalArgumentException.class,
//                () -> studentService.create("Ivan", -5));
//    }
//
//    @Test
//    public void shouldThrowExceptionWithAgeAbove100() {
//
//        assertThrows(IllegalArgumentException.class,
//                () -> studentService.create("Ivan", -5));
//
//    }
//
//    @Test
//    public void shouldThrowExceptionWithNullName() {
//
//        assertThrows(IllegalArgumentException.class,
//                () -> studentService.create(null, 5));
//    }
//
//
//
//}
