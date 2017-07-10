package com.cgu.d3.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Dependecy {
	String source;
	String target;
	String type;
}
