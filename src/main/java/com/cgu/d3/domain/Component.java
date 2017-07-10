package com.cgu.d3.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Builder
@Data
public class Component {
	String id;
	
	@Singular("addDependency")
	List<String> dependsOn;
}
