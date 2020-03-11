package com.caprinak.demo.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.caprinak.demo.model.Alien;

public interface JpaRepository extends PagingAndSortingRepository<Alien, Integer>{
	
}
