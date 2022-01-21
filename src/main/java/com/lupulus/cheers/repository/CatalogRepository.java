package com.lupulus.cheers.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lupulus.cheers.domain.Beer;

public interface CatalogRepository {

	Page<Beer> getBeers(Pageable pageable);

}
