package com.example.interdisciplinar.controllers;
import com.example.interdisciplinar.dtos.PedidoRecordDTO;
import com.example.interdisciplinar.models.CardapioModel;
import com.example.interdisciplinar.models.FormaPagamento;
import com.example.interdisciplinar.models.PedidoModel;
import com.example.interdisciplinar.models.UserModel;
import com.example.interdisciplinar.repositories.CardapioRepository;
import com.example.interdisciplinar.repositories.PedidoRepository;
import com.example.interdisciplinar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;//package com.example.interdisciplinar.controllers;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import com.example.interdisciplinar.repositories.AddressRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class PedidoController {
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    CardapioRepository cardapioRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

@PostMapping("/pedido")
public ResponseEntity<?> addPedido(@RequestBody PedidoRecordDTO pedidoRecordDTO) {
    var pedidoModel = new PedidoModel();


    Optional<UserModel> userOptional = userRepository.findById(Integer.parseInt(pedidoRecordDTO.usuario()));
    if (userOptional.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    UserModel usuario = userOptional.get();


    Optional<CardapioModel> produtoOptional = cardapioRepository.findByNome(pedidoRecordDTO.produto());
    if (produtoOptional.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    CardapioModel produto = produtoOptional.get();


    FormaPagamento formaPagamento;
    switch (pedidoRecordDTO.formaPagamento().toUpperCase()) {
        case "CARTAO":
            formaPagamento = FormaPagamento.CARTAO;
            break;
        case "DINHEIRO":
            formaPagamento = FormaPagamento.DINHEIRO;
            break;
        default:
            throw new IllegalArgumentException("Forma de pagamento não reconhecida: " + pedidoRecordDTO.formaPagamento());

    }
    if (pedidoRecordDTO.opcoes().size() > 9 || pedidoRecordDTO.carnes().size() > 2) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Apenas duas opções de carnes disponíveis");
    }
    BigDecimal precoTotal = produto.getPreco();

    if (pedidoRecordDTO.carnes().contains("Mão de Vaca")) {
        precoTotal = BigDecimal.valueOf(20);
    }

    pedidoModel.getFrete();
    pedidoModel.setUsuario(usuario);
    pedidoModel.setProduto(produto);
    pedidoModel.setFormaPagamento(formaPagamento);
    pedidoModel.setOpcoes(pedidoRecordDTO.opcoes());
    pedidoModel.setCarnes(pedidoRecordDTO.carnes());
    pedidoModel.setPrecoTotal(precoTotal);


    PedidoModel novoPedido = pedidoRepository.save(pedidoModel);

    return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
}
    @GetMapping("/pedido")
    public ResponseEntity<List<PedidoModel>> getAllPedidos() {
        List<PedidoModel> pedidos = pedidoRepository.findAll();
        if (pedidos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }

    @DeleteMapping("/pedido/{id}")
    public ResponseEntity<Object> deletePedido(@PathVariable Integer id) {
        Optional<PedidoModel> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pedidoRepository.delete(pedidoOptional.get());
        return ResponseEntity.ok("Pedido Deletado");
    }

    @PutMapping("/pedido/{id}")
    public ResponseEntity<PedidoModel> updatePedido(@PathVariable Integer id, @RequestBody PedidoRecordDTO pedidoRecordDTO) {
        Optional<PedidoModel> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PedidoModel pedido = pedidoOptional.get();

        Optional<UserModel> userOptional = userRepository.findById(Integer.parseInt(pedidoRecordDTO.usuario()));
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        UserModel usuario = userOptional.get();

        Optional<CardapioModel> produtoOptional = cardapioRepository.findByNome(pedidoRecordDTO.produto());
        if (produtoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        CardapioModel produto = produtoOptional.get();


        pedido.setUsuario(usuario);
        pedido.setProduto(produto);


        PedidoModel pedidoAtualizado = pedidoRepository.save(pedido);


        return ResponseEntity.ok(pedidoAtualizado);
    }
}

