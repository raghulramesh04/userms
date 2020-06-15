package com.infosys.project.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.project.user.entity.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer>{

	Buyer findByEmail(String email);

}
