package com.cgu.d3.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Service {
	
	@JsonProperty(required=false, value="depends_on")
	DependsOn dependsOn;
}
