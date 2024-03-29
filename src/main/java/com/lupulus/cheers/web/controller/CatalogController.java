package com.lupulus.cheers.web.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lupulus.cheers.domain.Beer;
import com.lupulus.cheers.domain.Manufacturer;
import com.lupulus.cheers.service.CatalogService;
import com.lupulus.cheers.web.controller.request.AddBeerRequest;
import com.lupulus.cheers.web.controller.request.AddManufacturerRequest;
import com.lupulus.cheers.web.controller.request.UpdateBeerRequest;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/public/v1/catalogue", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class CatalogController {
	
	@Autowired
	CatalogService catalogService;
	
	
	@GetMapping(value = "/beers")
	public ResponseEntity<Page<Beer>> getBeers(@RequestParam(required=false,defaultValue="") String search, Pageable pageable)
	{
		log.debug("getting beers ...");
		return new ResponseEntity<>(catalogService.getBeers(search, pageable), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/beer/{id}")
	public ResponseEntity<Beer> getBeer(@PathVariable("id") Integer id)
	{
		log.debug("getting beer ...");
		Beer beer = catalogService.getBeer(id);
		return new ResponseEntity<>(beer, beer==null?HttpStatus.NOT_FOUND:HttpStatus.OK);
	}
	
	@PreAuthorize("hasPermission(#beer.manufacturerId,'AddBeerRequest', 'MANUFACTURER')")
	@PostMapping(value = "/beer")
	public ResponseEntity<Beer> addBeer(@Valid @RequestBody AddBeerRequest beer)
	{
		log.debug("adding beer ...");
		return new ResponseEntity<>(catalogService.addBeer(beer), HttpStatus.OK);
	}	
	
	@PreAuthorize("hasPermission(#beer.manufacturerId,'UpdateBeerRequest', 'MANUFACTURER')")
	@PutMapping(value = "/beer")
	public ResponseEntity<Beer> updateBeer(@Valid @RequestBody UpdateBeerRequest beer)
	{
		log.debug("updating beer ...");
		Beer updated = catalogService.updateBeer(beer);
		return new ResponseEntity<>(updated, updated==null?HttpStatus.NOT_FOUND:HttpStatus.OK);
	}
	
	@PreAuthorize("hasPermission(#id,'DeleteBeerRequest', 'MANUFACTURER')")
	@DeleteMapping(value = "/beer")
	public ResponseEntity<Void> deleteBeer(@NotNull @Valid @RequestParam(value = "id", required = true) Integer id)
	{
		log.debug("deleting beer ...");
		catalogService.deleteBeer(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/*
	 * MANUFACTURERS
	 */
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/manufacturers")
	public ResponseEntity<Page<Manufacturer>> getManufacturers(Pageable pageable)
	{
		log.debug("getting manufacturers ...");
		return new ResponseEntity<>(catalogService.getManufacturers(pageable), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/manufacturer/{id}")
	public ResponseEntity<Manufacturer> getManufacturer(@PathVariable("id") Integer id)
	{
		log.debug("getting Manufacturer ...");
		Manufacturer manufacturer = catalogService.getManufacturer(id);
		return new ResponseEntity<>(manufacturer, manufacturer==null?HttpStatus.NOT_FOUND:HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value = "/manufacturer")
	public ResponseEntity<Manufacturer> addManufacturer(@Valid @RequestBody AddManufacturerRequest manufacturer)
	{
		log.debug("adding Manufacturer ...");
		return new ResponseEntity<>(catalogService.addManufacturer(manufacturer), HttpStatus.OK);
	}	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping(value = "/manufacturer")
	public ResponseEntity<Manufacturer> updateManufacturer(@Valid @RequestBody Manufacturer manufacturer)
	{
		log.debug("updating Manufacturer ...");
		Manufacturer updated = catalogService.updateManufacturer(manufacturer);
		return new ResponseEntity<>(updated, updated==null?HttpStatus.NOT_FOUND:HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping(value = "/manufacturer")
	public ResponseEntity<Void> deleteManufacturer(@NotNull @Valid @RequestParam(value = "id", required = true) Integer id)
	{
		log.debug("deleting Manufacturer ...");
		catalogService.deleteManufacturer(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
