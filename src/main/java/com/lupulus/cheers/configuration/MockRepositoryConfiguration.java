package com.lupulus.cheers.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.lupulus.cheers.domain.Beer;
import com.lupulus.cheers.domain.Manufacturer;
import com.lupulus.cheers.repository.profile.ConditionMockRepository;

@Configuration
@Conditional(ConditionMockRepository.class)
public class MockRepositoryConfiguration {

	@Bean
	public List<Beer> getBeersList() {
		List<Beer> list = new ArrayList<Beer>();
		list.add(new Beer(1, "Heineken", 5.5f, "Pale Lager", "description Heineken",
				new Manufacturer(1, "Heineken Brewery", "Netherlands")));
		list.add(new Beer(2, "Estrella Damm", 4.7f, "Lager", "description Estrella",
				new Manufacturer(2, "Damm Brewery", "Spain")));
		list.add(new Beer(3, "Guinness", 8.9f, "Stout", "description Guiness",
				new Manufacturer(3, "Guinness Brewery", "Ireland")));
		list.add(new Beer(4, "Paulaner", 6.1f, "Witbier", "description Paulaner",
				new Manufacturer(4, " Paulaner brewery", "Germany")));
		return list;
	}
	
	@Bean
	public List<Manufacturer> getManufacturers() {
		List<Manufacturer> list = new ArrayList<Manufacturer>();
		list.add(new Manufacturer(1, "Heineken Brewery", "Netherlands"));
		list.add(new Manufacturer(2, "Damm Brewery", "Spain"));
		list.add(new Manufacturer(3, "Guinness Brewery", "Ireland"));
		list.add(new Manufacturer(4, "Paulaner brewery", "Germany"));
		list.add(new Manufacturer(5, "Brewdog", "Scotland"));
		return list;
	}

}
