package com.lupulus.cheers.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer {

	@Min(0)
	private int id;
	@NotEmpty
	private String name;
	private String nationality;
}
