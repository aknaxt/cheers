package com.lupulus.cheers.web.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lupulus.cheers.web.controller.request.AddBeerRequest;
import com.lupulus.cheers.web.controller.request.AddManufacturerRequest;
import com.lupulus.cheers.web.controller.request.UpdateBeerRequest;
import com.lupulus.cheers.web.controller.request.UpdateManufacturerRequest;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class CatalogControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	CatalogController catalogController;
	
	private String serialize(Object o)
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
		    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		    return ow.writeValueAsString(o);
		}
		catch(Exception ex)
		{
			log.error("Error deserializing object", ex);
		}
		return "";
	}
	
	@Test
	void contextLoads() {
		assertThat(catalogController).isNotNull();
	}	
	
	@Test
	public void getBeers_WithPaging0andSize2_ThenExpectCorrectPageSize() throws Exception {
		int actualPage = 0;
		int actualSize = 2;
		
		ResultActions result = this.mockMvc.perform(get(String.format("/public/v1/catalogue/beers?page=%s&size=%s&sort=", actualPage, actualSize))).andDo(print());
		
		int expectedNumber = 0;
		int expectedSize = 2;
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.content",hasSize(expectedSize)));
		result.andExpect(jsonPath("$.numberOfElements",is(expectedSize)));
		result.andExpect(jsonPath("$.number",is(expectedNumber)));
	}
	
	/**
	 * Test sorting by id descending
	 * @throws Exception
	 */
	@Test
	public void getBeers_WithSortingByIdDesc_ThenExpectCorrectSorting() throws Exception {
		int actualPage = 0;
		int actualSize = 3;
		
		ResultActions result = this.mockMvc.perform(get(String.format("/public/v1/catalogue/beers?page=%s&size=%s&sort=id,desc", actualPage, actualSize))).andDo(print());
				
		log.debug("sorting test: " + result.andReturn().getResponse().getContentAsString());		
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.content[0].id",not(1)));
	}
	
	/**
	 * Test sorting by id ascending
	 * @throws Exception
	 */
	@Test
	public void getBeers_WithSortingByIdAsc_ThenExpectCorrectSorting() throws Exception {
		int actualPage = 0;
		int actualSize = 3;
		
		ResultActions result = this.mockMvc.perform(get(String.format("/public/v1/catalogue/beers?page=%s&size=%s&sort=id,asc", actualPage, actualSize))).andDo(print());
				
		log.debug("sorting test: " + result.andReturn().getResponse().getContentAsString());		
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.content[0].id",is(1)));
	}

	
	/**
	 * get an empty page setting a big pagination
	 * @throws Exception
	 */
	@Test
	public void getBeers_WithPage100_ThenExpectEmptyPageSize() throws Exception {
		int actualPage = 100;
		int actualSize = 2;
		
		ResultActions result = this.mockMvc.perform(get(String.format("/public/v1/catalogue/beers?page=%s&size=%s&sort=", actualPage, actualSize))).andDo(print());
		
		int expectedNumberOfElements = 0;
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.content",hasSize(expectedNumberOfElements)));
		result.andExpect(jsonPath("$.numberOfElements",is(expectedNumberOfElements)));
	}
	
	/**
	 * get an existing beer
	 * @throws Exception
	 */
	@Test
	public void getBeer_WithExistingId_ThenExpectCorrectElement() throws Exception {
		int actualid = 1;
		
		ResultActions result = this.mockMvc.perform(get(String.format("/public/v1/catalogue/beer/%s", actualid))).andDo(print());
		
		int expectedId = 1;
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id", is(expectedId)));
	}
	
	
	/**
	 * try to get an uneexisting beer and get a 404
	 * @throws Exception
	 */
	@Test
	public void getBeer_WithUnexistingId_ThenExpect404() throws Exception {
		int actualid = 100;
		
		ResultActions result = this.mockMvc.perform(get(String.format("/public/v1/catalogue/beer/%s", actualid))).andDo(print());
				
		result.andExpect(status().isNotFound());
	}	
	
	/**
	 * add a beer and check the beer created is corrrect
	 * @throws Exception
	 */
	@Test
	public void addBeer_WithCorrectValues_ThenExpectBeerCreatedCorrectly() throws Exception {
		String actual_name  = "Punk IPA";
		Float actual_graduation = new Float(5.8);
	    String actual_type = "IPA";
	    String actual_description = "description Punk IPA";
	    int actual_manufacturer_id = 1;    
	    AddBeerRequest beer = new AddBeerRequest(actual_name, actual_graduation, actual_type, actual_description,actual_manufacturer_id);
	    
		ResultActions result = this.mockMvc.perform(
				post("/public/v1/catalogue/beer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(serialize(beer))
		).andDo(print());
				
		String expected_name  = actual_name;
		Float expected_graduation = actual_graduation;
	    String expected_type = actual_type;
	    String expected_description = actual_description;
	    int expected_manufacturer_id = actual_manufacturer_id;
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.name", is(expected_name)));
		//result.andExpect(jsonPath("$.graduation", is(actual_graduation))); //TODO: fix foat comparison
		result.andExpect(jsonPath("$.type", is(expected_type)));
		result.andExpect(jsonPath("$.description", is(expected_description)));
		result.andExpect(jsonPath("$.manufacturer.id", is(expected_manufacturer_id)));
	
	}	
	
	/**
	 * add a beer with empty name
	 * @throws Exception
	 */
	@Test
	public void addBeer_WithEmptyName_ThenExpect400() throws Exception {
		String actual_name  = "";
		Float actual_graduation = new Float(5.8);
	    String actual_type = "IPA";
	    String actual_description = "description Punk IPA";
	    int actual_manufacturer_id = 1;    
	    AddBeerRequest beer = new AddBeerRequest(actual_name, actual_graduation, actual_type, actual_description,actual_manufacturer_id);
	    
		ResultActions result = this.mockMvc.perform(
				post("/public/v1/catalogue/beer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(serialize(beer))
		).andDo(print());
				
		result.andExpect(status().isBadRequest());
	
	}
	
	
	/**
	 * modify a beer and check the beer is modified corrrectly
	 * @throws Exception
	 */
	@Test
	public void modifyBeer_WithCorrectValues_ThenExpectBeerModifiedCorrectly() throws Exception {
		int actual_id = 1;
		String actual_name  = "Punk IPA MODIFIED";
		Float actual_graduation = new Float(5.6);
	    String actual_type = "IPA MODIFIED";
	    String actual_description = "description Punk IPA MODIFIED";
	    int actual_manufacturer_id = 1;	    
	    UpdateBeerRequest beer = new UpdateBeerRequest(actual_id, actual_name, actual_graduation, actual_type, actual_description, actual_manufacturer_id);
	    
		ResultActions result = this.mockMvc.perform(
				put("/public/v1/catalogue/beer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(serialize(beer))
		).andDo(print());
				
		int expected_id = actual_id;
		String expected_name  = actual_name;
		Float expected_graduation = actual_graduation;
	    String expected_type = actual_type;
	    String expected_description = actual_description;
	    int expected_manufacturer_id = actual_manufacturer_id;
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id", is(expected_id)));
		result.andExpect(jsonPath("$.name", is(expected_name)));
		//result.andExpect(jsonPath("$.graduation", is(actual_graduation))); //TODO: fix foat comparison
		result.andExpect(jsonPath("$.type", is(expected_type)));
		result.andExpect(jsonPath("$.description", is(expected_description)));
		result.andExpect(jsonPath("$.manufacturer.id", is(expected_manufacturer_id)));
	
	}
	
	/**
	 * modify a beer with empty name and expect 400
	 * @throws Exception
	 */
	@Test
	public void modifyBeer_WithEmptyName_ThenExpect400() throws Exception {
		int actual_id = 1;
		String actual_name  = "";
		Float actual_graduation = new Float(5.6);
	    String actual_type = "IPA MODIFIED";
	    String actual_description = "description Punk IPA MODIFIED";
	    int actual_manufacturer_id = 1;	    
	    UpdateBeerRequest beer = new UpdateBeerRequest(actual_id, actual_name, actual_graduation, actual_type, actual_description, actual_manufacturer_id);
	    
		ResultActions result = this.mockMvc.perform(
				put("/public/v1/catalogue/beer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(serialize(beer))
		).andDo(print());
				
		
		result.andExpect(status().isBadRequest());	
	}
	
	/**
	 * modify a beer that does not exist, and receive a 404
	 * @throws Exception
	 */
	@Test
	public void modifyBeer_WithNonExistingId_ThenExpect404() throws Exception {
		int actual_id = 100;
		String actual_name  = "Punk IPA MODIFIED";
		Float actual_graduation = new Float(5.6);
	    String actual_type = "IPA MODIFIED";
	    String actual_description = "description Punk IPA MODIFIED";
	    int actual_manufacturer_id = 1;	    
	    UpdateBeerRequest beer = new UpdateBeerRequest(actual_id, actual_name, actual_graduation, actual_type, actual_description, actual_manufacturer_id);
	    
		ResultActions result = this.mockMvc.perform(
				put("/public/v1/catalogue/beer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(serialize(beer))
		).andDo(print());
				
		
		result.andExpect(status().isNotFound());
	
	}


	/**
	 * delete a existing beer 
	 * @throws Exception
	 */
	@Test
	public void deleteBeer_WithExistingId_ThenExpectBeerDeletedCorrectly() throws Exception {
		int actual_id = 2;
		
		ResultActions result = this.mockMvc.perform(
				delete(String.format("/public/v1/catalogue/beer?id=%s", actual_id))
		).andDo(print());
		
		result.andExpect(status().isOk());	
	}
	

	/*
	 * MANUFACTURERS
	 */
	
	/**
	 * test manufacturers pagination
	 */
	@Test
	public void getManufacturers_WithPaging0andSize2_ThenExpectCorrectPageSize() throws Exception {
		int actualPage = 0;
		int actualSize = 2;
		
		ResultActions result = this.mockMvc.perform(get(String.format("/public/v1/catalogue/manufacturers?page=%s&size=%s&sort=", actualPage, actualSize))).andDo(print());
		
		int expectedNumber = 0;
		int expectedSize = 2;
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.content",hasSize(expectedSize)));
		result.andExpect(jsonPath("$.numberOfElements",is(expectedSize)));
		result.andExpect(jsonPath("$.number",is(expectedNumber)));
	}	
	
	/**
	 * get an empty page setting a big pagination
	 * @throws Exception
	 */
	@Test
	public void getManufacurers_WithPage100_ThenExpectEmptyPageSize() throws Exception {
		int actualPage = 100;
		int actualSize = 2;
		
		ResultActions result = this.mockMvc.perform(get(String.format("/public/v1/catalogue/manufacturers?page=%s&size=%s&sort=", actualPage, actualSize))).andDo(print());
		
		int expectedNumberOfElements = 0;
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.content",hasSize(expectedNumberOfElements)));
		result.andExpect(jsonPath("$.numberOfElements",is(expectedNumberOfElements)));
	}
	
	/**
	 * Test sorting by id descending
	 * @throws Exception
	 */
	@Test
	public void getManufacturers_WithSortingByIdDesc_ThenExpectCorrectSorting() throws Exception {
		int actualPage = 0;
		int actualSize = 3;
		
		ResultActions result = this.mockMvc.perform(get(String.format("/public/v1/catalogue/manufacturers?page=%s&size=%s&sort=id,desc", actualPage, actualSize))).andDo(print());
				
		log.debug("sorting test: " + result.andReturn().getResponse().getContentAsString());		
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.content[0].id",not(1)));
	}
	
	/**
	 * Test sorting by id ascending
	 * @throws Exception
	 */
	@Test
	public void getManufacturers_WithSortingByIdAsc_ThenExpectCorrectSorting() throws Exception {
		int actualPage = 0;
		int actualSize = 3;
		
		ResultActions result = this.mockMvc.perform(get(String.format("/public/v1/catalogue/manufacturers?page=%s&size=%s&sort=id,asc", actualPage, actualSize))).andDo(print());
				
		log.debug("sorting test: " + result.andReturn().getResponse().getContentAsString());		
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.content[0].id",is(1)));
	}
	
	/**
	 * get an existing manufacturers
	 * @throws Exception
	 */
	@Test
	public void getManufacturer_WithExistingId_ThenExpectCorrectElement() throws Exception {
		int actualid = 1;
		
		ResultActions result = this.mockMvc.perform(get(String.format("/public/v1/catalogue/manufacturer/%s", actualid))).andDo(print());
		
		int expectedId = 1;
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id", is(expectedId)));
	}
	
	
	/**
	 * try to get an unexisting manufacturer and get a 404
	 * @throws Exception
	 */
	@Test
	public void getManufacturer_WithUnexistingId_ThenExpect404() throws Exception {
		int actualid = 100;
		
		ResultActions result = this.mockMvc.perform(get(String.format("/public/v1/catalogue/manufacturer/%s", actualid))).andDo(print());
				
		result.andExpect(status().isNotFound());
	}	
	
	/**
	 * add a manufacturer and check the manufacturer created is correct
	 * @throws Exception
	 */
	@Test
	public void addManufacturer_WithCorrectValues_ThenExpectManufacturerCreatedCorrectly() throws Exception {
		String actual_name  = "Brewdow Brewery";
	    String actual_nationality = "Scotland";
	    AddManufacturerRequest manufacturer = new AddManufacturerRequest(actual_name, actual_nationality);
	    
		ResultActions result = this.mockMvc.perform(
				post("/public/v1/catalogue/manufacturer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(serialize(manufacturer))
		).andDo(print());
				
		String expected_name  = "Brewdow Brewery";
	    String expected_nationality = "Scotland";
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.name", is(expected_name)));
		result.andExpect(jsonPath("$.nationality", is(expected_nationality)));
	
	}	
	
	/**
	 * add a manufacturer with empty name
	 * @throws Exception
	 */
	@Test
	public void addManufacturer_WithEmptyName_ThenExpect400() throws Exception {
		String actual_name  = "";
	    String actual_nationality = "Scotland";
	    AddManufacturerRequest manufacturer = new AddManufacturerRequest(actual_name, actual_nationality);
	    
		ResultActions result = this.mockMvc.perform(
				post("/public/v1/catalogue/manufacturer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(serialize(manufacturer))
		).andDo(print());
				
		result.andExpect(status().isBadRequest());	
	}
	
	
	/**
	 * modify a manufacturer and check the manufacturer is modified corrrectly
	 * @throws Exception
	 */
	@Test
	public void modifyManufacturer_WithCorrectValues_ThenExpectManufacturerModifiedCorrectly() throws Exception {
		int actual_id = 1;
		String actual_name  = "Brewdow Brewery UPDATED";
	    String actual_nationality = "Scotland UPDATED"; 
	    UpdateManufacturerRequest manufacturer = new UpdateManufacturerRequest(actual_id, actual_name, actual_nationality);
	    
		ResultActions result = this.mockMvc.perform(
				put("/public/v1/catalogue/manufacturer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(serialize(manufacturer))
		).andDo(print());
				
		int expected_id = actual_id;
		String expected_name  = actual_name;
		String expected_nationality = actual_nationality; 
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id", is(expected_id)));
		result.andExpect(jsonPath("$.name", is(expected_name)));
		result.andExpect(jsonPath("$.nationality", is(expected_nationality)));
	
	}
	
	/**
	 * modify a manufacturer with empty name and expect 400
	 * @throws Exception
	 */
	@Test
	public void modifyManufacturer_WithEmptyName_ThenExpect400() throws Exception {
		int actual_id = 1;
		String actual_name  = "";
	    String actual_nationality = "Scotland UPDATED";
	    UpdateManufacturerRequest manufacturer = new UpdateManufacturerRequest(actual_id, actual_name, actual_nationality);
	    
		ResultActions result = this.mockMvc.perform(
				put("/public/v1/catalogue/manufacturer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(serialize(manufacturer))
		).andDo(print());				
		
		result.andExpect(status().isBadRequest());	
	}
	
	/**
	 * modify a manufacturer that does not exist, and receive a 404
	 * @throws Exception
	 */
	@Test
	public void modifyManufacturer_WithNonExistingId_ThenExpect404() throws Exception {
		int actual_id = 100;
		String actual_name  = "Brewdow Brewery UPDATED";
	    String actual_nationality = "Scotland UPDATED"; 
	    UpdateManufacturerRequest manufacturer = new UpdateManufacturerRequest(actual_id, actual_name, actual_nationality);
	    
		ResultActions result = this.mockMvc.perform(
				put("/public/v1/catalogue/manufacturer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(serialize(manufacturer))
		).andDo(print());
				
		
		result.andExpect(status().isNotFound());
	
	}


	/**
	 * delete a existing manufacturer 
	 * @throws Exception
	 */
	@Test
	public void deleteManufacturer_WithExistingId_ThenExpectManufacturerDeletedCorrectly() throws Exception {
		int actual_id = 2;
		
		ResultActions result = this.mockMvc.perform(
				delete(String.format("/public/v1/catalogue/manufacturer?id=%s", actual_id))
		).andDo(print());
		
		result.andExpect(status().isOk());	
	}	
	
}
