package com.springbootexercise.springbootexercise.repository;

import com.springbootexercise.springbootexercise.entity.AffilateClientMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AffilateClientRepository extends JpaRepository<AffilateClientMap, UUID> {

}
