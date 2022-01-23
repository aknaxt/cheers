package com.lupulus.cheers.repository.implementation.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.lupulus.cheers.repository.CatalogRepository;
import com.lupulus.cheers.repository.profile.ConditionMockRepository;
import com.lupulus.cheers.web.controller.request.AddBeerRequest;
import com.lupulus.cheers.web.controller.request.AddManufacturerRequest;
import com.lupulus.cheers.web.controller.request.UpdateBeerRequest;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Conditional(ConditionMockRepository.class)
public class MockCatalogRepositoryImpl implements CatalogRepository {
	
	@Autowired
	List<Beer> beers;
	
	@Autowired
	List<Manufacturer> manufacturers;

	public Page<Beer> getBeers(Pageable pageable) {
		PagedListHolder<Beer> pageHolder = new PagedListHolder<Beer>(beers);
		pageHolder.setPage(pageable.getPageNumber());
		pageHolder.setPageSize(pageable.getPageSize());
		int offset = pageable.getPageSize()*pageable.getPageNumber();
		int size = beers.size();
		Page<Beer> result = new PageImpl<>(offset+1>size?new ArrayList<>():pageHolder.getPageList(),pageable,size);
		return result;
	}

	@Override
	public Beer getBeer(Integer id) {	
		Optional<Beer> opt = beers.stream().filter(beer -> id == beer.getId()).findFirst();
		return opt.isPresent()?opt.get():null;				
	}

	@Override
	public Beer addBeer(AddBeerRequest beer) {
		Manufacturer manufacturer = getManufacturer(beer.getManufacturerId());
		//get highest id		
		int id = beers.stream().mapToInt(b -> b.getId()).max().getAsInt();
		//create beer and set id+1
		Beer data = new Beer();
		BeanUtils.copyProperties(beer, data);
		data.setId(++id);
		data.setManufacturer(manufacturer);
		beers.add(data);
		return data;
	}

	@Override
	public Beer updateBeer(UpdateBeerRequest beer) {
		Manufacturer manufacturer = getManufacturer(beer.getManufacturerId());
		Beer data = this.getBeer(beer.getId());
		if(data == null)
			return null;
		BeanUtils.copyProperties(beer, data);
		data.setManufacturer(manufacturer);
		return data;
	}

	@Override
	public void deleteBeer(Integer id) {
		Beer data = this.getBeer(id);
		beers.remove(data);
	}

	/*
	 * MANUFACTURERS
	 */
	
	
	@Override
	public Page<Manufacturer> getManufacturers(Pageable pageable) {
		PagedListHolder<Manufacturer> pageHolder = new PagedListHolder<Manufacturer>(manufacturers);
		pageHolder.setPage(pageable.getPageNumber());
		pageHolder.setPageSize(pageable.getPageSize());
		int offset = pageable.getPageSize()*pageable.getPageNumber();
		int size = beers.size();
		Page<Manufacturer> result = new PageImpl<>(offset+1>size?new ArrayList<>():pageHolder.getPageList(),pageable,pageHolder.getPageList().size());
		return result;
	}

	@Override
	public Manufacturer getManufacturer(Integer id) {
		Optional<Manufacturer> opt = manufacturers.stream().filter(beer -> id == beer.getId()).findFirst();
		return opt.isPresent()?opt.get():null;				
	}

	@Override
	public Manufacturer addManufacturer(AddManufacturerRequest manufacturer) {
		//get highest id
		int id = manufacturers.stream().mapToInt(b -> b.getId()).max().getAsInt();
		//create beer and set id+1
		Manufacturer data = new Manufacturer();
		BeanUtils.copyProperties(manufacturer, data);
		data.setId(++id);
		manufacturers.add(data);
		return data;
	}

	@Override
	public Manufacturer updateManufacturer(Manufacturer manufacturer) {
		Manufacturer data = this.getManufacturer(manufacturer.getId());
		if(data == null)
			return null;
		BeanUtils.copyProperties(manufacturer, data);
		return data;
	}

	@Override
	public void deleteManufacturer(Integer id) {
		Manufacturer data = this.getManufacturer(id);
		manufacturers.remove(data);
		
	}
}
