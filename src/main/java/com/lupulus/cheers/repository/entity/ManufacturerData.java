package com.lupulus.cheers.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;

@Entity
@Table(name = "MANUFACTURER")
@Data
public class ManufacturerData {

	@Id
	@SequenceGenerator(name = "ODS_SEQ", sequenceName = "ODS_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotFound(action = NotFoundAction.IGNORE) 
    private Integer id;
    private String name;
    private String nationality;
}
