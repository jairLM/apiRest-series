package com.company.backend.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.company.backend.dao.ISerieDao;

@Component
@Configuration //esta clase se crea para incializar datos en la base de datos
public class InitializerData implements CommandLineRunner {

	@Autowired
	private ISerieDao serieDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public void run(String... args) throws Exception {
		Serie serie = new Serie();
		Serie serie1 = new Serie();
		
		serie.setId((long) 1);
		serie.setName("Pelicula");
		serie.setRating(4);
		serie.setReleaseYear("2014");
		serie.setDescription("Buena peli");
		
		
		serie1.setName("Pelicula 2");
		serie1.setRating(5);
		serie1.setReleaseYear("2017");
		serie1.setDescription("Mala peli");
		
		serieDao.save(serie);
		serieDao.save(serie1);
		
		 System.out.println("Datos inicializados.");
		
	}
	
	@Bean
	public CommandLineRunner createPasswordsCommand() {
		return args ->{
			
			System.out.println(passwordEncoder.encode("marquez456"));  
			System.out.println(passwordEncoder.encode("jair123"));  
			
		};
	}
	

}
