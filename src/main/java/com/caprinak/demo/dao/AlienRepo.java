package com.caprinak.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.caprinak.demo.model.Alien;



public interface AlienRepo extends CrudRepository<Alien, Integer>{
	List<Alien> findByTech(String tech);
	
	List<Alien> findByAidGreaterThan(int aid);
	
	@Query("from Alien where tech=?1 order by lower(aname)")
	List<Alien> findByTechSorted(String tech);

	
} 
