package com.cgu.d3.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DependsOn {
	
	@JsonProperty(required=false)
	List<String> internal;
	@JsonProperty(required=false)
	List<String> external;
}
