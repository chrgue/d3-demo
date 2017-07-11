package com.cgu.d3.domain;

import java.util.List;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;


@Builder
@Value
public class Component {
	String id;
	
	@Singular("addDependency")
	List<String> dependsOn;
}
