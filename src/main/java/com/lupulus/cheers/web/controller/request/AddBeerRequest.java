package com.lupulus.cheers.web.controller.request;

import com.lupulus.cheers.domain.Manufacturer;

import javax.validation.constraints.*;

import lombok.Data;

@Data
public class AddBeerRequest {

	@NotEmpty
	private String name;
	@Min(0)
	private float graduation;
	private String type;
	private String description;
	@NotNull
	private Manufacturer manufacturer;
	
}
