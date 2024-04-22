package com.example.interdisciplinar.controllers;


import com.example.interdisciplinar.dtos.LoginRecordDTO;
import com.example.interdisciplinar.models.UserModel;
import com.example.interdisciplinar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRecordDTO loginRecordDTO) {
        Optional<UserModel> userOptional = userRepository.findByNumero(loginRecordDTO.numero());
        if (userOptional.isPresent()) {
            UserModel usuario = userOptional.get();
//            if (usuario.getSenha().equals(loginRecordDTO.senha())) {
                return ResponseEntity.ok("Login bem-sucedido!");
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta.");
//            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado.");
        }
    }}

