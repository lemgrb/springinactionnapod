package com.lemsst.ceburoomforrent.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lemsst.ceburoomforrent.domain.Ingredient;
import com.lemsst.ceburoomforrent.domain.Ingredient.Type;
import com.lemsst.ceburoomforrent.domain.Taco;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

	@GetMapping
	public String showDesignForm(Model model) {
		
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("0", "Flour",Type.WRAP),
				new Ingredient("1", "Rice",Type.WRAP),
				new Ingredient("2", "Fish",Type.PROTEIN),
				new Ingredient("3", "Chicken",Type.PROTEIN));
		
		Type[] types = Ingredient.Type.values();
		
		for (Type type: types) {
			List<Ingredient> filteredIngredients = ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
			model.addAttribute(type.toString().toUpperCase(), filteredIngredients);
		}
		
		model.addAttribute("design", new Taco());
		
		return "design";
		
	}
	
	@PostMapping
	public String processDesign(@Valid Taco design, Errors errors) {
		if(errors.hasErrors())
			return "design";
		log.info("Design: " + design);
		return "redirect:/orders/current";
	}
}
