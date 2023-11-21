package com.company.backend.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import com.company.backend.dao.ISerieDao;

@Component //esta clase se crea para borrar los datos al cerrar la app de la base de datos
public class CloseAppListener implements ApplicationListener<ContextClosedEvent>{

	@Autowired
	private ISerieDao serieDao;
	
	@Override
	public void onApplicationEvent(ContextClosedEvent event) {

	serieDao.deleteAll();
		
		
	}

}
