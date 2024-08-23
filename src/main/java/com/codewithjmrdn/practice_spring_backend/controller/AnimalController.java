package com.codewithjmrdn.practice_spring_backend.controller;

import com.codewithjmrdn.practice_spring_backend.exception.AnimalNotFoundException;
import com.codewithjmrdn.practice_spring_backend.model.Animal;
import com.codewithjmrdn.practice_spring_backend.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @PostMapping("/animal")
    Animal addAnimal(@RequestBody Animal newAnimal){ return animalRepository.save(newAnimal);}

    @GetMapping("/animals")
    List<Animal> getAllAnimals(){return animalRepository.findAll();}

    @GetMapping("/animals/{id}")
    Animal getAnimal (@PathVariable Integer id){return animalRepository.findById(id).orElseThrow(()-> new AnimalNotFoundException(id));}

    @PutMapping("/animals/{id}")
    Animal updateAnimal (@RequestBody Animal newAnimal , @PathVariable Integer id){
        return animalRepository.findById(id)
                .map(animal ->{
                    animal.setKindOfAnimal(newAnimal.getKindOfAnimal());
                    animal.setAge(newAnimal.getAge());
                    animal.setName(newAnimal.getName());
                    animal.setBreed(newAnimal.getBreed());
                    return animalRepository.save(animal);
                })
                .orElseThrow(()-> new AnimalNotFoundException(id));
    }

    @DeleteMapping("/animals/{id}")
    String deleteAnimal (@PathVariable Integer id){
        if (!animalRepository.existsById(id))
            throw new AnimalNotFoundException(id);

        animalRepository.deleteById(id);
        return "Animal successfully deleted with id: " + id;
    }
}
