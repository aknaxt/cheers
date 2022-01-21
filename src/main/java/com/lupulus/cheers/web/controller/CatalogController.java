package com.lupulus.cheers.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lupulus.cheers.domain.Beer;
import com.lupulus.cheers.service.CatalogService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/public/v1/catalogue", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class CatalogController {
	
	@Autowired
	CatalogService catalogService;
	

	@GetMapping(value = "/beers")
	public ResponseEntity<Page<Beer>> getBeers(
			@RequestParam(defaultValue = "0", required = true) Integer page, 
            @RequestParam(defaultValue = "10", required = true) Integer size,
            @RequestParam(defaultValue = "id", required = false) String sort )
	{
		log.debug("getting beers ...");
		return new ResponseEntity<>(catalogService.getBeers(PageRequest.of(page, size)), HttpStatus.OK);
	}	
	
}
