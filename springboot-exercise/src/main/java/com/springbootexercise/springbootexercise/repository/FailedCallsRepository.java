package com.springbootexercise.springbootexercise.repository;

import com.springbootexercise.springbootexercise.entity.FailedCalls;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FailedCallsRepository extends JpaRepository<FailedCalls, UUID> {
}
