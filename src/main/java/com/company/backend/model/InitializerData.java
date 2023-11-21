package com.company.backend.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.company.backend.dao.ISerieDao;

@Component
public class InitializerData implements CommandLineRunner {

	@Autowired
	private ISerieDao serieDao;
	
	
	@Override
	public void run(String... args) throws Exception {
		Serie serie = new Serie();
		serie.setId((long) 1);
		serie.setName("Pelicula");
		serie.setRating(4);
		serie.setReleaseYear("2014");
		serie.setDescription("Buena peli");
		
		serieDao.save(serie);
		
		 System.out.println("Datos inicializados.");
		
	}

}
