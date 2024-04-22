package com.example.interdisciplinar.repositories;

import com.example.interdisciplinar.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByNumero(int numero);

    boolean existsByNumero(int numero);
}
