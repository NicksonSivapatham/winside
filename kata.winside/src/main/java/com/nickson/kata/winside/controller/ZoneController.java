package com.nickson.kata.winside.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nickson.kata.winside.dao.ReaderDao;
import com.nickson.kata.winside.model.PointOfInterest;
import com.nickson.kata.winside.model.Zone;
import com.nickson.kata.winside.service.PoiFinder;
import com.nickson.kata.winside.service.ZoneDeterminer;

@RestController
@RequestMapping("/api/v1")
public class ZoneController {

	@Autowired
	private ReaderDao readerDao;
	
	@Autowired
	private ZoneDeterminer zoneDeterminer;
	
	@GetMapping("/zone/dense/{number}")
	public ResponseEntity getMostDenseZones(@PathVariable Integer number) {
		List<PointOfInterest> listOfPoi = new ArrayList<>();
		try {
			listOfPoi = readerDao.read().stream()
												.map(PointOfInterest.class::cast)
												.collect(Collectors.toList());
		} catch (IOException e) {
			ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(zoneDeterminer.retrieveDenseZone(listOfPoi, number));
	}
}
