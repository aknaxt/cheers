package com.lupulus.cheers.web.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateManufacturerRequest {

	@NotNull
	int id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String nationality;
}
