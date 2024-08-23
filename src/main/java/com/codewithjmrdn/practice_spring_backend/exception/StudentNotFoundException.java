package com.codewithjmrdn.practice_spring_backend.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException (Integer stud_id){
        super("Student not found by id: " + stud_id);
    }
}
