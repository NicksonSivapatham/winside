package com.nickson.kata.winside.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.nickson.kata.winside.model.PointOfInterest;
import com.nickson.kata.winside.model.Zone;

@Service
public class PoiFinder {
	
	/**
	 * Return the number of POI that match the criteria
	 * @param listOfPOI
	 * @param criteria
	 * @return
	 */
	public long find(List<PointOfInterest> listOfPoi, Zone zoneCriteria) {
		List<Predicate<PointOfInterest>> listOfCriteria = new ArrayList<>();
		
		if (zoneCriteria.getMinLatitude() != null) {
			listOfCriteria.add(poi -> poi.getLatitude() > zoneCriteria.getMinLatitude());
		}
		if (zoneCriteria.getMinLongitude() != null) {
			listOfCriteria.add(poi -> poi.getLongitude() > zoneCriteria.getMinLongitude());
		}
		if (zoneCriteria.getMaxLatitude() != null) {
			listOfCriteria.add(poi -> poi.getLatitude() < zoneCriteria.getMaxLatitude());
		}
		if (zoneCriteria.getMaxLongitude() != null) {
			listOfCriteria.add(poi -> poi.getLongitude() < zoneCriteria.getMaxLongitude());
		}
		
		return listOfPoi.stream()
						.filter(listOfCriteria.stream().reduce(x->true, Predicate::and))
						.count();
	}
}
