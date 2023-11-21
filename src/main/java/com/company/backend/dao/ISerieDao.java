package com.company.backend.dao;


import org.springframework.data.repository.CrudRepository;

import com.company.backend.model.Serie;
public interface ISerieDao extends CrudRepository<Serie, Long>{

	
	public Serie findById(long id);
	
	
}
