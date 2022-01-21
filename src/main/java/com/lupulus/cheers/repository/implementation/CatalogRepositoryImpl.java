package com.lupulus.cheers.repository.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.lupulus.cheers.domain.Beer;
import com.lupulus.cheers.repository.CatalogRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CatalogRepositoryImpl implements CatalogRepository {

	public Page<Beer> getBeers(Pageable pageable) {

		List<Beer> list = new ArrayList<Beer>();
		
		list.add(new Beer(1,"Heineken",5.5,"Heineken","Lager", "description Heineken"));
		list.add(new Beer(2,"Estrella",4.7,"Damm","Lager","description Estrella"));
		list.add(new Beer(3,"Guiness",8.9,"Guiness Co.","Stout","description Guiness"));
		list.add(new Beer(4,"Paulaner",6.1,"Paulaner Co","Witbier","description Paulaner"));

		PagedListHolder<Beer> pageHolder = new PagedListHolder<Beer>(list);
		pageHolder.setPage(pageable.getPageNumber());
		pageHolder.setPageSize(pageable.getPageSize());
		Page<Beer> result = new PageImpl<>(pageHolder.getPageList(),pageable,pageHolder.getPageList().size());
		return result;
	}
}
