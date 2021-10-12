package com.nickson.kata.winside.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nickson.kata.winside.model.PointOfInterest;


@SpringBootTest
class TsvReaderPoiDaoTest {
	
	Logger logger = LoggerFactory.getLogger(TsvReaderPoiDaoTest.class);
	
	@Autowired
	private TsvReaderPoiDao tsvReaderPoiDao;

	@Test
	 void readFile() {
	    List<PointOfInterest> expectedData = new ArrayList<>(Arrays.asList(
				new PointOfInterest("id1", -48.6, -37.7),
				new PointOfInterest("id2", -27.1, 8.4),
				new PointOfInterest("id3", 6.6, -6.9),
				new PointOfInterest("id4", -2.3, 38.3),
				new PointOfInterest("id5", 6.8, -6.9),
				new PointOfInterest("id6", -2.5, 38.3),
				new PointOfInterest("id7", 0.1, -0.1),
				new PointOfInterest("id8", -2.1, 38.1)
				));
	    List<Object> lines = null;
		try {
			lines = tsvReaderPoiDao.read();
		} catch (IOException e) {
			logger.error("Erreur lors de la lecture du fichier test", e);
		}
	    assertThat(expectedData).isEqualTo(lines);     
	}
	
}
