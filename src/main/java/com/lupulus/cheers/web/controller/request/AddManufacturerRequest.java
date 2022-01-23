package com.lupulus.cheers.web.controller.request;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddManufacturerRequest {

	@NotEmpty
	private String name;
	@NotEmpty
	private String nationality;
}
