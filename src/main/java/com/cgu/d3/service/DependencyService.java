package com.cgu.d3.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cgu.d3.domain.Component;
import com.cgu.d3.domain.Index;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


@Service
public class DependencyService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${metadata.url.index}")
	private String indexUrl;
	
	@Value("${metadata.url.meta}")
	private String metaUrl;
	
	@Value("${metadata.host}")
	private String host;
	
	@Value("${metadata.port}")
	private int port;
	
	private static ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());
	
	private String getUrlPrefix() {
		return "http://" + host + ":" + port;
	}
	
	public List<Component> getAllComponents(){
		ResponseEntity<Index> forEntity = restTemplate.getForEntity(getUrlPrefix() + indexUrl, Index.class);
		Index body = forEntity.getBody();
		
		return body.getPaths().stream()
				.map(this::getComponentById)
				.collect(Collectors.toList());
		
	}

	public Component getComponentById(String id) {
		ResponseEntity<String> meta = restTemplate.getForEntity(getUrlPrefix() + metaUrl + id, String.class);

		try {
			Component component = YAML_MAPPER.readValue(meta.getBody(), Component.class);
			return component;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
