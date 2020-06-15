package com.infosys.project.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.project.user.entity.Seller;


public interface SellerReposiotry extends JpaRepository<Seller, Integer>{

Seller findByEmail(String email);

}
