package com.company.backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.backend.dao.ISerieDao;
import com.company.backend.model.Serie;

@Service
public class SerieServiceImpl implements ISerieService {
	
	private static final Logger log = LoggerFactory.getLogger(SerieServiceImpl.class); 
	
	@Autowired
	private ISerieDao serieDao;

	@Override
	@Transactional(readOnly = true)
	public List<Serie> getAllSeries() {
		try {
			log.info("ejecutando");
			List<Serie> series = (List<Serie>) serieDao.findAll();
			return series;
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Serie getSerieById(Long id) {
		try {
			log.info("Ejecutando");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}


	
	
	

}
