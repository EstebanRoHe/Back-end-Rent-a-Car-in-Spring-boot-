package com.rent_a_car.Controllers;

import com.rent_a_car.Model.TypeCar;
import com.rent_a_car.Repository.TypeCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/TypeCar")
public class TypeCarController {
    @Autowired
    private TypeCarRepository typeCarRepository;

    @GetMapping
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<List<TypeCar>> findAll(){
        List<TypeCar> list = new ArrayList<TypeCar>();
        typeCarRepository.findAll().forEach(e->list.add(e));
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id_typeCar}")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<TypeCar> findById(@PathVariable Long id_typeCar){
        if(!typeCarRepository.findById(id_typeCar).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(typeCarRepository.findById(id_typeCar).get());
    }

    @PostMapping
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity create (@RequestBody TypeCar typeCar){
        return ResponseEntity.ok(typeCarRepository.save(typeCar));
    }

    @PutMapping
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<TypeCar> update(@RequestBody TypeCar typeCar){
        if(!typeCarRepository.findById(typeCar.getId_typeCar()).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(typeCarRepository.save(typeCar));
    }

    @DeleteMapping("/{id_typeCar}")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<TypeCar> delete(@PathVariable Long id_typeCar){
        if(!typeCarRepository.findById(id_typeCar).isPresent()){
            ResponseEntity.badRequest().build();
        }

        typeCarRepository.delete(typeCarRepository.findById(id_typeCar).get());
        return ResponseEntity.ok().build();
    }



}
