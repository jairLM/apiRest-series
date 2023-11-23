package com.company.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		log.info("Executing updateSerie");
		SerieResponseRest response = new SerieResponseRest();
		List<Serie> serieChanged = new ArrayList<Serie>();
		
		Optional<Serie> serieFound = serieDao.findById(id);
		if (serieFound.isPresent()) {
			try {
				serieFound.get().setId(serie.getId());
				serieFound.get().setName(serie.getName());
				serieFound.get().setReleaseYear(serie.getReleaseYear());
				serieFound.get().setRating(serie.getRating());
				serieFound.get().setDescription(serie.getDescription());
				
				serieDao.save(serieFound.get());
				
				serieChanged.add(serieFound.get());
				response.getSerieResponse().setSerie(serieChanged);
				response.setMetadata("Response OK", "00", "successful response");	
				
			} catch (Exception e) {
				log.error("Error response. Unchanged series" , e.getMessage());
				e.getStackTrace();
				return new ResponseEntity<SerieResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			log.error("Error, Serie not found");			
			return new ResponseEntity<SerieResponseRest>(response, HttpStatus.NOT_FOUND);
		}
		
		
		return new ResponseEntity<SerieResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SerieResponseRest> deleteSerie(Long id) {
		log.info("Executing deleteSerie");
		SerieResponseRest response = new SerieResponseRest();
		List<Serie> serieDeleted = new ArrayList<Serie>();
		
		Optional<Serie> serieFound = serieDao.findById(id);
		
		if (serieFound.isPresent()) {
			try {
				
				serieDao.delete(serieFound.get());
				
				serieDeleted.add(serieFound.get());
				response.getSerieResponse().setSerie(serieDeleted);
				response.setMetadata("Response OK", "00", "successful response");	
			
				
			} catch (Exception e) {
				log.error("Error response. Undeleted serie" , e.getMessage());
				e.getStackTrace();
				return new ResponseEntity<SerieResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			log.error("Error, Serie not found");			
			return new ResponseEntity<SerieResponseRest>(response, HttpStatus.NOT_FOUND);
		}
		
		
		
		return new ResponseEntity<SerieResponseRest>(response, HttpStatus.OK);
	}


	
	
	

}
