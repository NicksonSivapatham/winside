package com.nickson.kata.winside.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nickson.kata.winside.dao.ReaderDao;
import com.nickson.kata.winside.model.PointOfInterest;
import com.nickson.kata.winside.model.Zone;
import com.nickson.kata.winside.service.PoiFinder;

@RestController
@RequestMapping("/api/v1")
public class PoiController {

	@Autowired
	private ReaderDao readerDao;
	
	@Autowired
	private PoiFinder poiFinder;
	
	@GetMapping("/poi/zone")
	public ResponseEntity getNumberOfPoiInZone(@RequestBody Zone zone) {
		List<PointOfInterest> listOfPoi = new ArrayList<>();
		try {
			listOfPoi = readerDao.read().stream()
												.map(PointOfInterest.class::cast)
												.collect(Collectors.toList());
		} catch (IOException e) {
			ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(poiFinder.find(listOfPoi, zone));
	}
}
