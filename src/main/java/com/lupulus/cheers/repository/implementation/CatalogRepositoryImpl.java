package com.lupulus.cheers.repository.implementation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lupulus.cheers.domain.Beer;
import com.lupulus.cheers.domain.Manufacturer;
import com.lupulus.cheers.repository.CatalogRepository;
import com.lupulus.cheers.web.controller.request.AddBeerRequest;
import com.lupulus.cheers.web.controller.request.AddManufacturerRequest;
import com.lupulus.cheers.web.controller.request.UpdateBeerRequest;

public class CatalogRepositoryImpl implements CatalogRepository {

	@Override
	public Page<Beer> getBeers(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Beer getBeer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Beer addBeer(AddBeerRequest beer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Beer updateBeer(UpdateBeerRequest beer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBeer(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Manufacturer> getManufacturers(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manufacturer getManufacturer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manufacturer addManufacturer(AddManufacturerRequest manufacturer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manufacturer updateManufacturer(Manufacturer manufacturer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteManufacturer(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
