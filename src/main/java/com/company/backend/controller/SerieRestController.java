package com.company.backend.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.backend.model.Serie;
import com.company.backend.response.SerieResponseRest;
import com.company.backend.service.ISerieService;


@RestController
@RequestMapping("/v1")
public class SerieRestController {
	
	@Autowired
	private ISerieService serieService;
	
	
	@GetMapping("/series")
	public ResponseEntity<SerieResponseRest> getSeriesRequest(){
		ResponseEntity<SerieResponseRest> response = serieService.getAllSeries();
		return response;
	}
	
	@GetMapping("/series/{id}")
	public ResponseEntity<SerieResponseRest> getSerieByIdRequest(@PathVariable Long id){
		ResponseEntity<SerieResponseRest> response = serieService.getSerieById(id);
		return response;
	}
	
	@PostMapping("/series")
	public ResponseEntity<SerieResponseRest> postSerieRequest(@RequestBody Serie serie){	
		ResponseEntity<SerieResponseRest> response = serieService.createSerie(serie);
		return response;
		
	}
	
	@PutMapping("/series/{id}")
	public ResponseEntity<SerieResponseRest> putSerieRequest(@RequestBody Serie serie, @PathVariable Long id){
		ResponseEntity<SerieResponseRest> response = serieService.updateSerie(serie, id);
		return response;
	}
	
	@DeleteMapping("/series/{id}")
	public ResponseEntity<SerieResponseRest> deleteSerieRequest(@PathVariable Long id){
		ResponseEntity<SerieResponseRest> response = serieService.deleteSerie(id);
		return response;
		
	}
	
	

}
