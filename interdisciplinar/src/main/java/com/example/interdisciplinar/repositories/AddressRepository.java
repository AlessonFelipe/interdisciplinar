package com.example.interdisciplinar.repositories;

import com.example.interdisciplinar.models.AddressModel;
import com.example.interdisciplinar.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Integer> {
    Optional<AddressModel> findByUser(UserModel user);
}
