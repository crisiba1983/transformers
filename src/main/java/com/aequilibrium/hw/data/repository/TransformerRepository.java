package com.aequilibrium.hw.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aequilibrium.hw.data.entity.Transformer;

@Repository
public interface TransformerRepository extends JpaRepository<Transformer, Long> {

	List<Transformer> findAll();
	
}
