package com.company.backend.service;


import org.springframework.http.ResponseEntity;

import com.company.backend.model.Serie;
import com.company.backend.response.SerieResponseRest;

public interface ISerieService {

	public ResponseEntity<SerieResponseRest> getAllSeries();	
	
	public ResponseEntity<SerieResponseRest> getSerieById(long id);
	
	public ResponseEntity<SerieResponseRest> createSerie(Serie serie);
	
	public ResponseEntity<SerieResponseRest> updateSerie(Serie serie, Long id);
	
	public ResponseEntity<SerieResponseRest> deleteSerie(Long id);
	
	
}
