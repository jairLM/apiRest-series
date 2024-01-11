package com.company.backend.serieRestControllerTest;

import com.company.backend.dao.ISerieDao;
import com.company.backend.model.Serie;
import com.company.backend.response.SerieResponseRest;

import com.company.backend.service.SerieServiceImpl;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GetSerieTest {
    @Autowired
    private SerieServiceImpl serieService;
    @Autowired
    private ISerieDao serieDao;

    @Test
    public void getSerieAssert(){
        SerieResponseRest response = new SerieResponseRest();
        List<Serie> series = (List<Serie>) serieDao.findAll();
        ResponseEntity<SerieResponseRest> entity = serieService.getAllSeries();

        assertEquals(entity.getStatusCode(), HttpStatus.OK);

    }


}
