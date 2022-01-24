package com.lupulus.cheers.repository.entity;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;

@Entity
@Table(name = "BEER")
@Data
public class BeerData {

	@Id
	@SequenceGenerator(name = "BEER_SEQ", sequenceName = "BEER_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotFound(action = NotFoundAction.IGNORE) 
    private Integer id;
	private String name;
	private float graduation;
	private String type;
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturerid")
	@NotFound(action = NotFoundAction.IGNORE) 
	private ManufacturerData manufacturer;
    
}
