package com.company.backend.service;


import org.springframework.http.ResponseEntity;


import com.company.backend.response.SerieResponseRest;

public interface ISerieService {

	public ResponseEntity<SerieResponseRest> getAllSeries();	
	
	public ResponseEntity<SerieResponseRest> getSerieById(long id);
	
}
