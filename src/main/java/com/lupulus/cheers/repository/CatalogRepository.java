package com.lupulus.cheers.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lupulus.cheers.domain.Beer;
import com.lupulus.cheers.domain.Manufacturer;
import com.lupulus.cheers.web.controller.request.AddBeerRequest;
import com.lupulus.cheers.web.controller.request.AddManufacturerRequest;

public interface CatalogRepository {
	
	/*
	 * BEERS
	 */

	Page<Beer> getBeers(Pageable pageable);
	
	Beer getBeer(Integer id);
	
	Beer addBeer(AddBeerRequest beer);
	
	Beer updateBeer(Beer beer);
	
	void deleteBeer(Integer id);
	
	/*
	 * MANUFACTURERS
	 */
	
	Page<Manufacturer> getManufacturers(Pageable pageable);
	
	Manufacturer getManufacturer(Integer id);
	
	Manufacturer addManufacturer(AddManufacturerRequest manufacturer);
	
	Manufacturer updateManufacturer(Manufacturer manufacturer);
	
	void deleteManufacturer(Integer id);

}
