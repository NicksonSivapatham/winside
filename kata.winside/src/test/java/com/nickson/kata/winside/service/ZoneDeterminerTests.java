package com.nickson.kata.winside.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nickson.kata.winside.model.PointOfInterest;
import com.nickson.kata.winside.model.Zone;

@SpringBootTest
class ZoneDeterminerTests {
	
	@Autowired
	private ZoneDeterminer zoneDeterminer;

	@Test
	void determineZone() {
		PointOfInterest poi = new PointOfInterest("id1", -2.1, 38.1);
		assertEquals(new Zone(-2.5, 38.0, -2.0, 38.5), zoneDeterminer.determineZone(poi));
		poi = new PointOfInterest("id1", 0.1, -0.1);
		assertEquals(new Zone(0., -0.5, 0.5, 0.), zoneDeterminer.determineZone(poi));
		poi = new PointOfInterest("id1", -2.5, -3.);
		assertEquals(new Zone(-2.5, -3., -2.0, -2.5), zoneDeterminer.determineZone(poi));
		poi = new PointOfInterest("id1", 2.5, 3.);
		assertEquals(new Zone(2.5, 3., 3., 3.5), zoneDeterminer.determineZone(poi));
	}
	
	@Test
	void retrieveDenseZone() {
		 List<Zone> expectedZones = new ArrayList<>(Arrays.asList(
					new Zone(-2.5, 38., -2., 38.5),
					new Zone(6.5, -7., 7., -6.5)
					));
		List<PointOfInterest> listOfPOI = new ArrayList<>(Arrays.asList(
															new PointOfInterest("id1", -48.6, -37.7),
															new PointOfInterest("id2", -27.1, 8.4),
															new PointOfInterest("id3", 6.6, -6.9),
															new PointOfInterest("id4", -2.3, 38.3),
															new PointOfInterest("id5", 6.8, -6.9),
															new PointOfInterest("id6", -2.5, 38.3),
															new PointOfInterest("id7", 0.1, -0.1),
															new PointOfInterest("id8", -2.1, 38.1)
															));
		List<Zone> zones = zoneDeterminer.retrieveDenseZone(listOfPOI, 2);
		assertEquals(expectedZones, zones);
	}
}
