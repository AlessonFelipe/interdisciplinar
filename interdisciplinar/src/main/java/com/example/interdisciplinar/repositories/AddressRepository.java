package com.example.interdisciplinar.repositories;

import com.example.interdisciplinar.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, UUID> {
    // Você pode adicionar métodos de consulta personalizados aqui, se necessário
}
