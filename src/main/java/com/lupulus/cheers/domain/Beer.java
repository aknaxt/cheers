package com.lupulus.cheers.domain;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beer {

	@Min(0)
	private int id;
	@NotEmpty
	private String name;
	@DecimalMin("0.0")
	private float graduation;
	private String type;
	private String description;
	@NotNull
	private Manufacturer manufacturer;
}
