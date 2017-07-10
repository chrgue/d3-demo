package com.cgu.d3.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.cgu.d3.domain.Component;
import com.cgu.d3.domain.Component.ComponentBuilder;
import com.cgu.d3.domain.Dependecy;


@Service
public class DependencyService {
	
	private static final String COMPONENT_PREFIX = "component";

	private static final int MAX_DEPENDENCIES = 5;

	private static final int DEFAULT_COMPONENT_COUNT = 20;
	
	private Map<String,Component> fakeRepository;
	
	public List<Dependecy> getAll(){
		return fakeRepository.values().stream()
			.map(this::generateComponentDependencies)
			.flatMap(l -> l.stream())
			.collect(Collectors.toList());
	}
	
	public List<Dependecy> getByName(String name){
		Component component = fakeRepository.get(name);
		return generateComponentDependencies(component);
	}

	private List<Dependecy> generateComponentDependencies(Component component) {
		return component.getDependsOn().stream()
			.map(dep -> {
				Dependecy dependecy = Dependecy.builder()
				.source(component.getId())
				.target(dep)
				.type("suit")
				.build();
				return dependecy;
				
			}).collect(Collectors.toList());
	}

	public void generate() {
		generate(DEFAULT_COMPONENT_COUNT);
	}
	
	public void generate(int count){
		fakeRepository = IntStream.range(1, count)
				.boxed()
				.map(value -> {
					ComponentBuilder builder = Component.builder()
							.id(COMPONENT_PREFIX + value);
					
					// create some dependencies
					IntStream.range(1,Math.toIntExact(Math.round(Math.random() * MAX_DEPENDENCIES)))
						.forEach(a -> {
							builder.addDependency(COMPONENT_PREFIX + Math.round(Math.random() * count));
						});
					
					return builder.build();
				}).collect(Collectors.toMap(Component::getId, Function.identity()));
	}
}
