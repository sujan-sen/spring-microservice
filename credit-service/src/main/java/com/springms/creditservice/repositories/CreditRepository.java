package com.springms.creditservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springms.creditservice.entities.Credit;

public interface CreditRepository extends JpaRepository<Credit, Integer> {

}
