package com.lupulus.cheers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.lupulus.cheers.web.controller.CatalogController;


@SpringBootTest
@AutoConfigureMockMvc
class CheersApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	CatalogController catalogController;
	
	@Test
	void contextLoads() {
		assertThat(catalogController).isNotNull();
	}
	
	@Test
	public void getBeers_OK() throws Exception {
		this.mockMvc.perform(get("/public/v1/catalogue/beers?page=0&size=2&sort=")).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.content",hasSize(2)));
	}

}
