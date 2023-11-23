package com.company.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.backend.dao.ISerieDao;
import com.company.backend.model.Serie;
import com.company.backend.response.SerieResponseRest;

@Service
public class SerieServiceImpl implements ISerieService {
	
	private static final Logger log = LoggerFactory.getLogger(SerieServiceImpl.class); 
	
	@Autowired
	private ISerieDao serieDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<SerieResponseRest> getAllSeries() {
		
			log.info("Executing getAllSeries");
			SerieResponseRest response = new SerieResponseRest();
			
			try {				
				List<Serie> series = (List<Serie>) serieDao.findAll();
				response.getSerieResponse().setSerie(series);
				response.setMetadata("Response OK", "00", "successful response");
				
			} catch (Exception e) {
				log.error("Error response" , e.getMessage());
				e.getStackTrace();
				return new ResponseEntity<SerieResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<SerieResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<SerieResponseRest> getSerieById(long id) {
		
		log.info("Executing setSerieById");
		List<Serie> list = new ArrayList<Serie>();
		SerieResponseRest response = new SerieResponseRest();		
			
		try {
			
			Serie serie = serieDao.findById(id);
			
			if (serie != null) {				
				list.add(serie);
				response.getSerieResponse().setSerie(list);
				response.setMetadata("Response OK", "00", "successful response");
			}else {
				log.error("Serie by id request failed");
				response.setMetadata("Failed response", "-1", "Serie not found");
				return new ResponseEntity<SerieResponseRest>(response, HttpStatus.NOT_FOUND);
			}		
			
		} catch (Exception e) {
			log.error("Error response" , e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<SerieResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SerieResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SerieResponseRest> createSerie(Serie serie) {
		
		log.info("Executing createSerie");
		List<Serie> list = new ArrayList<Serie>();
		SerieResponseRest response = new SerieResponseRest();
		
		try {
			serieDao.save(serie);
			list.add(serie);
			response.getSerieResponse().setSerie(list);
			response.setMetadata("Response OK", "00", "successful response");			
			
		} catch (Exception e) {
			log.error("Error response. Uncreated serie" , e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<SerieResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		return new ResponseEntity<SerieResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SerieResponseRest> updateSerie(Serie serie, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<SerieResponseRest> deleteSerie(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	

}
