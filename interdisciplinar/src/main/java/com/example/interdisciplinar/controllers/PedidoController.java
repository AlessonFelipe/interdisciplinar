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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
public ResponseEntity<PedidoModel> addPedido(@RequestBody PedidoRecordDTO pedidoRecordDTO) {
    var pedidoModel = new PedidoModel();

    // Encontrar o UserModel correspondente ao ID fornecido
    Optional<UserModel> userOptional = userRepository.findById(UUID.fromString(pedidoRecordDTO.usuario()));
    if (userOptional.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    UserModel usuario = userOptional.get();

    // Encontrar o CardapioModel correspondente ao nome do produto fornecido
    Optional<CardapioModel> produtoOptional = cardapioRepository.findByNome(pedidoRecordDTO.produto());
    if (produtoOptional.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    CardapioModel produto = produtoOptional.get();

    // Calcular o preço total do pedido
    BigDecimal precoTotal = produto.getPreco().multiply(BigDecimal.valueOf(pedidoRecordDTO.quantidade()));
    FormaPagamento formaPagamento;
    switch (pedidoRecordDTO.formaPagamento().toUpperCase()) {
        case "DEBITO":
            formaPagamento = FormaPagamento.DEBITO;
            break;
        case "CREDITO":
            formaPagamento = FormaPagamento.CREDITO;
            break;
        case "PIX":
            formaPagamento = FormaPagamento.PIX;
            break;
        default:
            throw new IllegalArgumentException("Forma de pagamento não reconhecida: " + pedidoRecordDTO.formaPagamento());

    }
    // Preencher o pedidoModel com os dados do pedidoRecordDTO e o preço total
    pedidoModel.setUsuario(usuario);
    pedidoModel.setProduto(produto);
    pedidoModel.setQuantidade(pedidoRecordDTO.quantidade());
    pedidoModel.setPrecoTotal(precoTotal);
    pedidoModel.setFormaPagamento(formaPagamento);


    // Salvar o pedido no banco de dados
    PedidoModel novoPedido = pedidoRepository.save(pedidoModel);

    // Retornar a resposta com o novo pedido criado
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
    public ResponseEntity<Object> deletePedido(@PathVariable UUID id) {
        Optional<PedidoModel> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pedidoRepository.delete(pedidoOptional.get());
        return ResponseEntity.ok("Pedido Deletado");
    }

    @PutMapping("/pedido/{id}")
    public ResponseEntity<PedidoModel> updatePedido(@PathVariable UUID id, @RequestBody PedidoRecordDTO pedidoRecordDTO) {
        Optional<PedidoModel> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PedidoModel pedido = pedidoOptional.get();

        // Encontre o usuário com base no ID do usuário no DTO
        Optional<UserModel> userOptional = userRepository.findById(UUID.fromString(pedidoRecordDTO.usuario()));
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        UserModel usuario = userOptional.get();

        // Encontre o produto com base no nome do produto no DTO
        Optional<CardapioModel> produtoOptional = cardapioRepository.findByNome(pedidoRecordDTO.produto());
        if (produtoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        CardapioModel produto = produtoOptional.get();

        // Atualize os dados do pedido
        pedido.setUsuario(usuario);
        pedido.setProduto(produto);
        pedido.setQuantidade(pedidoRecordDTO.quantidade());

        // Salve o pedido atualizado no banco de dados
        PedidoModel pedidoAtualizado = pedidoRepository.save(pedido);

        // Retorne a resposta com o pedido atualizado
        return ResponseEntity.ok(pedidoAtualizado);
    }
}

