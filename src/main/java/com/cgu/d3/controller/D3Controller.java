package com.cgu.d3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cgu.d3.domain.Dependency;
import com.cgu.d3.service.DependencyService;

@Controller
public class D3Controller {
	
	@Autowired
	DependencyService service;
	
	@GetMapping("/")
	public ModelAndView getAll(@RequestParam(value="refresh", required=false, defaultValue="false") boolean refresh){
		if(refresh){
			service.generate();
		}
		List<Dependency> dependencies = service.getAll();
		return createCommon(dependencies);
	}
	
	@GetMapping("/{componentId}")
	public ModelAndView getByComponentId(@PathVariable String componentId){
		List<Dependency> dependencies = service.getByName(componentId);
		return createCommon(dependencies);
	}
	
	private ModelAndView createCommon(List<Dependency> dependencies){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("view");
		modelAndView.addObject("dependencies", dependencies);
		return modelAndView;
	}
}
