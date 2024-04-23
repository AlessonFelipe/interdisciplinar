package com.example.interdisciplinar.controllers;

import com.example.interdisciplinar.dtos.AddressRecordDTO;
import com.example.interdisciplinar.dtos.CardapioRecordDTO;
import com.example.interdisciplinar.models.AddressModel; // Alteração do nome do modelo
import com.example.interdisciplinar.repositories.AddressRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class AddressController {
    @Autowired
    AddressRepository addressRepository;

    @PostMapping("/endereco")
    public ResponseEntity<AddressModel> saveEndereco(@RequestBody @Valid AddressRecordDTO addressRecordDTO){
        var addressModel = new AddressModel();
        BeanUtils.copyProperties(addressRecordDTO, addressModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressRepository.save(addressModel));
    }

    @GetMapping("/endereco")
    public ResponseEntity<List<AddressModel>> getAllEndereco(){
        return ResponseEntity.status(HttpStatus.OK).body(addressRepository.findAll());
    }

    @GetMapping("/endereco/{id}")
    public ResponseEntity<Object> getOneEndereco(@PathVariable(value = "id")Integer id){
        Optional<AddressModel> address = addressRepository.findById(id);
        if(address.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(address.get());
    }

    @PutMapping("/endereco/{id}")
    public ResponseEntity<Object> updateEndereco(@PathVariable(value = "id") Integer id, @RequestBody @Valid AddressRecordDTO addressRecordDTO){
        Optional<AddressModel> address = addressRepository.findById(id);
        if(address.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado.");
        }
        var addressModel= address.get();
        BeanUtils.copyProperties(addressRecordDTO, addressModel);
        return ResponseEntity.status(HttpStatus.OK).body(addressRepository.save(addressModel));
    }

    @DeleteMapping("/endereco/{id}")
    public ResponseEntity<Object> deleteEndereco(@PathVariable(value = "id")Integer id){
        Optional<AddressModel> address = addressRepository.findById(id);
        if(address.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado.");
        }
        addressRepository.delete(address.get());
        return ResponseEntity.status(HttpStatus.OK).body("Endereço deletado com sucesso.");
    }
}
