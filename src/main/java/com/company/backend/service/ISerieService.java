package com.company.backend.service;

import java.util.List;

import com.company.backend.model.Serie;

public interface ISerieService {

	public List<Serie> getAllSeries();	
	
	public Serie getSerieById(Long id);
	
}
