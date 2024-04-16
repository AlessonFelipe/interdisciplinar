package com.example.interdisciplinar.repositories;

import com.example.interdisciplinar.models.CardapioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface CardapioRepository extends JpaRepository<CardapioModel, UUID> {
}
