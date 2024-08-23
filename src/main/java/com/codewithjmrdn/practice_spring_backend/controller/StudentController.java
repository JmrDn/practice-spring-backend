package com.codewithjmrdn.practice_spring_backend.controller;

import com.codewithjmrdn.practice_spring_backend.exception.StudentNotFoundException;
import com.codewithjmrdn.practice_spring_backend.exception.UserNotFoundException;
import com.codewithjmrdn.practice_spring_backend.model.Student;
import com.codewithjmrdn.practice_spring_backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/student")
    Student newStudent (@RequestBody Student newStudent){
        return studentRepository.save(newStudent);
    }

    @GetMapping("/students")
    List<Student> getAllStudents (){ return studentRepository.findAll();}

    @GetMapping("/students/{stud_id}")
    Student getStudent (@PathVariable Integer stud_id){
        return studentRepository.findById(stud_id)
                .orElseThrow(()-> new StudentNotFoundException(stud_id));
    }

    @PutMapping("/students/{stud_id}")
    Student updateStudent (@RequestBody Student updatedStudent, @PathVariable Integer stud_id){
        return studentRepository.findById(stud_id)
                .map(student -> {
                    student.setStud_gender(updatedStudent.getStud_gender());
                    student.setStud_email(updatedStudent.getStud_email());
                    student.setStud_name(updatedStudent.getStud_name());
                    student.setStud_section(updatedStudent.getStud_section());

                    return studentRepository.save(student);
                })
                .orElseThrow(()-> new StudentNotFoundException(stud_id));
    }

    @DeleteMapping("students/{stud_id}")
    String deleteUser (@PathVariable Integer stud_id){
        if (!studentRepository.existsById(stud_id)){
            throw new UserNotFoundException(stud_id);
        }

        studentRepository.deleteById(stud_id);

        return "Student successfully deleted with id: " + stud_id;
    }
}
