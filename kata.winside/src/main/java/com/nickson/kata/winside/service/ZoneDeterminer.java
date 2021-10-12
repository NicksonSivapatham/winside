package com.nickson.kata.winside.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nickson.kata.winside.model.PointOfInterest;
import com.nickson.kata.winside.model.Zone;

@Service
public class ZoneDeterminer {
	
	@Value("${properties.map.step}")
	Double step;

	/**
	 * Return the zone corresponding to the POI
	 * @param poi
	 * @return
	 */
	public Zone determineZone(PointOfInterest poi) {
		Double minLat = roundDown(poi.getLatitude());
		Double minLong = roundDown(poi.getLongitude());
		Double maxLat = roundUp(poi.getLatitude());
		Double maxLong = roundUp(poi.getLongitude());
		return new Zone (minLat, minLong, maxLat, maxLong);
	}
	
	/**
	 * Round down the number according to the step propertie
	 * @param number
	 */
	private Double roundDown(Double number) {
		if (number % step == 0) {
			return number;
		} else if (number > 0) {
			return number - (number % this.step);
		} else {
			return number - (this.step + (number % this.step));
		}	
	}
	
	/**
	 * Round up the number according to the step propertie
	 * @param number
	 */
	private Double roundUp(Double number) {
		if (number > 0 || number % step == 0 ) {
			return number + (this.step - (number % this.step));
		} else {
			return number - (number % this.step);
		} 
	}
	
	/**
	 * Return a list at given size param which contains the dense zones
	 * @param numberOfZone
	 * @return
	 */
	public List<Zone> retrieveDenseZone(List<PointOfInterest> listOfPoi, Integer numberOfZone) {
		Map<Zone, Long> zoneToNumberOfPOI = listOfPoi.stream()
														.collect(Collectors.groupingBy(poi -> determineZone(poi), Collectors.counting()));
		return zoneToNumberOfPOI.entrySet()
								.stream()
								.sorted(Entry.<Zone, Long>comparingByValue().reversed())
								.limit(numberOfZone)
								.map(Entry::getKey)
								.collect(Collectors.toList());
	}
}
