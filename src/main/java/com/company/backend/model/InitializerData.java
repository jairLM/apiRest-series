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
		serie.setName("Breaking Bad");
		serie.setRating(5);
		serie.setReleaseYear("2008");
		serie.setDescription("Excelente");
		
		
		serie1.setName("Arcane");
		serie1.setRating(4);
		serie1.setReleaseYear("2021");
		serie1.setDescription("Buena");
		
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
