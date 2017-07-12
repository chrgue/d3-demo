package com.cgu.d3.config;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import com.github.tomakehurst.wiremock.WireMockServer;

@Configuration
public class WireMockConfiguration {
	
	@Value("${metadata.url.index}")
	private String indexUrl;
	
	@Value("${metadata.url.meta}")
	private String metaUrl;
	
	@Bean
	public WireMockServer mockServer(){
		WireMockServer mockServer = new WireMockServer(options().withRootDirectory("src/main/resources").port(1234));
		
		// mock three component meta data
		subMetadata(mockServer, "component1");
		subMetadata(mockServer, "component2");
		subMetadata(mockServer, "component3");
		subMetadata(mockServer, "component4");
		
		mockServer.stubFor(
				get(urlPathEqualTo(indexUrl)).willReturn(aResponse().withBodyFile("all.json")
						.withHeader("Content-Type", "application/json").withStatus(HttpStatus.OK.value())));
		
		mockServer.start();
		
		return mockServer;
	}

	private void subMetadata(WireMockServer mockServer, String componentId) {
		mockServer.stubFor(get(urlPathEqualTo(metaUrl + componentId))
				.willReturn(aResponse()
						.withBodyFile(componentId + ".yml")
						.withHeader("Content-Type", "application/json")
						.withStatus(HttpStatus.OK.value())));
	}
}
