package com.nickson.kata.winside.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nickson.kata.winside.model.PointOfInterest;
import com.nickson.kata.winside.model.Zone;

@SpringBootTest
class PoiFinderTests {
	
	@Autowired
	private PoiFinder poiFinder;
	private List<PointOfInterest> testData;

	@BeforeEach
	public void setup() {
	  this.testData = prepareTestDataSet();
	}
	
	private List<PointOfInterest> prepareTestDataSet() {	
		return new ArrayList<>(Arrays.asList(
				new PointOfInterest("id1", -48.6, -37.7),
				new PointOfInterest("id2", -27.1, 8.4),
				new PointOfInterest("id3", 6.6, -6.9),
				new PointOfInterest("id4", -2.3, 38.3),
				new PointOfInterest("id5", 6.8, -6.9),
				new PointOfInterest("id6", -2.5, 38.3),
				new PointOfInterest("id7", 0.1, -0.1),
				new PointOfInterest("id8", -2.1, 38.1)
				));
	}

	@Test
	void find() {
		long result;
		Zone zoneCriteria = new Zone(6.5, -7., null, null);
		result = poiFinder.find(this.testData, zoneCriteria);
		assertEquals(2,result);
	}
}
