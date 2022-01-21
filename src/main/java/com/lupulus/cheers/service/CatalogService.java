package com.lupulus.cheers.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lupulus.cheers.domain.Beer;

public interface CatalogService {

	Page<Beer> getBeers(Pageable pageable);
}
