package com.lupulus.cheers.web.controller.request;

import javax.validation.constraints.NotEmpty;


import lombok.Data;

@Data
public class AddManufacturerRequest {

	@NotEmpty
	private String name;
	private String nationality;
}
