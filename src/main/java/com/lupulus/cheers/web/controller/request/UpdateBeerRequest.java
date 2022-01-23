package com.lupulus.cheers.web.controller.request;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBeerRequest {

	@NotNull
	int id;
	@NotEmpty
	private String name;
	@DecimalMin("0.0")
	private float graduation;
	private String type;
	private String description;
	@NotNull
	private int manufacturerId;
	
}
