package com.lemsst.ceburoomforrent.domain;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {
	
	@NotNull
	@Size(min=5, message="Size >5")
	private String name;
	
	@Size(min=1, message="Select at least one ingredient")
	private List<String> ingredients;
}
