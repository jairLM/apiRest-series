package com.company.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.backend.model.Serie;
import com.company.backend.service.ISerieService;


@RestController
@RequestMapping("/v1")
public class SerieRestController {
	
	@Autowired
	private ISerieService serieService;
	
	
	@GetMapping("/series")
	public List<Serie> 	getSeriesRequest(){
		List<Serie> response = serieService.getAllSeries();
		return response;
	}
	
	

}
