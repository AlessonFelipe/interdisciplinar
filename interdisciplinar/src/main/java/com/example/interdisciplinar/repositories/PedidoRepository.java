package com.example.interdisciplinar.repositories;

import com.example.interdisciplinar.models.PedidoModel;
import com.example.interdisciplinar.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, UUID> {
    List<PedidoModel> findByUsuario(UserModel usuario);
}