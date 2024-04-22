package com.example.interdisciplinar.controllers;

import com.example.interdisciplinar.dtos.CardapioRecordDTO;
import com.example.interdisciplinar.models.CardapioModel;
import com.example.interdisciplinar.repositories.CardapioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
public class Cardapiocontroller {
    @Autowired
    CardapioRepository cardapioRepository;
//@PostMapping("/cardapio")
//public ResponseEntity<CardapioModel> saveCardapio(@RequestBody @Valid CardapioRecordDTO cardapioRecordDTO){
//    // Cria um novo objeto CardapioModel
//    CardapioModel cardapioModel = new CardapioModel();
//
//    // Copia as propriedades do DTO para o modelo
//    BeanUtils.copyProperties(cardapioRecordDTO, cardapioModel);
//
//    // Salva o CardapioModel no banco de dados
//    CardapioModel savedCardapio = cardapioRepository.save(cardapioModel);
//
//    // Retorna uma resposta com o CardapioModel salvo e o status CREATED (201)
//    return ResponseEntity.status(HttpStatus.CREATED).body(savedCardapio);
//}
@PostMapping("/cardapio")
public ResponseEntity<CardapioModel> criarCardapio(@RequestBody @Valid CardapioRecordDTO cardapioRecordDTO) {
    CardapioModel cardapio = new CardapioModel();
    cardapio.setNome(cardapioRecordDTO.nome());
    cardapio.setDescricao(cardapioRecordDTO.descricao());
    cardapio.setPreco(cardapioRecordDTO.preco());
    cardapio.setImagemUrl(cardapioRecordDTO.imagemUrl());

    List<String> opcoes = cardapioRecordDTO.opcoes();
    List<String> carnes = cardapioRecordDTO.carnes();

    cardapio.setOpcoes(opcoes);
    cardapio.setCarnes(carnes);

    CardapioModel savedCardapio = cardapioRepository.save(cardapio);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCardapio);
}


    @GetMapping("/cardapio")
    public  ResponseEntity<List<CardapioModel>> getAllCardapios(){
        return ResponseEntity.status(HttpStatus.OK).body(cardapioRepository.findAll());
    }
    @GetMapping("/cardapio/{id}")
    public ResponseEntity<Object> getOneCardapio(@PathVariable(value = "id")Integer id){
        Optional<CardapioModel> cardapio0 = cardapioRepository.findById(id);
        if(cardapio0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cardapio0.get());
    }
    @PutMapping("/cardapio/{id}")
    public ResponseEntity<Object> updateCardapio(@PathVariable(value = "id") Integer id, @RequestBody @Valid CardapioRecordDTO cardapioRecordDTO){
        Optional<CardapioModel> cardapio0 = cardapioRepository.findById(id);
        if(cardapio0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        var cardapioModel= cardapio0.get();
        BeanUtils.copyProperties(cardapioRecordDTO,cardapioModel);
        return ResponseEntity.status(HttpStatus.OK).body(cardapioRepository.save(cardapioModel));
    }
    @DeleteMapping("/cardapio/{id}")
    public ResponseEntity<Object> deleteCardapio(@PathVariable(value = "id")Integer id){
        Optional<CardapioModel> cardapio0 = cardapioRepository.findById(id);
        if(cardapio0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        cardapioRepository.delete(cardapio0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }}