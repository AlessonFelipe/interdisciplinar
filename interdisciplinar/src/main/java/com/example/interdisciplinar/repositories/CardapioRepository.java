package com.example.interdisciplinar.repositories;

import com.example.interdisciplinar.models.CardapioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface CardapioRepository extends JpaRepository<CardapioModel, Integer> {
    Optional<CardapioModel> findByNome(String nome);
    @Query("SELECT c FROM CardapioModel c LEFT JOIN FETCH c.opcoes LEFT JOIN FETCH c.carnes WHERE c.id = :id")
    Optional<CardapioModel> findByIdWithOpcoesAndCarnes(@Param("id") Integer id);
}

