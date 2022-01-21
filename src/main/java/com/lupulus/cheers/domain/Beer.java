package com.lupulus.cheers.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beer {

	private int id;
	private String name;
	private double graduation;
	private String manufacturer;
	private String type;
	private String description;
}
