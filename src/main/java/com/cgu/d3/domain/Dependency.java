package com.cgu.d3.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Dependency {
	String source;
	String target;
	String type;
}
