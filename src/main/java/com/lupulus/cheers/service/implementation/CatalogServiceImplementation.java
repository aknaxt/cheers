package com.lupulus.cheers.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.lupulus.cheers.domain.Beer;
import com.lupulus.cheers.repository.CatalogRepository;
import com.lupulus.cheers.service.CatalogService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CatalogServiceImplementation implements CatalogService {

	@Autowired
	CatalogRepository catalogRepositoy;

	public Page<Beer> getBeers(Pageable pageable) {

		return catalogRepositoy.getBeers(pageable);

	}

}
