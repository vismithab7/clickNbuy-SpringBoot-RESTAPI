package com.clickNbuy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Category {
//	@Id
	@GeneratedValue(generator = "cat_id")
//	@SequenceGenerator(name = "cat_id", initialValue = 1000, allocationSize = 1)
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

}
