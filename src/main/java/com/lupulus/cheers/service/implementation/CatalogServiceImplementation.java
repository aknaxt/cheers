package com.lupulus.cheers.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.lupulus.cheers.domain.Beer;
import com.lupulus.cheers.domain.Manufacturer;
import com.lupulus.cheers.repository.CatalogRepository;
import com.lupulus.cheers.service.CatalogService;
import com.lupulus.cheers.web.controller.request.AddBeerRequest;
import com.lupulus.cheers.web.controller.request.AddManufacturerRequest;
import com.lupulus.cheers.web.controller.request.UpdateBeerRequest;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CatalogServiceImplementation implements CatalogService {

	@Autowired
	CatalogRepository catalogRepositoy;

	/*
	 * BEERS
	 */
	
	public Page<Beer> getBeers(Pageable pageable) {

		return catalogRepositoy.getBeers(pageable);

	}

	@Override
	public Beer getBeer(Integer id) {
		return catalogRepositoy.getBeer(id);
	}

	@Override
	public Beer addBeer(AddBeerRequest beer) {
		return catalogRepositoy.addBeer(beer) ;
	}

	@Override
	public Beer updateBeer(UpdateBeerRequest beer) {
		return catalogRepositoy.updateBeer(beer);
	}

	@Override
	public void deleteBeer(Integer id) {
		catalogRepositoy.deleteBeer(id);
	}
	
	/*
	 * MANUFACTURERS
	 */

	@Override
	public Page<Manufacturer> getManufacturers(Pageable pageable) {
		return catalogRepositoy.getManufacturers(pageable);
	}

	@Override
	public Manufacturer getManufacturer(Integer id) {
		return catalogRepositoy.getManufacturer(id);
	}

	@Override
	public Manufacturer addManufacturer(AddManufacturerRequest manufacturer) {
		return catalogRepositoy.addManufacturer(manufacturer);
	}

	@Override
	public Manufacturer updateManufacturer(Manufacturer manufacturer) {
		return catalogRepositoy.updateManufacturer(manufacturer);
	}

	@Override
	public void deleteManufacturer(Integer id) {
		catalogRepositoy.deleteManufacturer(id);		
	}

}
