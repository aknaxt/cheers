package com.lupulus.cheers.repository.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.lupulus.cheers.domain.Beer;
import com.lupulus.cheers.domain.Manufacturer;
import com.lupulus.cheers.repository.BeerRepository;
import com.lupulus.cheers.repository.CatalogRepository;
import com.lupulus.cheers.repository.ManufacturerRepository;
import com.lupulus.cheers.repository.entity.BeerData;
import com.lupulus.cheers.repository.entity.ManufacturerData;
import com.lupulus.cheers.repository.implementation.mock.MockCatalogRepositoryImpl;
import com.lupulus.cheers.repository.profile.ConditionMockRepository;
import com.lupulus.cheers.repository.profile.ConditionRealRepository;
import com.lupulus.cheers.web.controller.request.AddBeerRequest;
import com.lupulus.cheers.web.controller.request.AddManufacturerRequest;
import com.lupulus.cheers.web.controller.request.UpdateBeerRequest;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Conditional(ConditionRealRepository.class)
public class CatalogRepositoryImpl implements CatalogRepository {

	@Autowired
	ManufacturerRepository manufacturerRepository;
	
	@Autowired
	BeerRepository beerRepository;
	
	@Override
	public Page<Beer> getBeers(Pageable pageable) {
		Page<BeerData> beers= beerRepository.findAll(pageable);		
		return beers.map(n -> new Beer(n.getId(), n.getName(), n.getGraduation(), n.getType(), n.getDescription(), convertManufacturerEntityToDTO(n.getManufacturer())));
	}

	@Override
	public Beer getBeer(Integer id) {
		try
		{
			BeerData data = beerRepository.getById(id);
			return  new Beer(data.getId(), data.getName(), data.getGraduation(), data.getType(), data.getDescription(), convertManufacturerEntityToDTO(data.getManufacturer()));
		}
		catch (EntityNotFoundException ex)
		{
			//FIXME: Workaround for NotFoundAction.IGNORE not working for some reason 
			return null;
		}
	}
	
	private Manufacturer convertManufacturerEntityToDTO(ManufacturerData entity)
	{
		Manufacturer dto = new Manufacturer();		
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	@Override
	public Beer addBeer(AddBeerRequest beer) {
		BeerData entity = new BeerData();
		BeanUtils.copyProperties(beer, entity);		
		BeerData data = beerRepository.save(entity);
		return new Beer(data.getId(), data.getName(), data.getGraduation(), data.getType(), data.getDescription(), convertManufacturerEntityToDTO(manufacturerRepository.getOne(beer.getManufacturerId())));
	}

	@Override
	public Beer updateBeer(UpdateBeerRequest beer) {
		BeerData entity = new BeerData();
		if(!beerRepository.existsById(beer.getId()))
			return null;
		BeanUtils.copyProperties(beer, entity);
		BeerData data = beerRepository.save(entity);
		return new Beer(data.getId(), data.getName(), data.getGraduation(), data.getType(), data.getDescription(), convertManufacturerEntityToDTO(manufacturerRepository.getOne(beer.getManufacturerId())));
	}

	@Override
	public void deleteBeer(Integer id) {
		beerRepository.deleteById(id);			
	}

	@Override
	public Page<Manufacturer> getManufacturers(Pageable pageable) {
		Page<ManufacturerData> manufacturers= manufacturerRepository.findAll(pageable);
		return manufacturers.map(n -> new Manufacturer(n.getId(), n.getName(), n.getNationality()));		
	}

	@Override
	public Manufacturer getManufacturer(Integer id) {
		try
		{
			ManufacturerData data = manufacturerRepository.getById(id);
			return convertManufacturerEntityToDTO(data);
		}
		catch (Exception ex)
		{
			log.error("error getting manufacturer with id " + id,ex);
			//FIXME: Workaround for NotFoundAction.IGNORE not working for some reason 
			return null;
		}
	}

	@Override
	public Manufacturer addManufacturer(AddManufacturerRequest manufacturer) {
		ManufacturerData data = new ManufacturerData();
		BeanUtils.copyProperties(manufacturer, data);
		ManufacturerData saved = manufacturerRepository.save(data);
		Manufacturer result = new Manufacturer();
		BeanUtils.copyProperties(saved, result);
		return result;
	}

	@Override
	public Manufacturer updateManufacturer(Manufacturer manufacturer) {
		ManufacturerData data = new ManufacturerData();
		if(!manufacturerRepository.existsById(manufacturer.getId()))
			return null;
		BeanUtils.copyProperties(manufacturer, data);
		ManufacturerData saved = manufacturerRepository.save(data);
		Manufacturer result = new Manufacturer();
		BeanUtils.copyProperties(saved, result);
		return result;
	}

	@Override
	public void deleteManufacturer(Integer id) {
		manufacturerRepository.deleteById(id);		
	}

}
