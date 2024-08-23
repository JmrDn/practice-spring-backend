package com.codewithjmrdn.practice_spring_backend.exception;

public class AnimalNotFoundException extends RuntimeException{

    public AnimalNotFoundException (Integer id){
        super("Animal not found with id: " + id);
    }

}
