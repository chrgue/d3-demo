package com.cgu.d3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.cgu.d3.domain.Component;
import com.cgu.d3.service.DependencyService;

import com.google.common.collect.Lists;

@Controller
public class D3Controller {
	
	@Autowired
	DependencyService service;
	
	@GetMapping("/")
	public ModelAndView getAll(){
		List<Component> components = service.getAllComponents();
		return createCommon(components);
	}
	
	@GetMapping("/{componentId}")
	public ModelAndView getByComponentId(@PathVariable String componentId){
		Component component = service.getComponentById(componentId);
		return createCommon(Lists.newArrayList(component));
	}
	
	private ModelAndView createCommon(List<Component> components){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("view");
		modelAndView.addObject("dependencies", components);
		return modelAndView;
	}
}
