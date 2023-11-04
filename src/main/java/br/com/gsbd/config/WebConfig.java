package br.com.gsbd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		https://www.baeldung.com/spring-mvc-content-negotiation-json-xml
		
////		VIA QUERY PARAM  ("?mediaType=xml")
//		configurer.favorParameter(true)
//			.parameterName("mediaType")
//			.ignoreAcceptHeader(true)
//			.useRegisteredExtensionsOnly(false)
//			.defaultContentType(MediaType.APPLICATION_JSON)
//				.mediaType("json", MediaType.APPLICATION_JSON)
//				.mediaType("xml", MediaType.APPLICATION_XML);
//	}
		
//	VIA HEADER KEY  ("?mediaType=xml")

		configurer.favorParameter(false)
		.ignoreAcceptHeader(false)
		.useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON)
			.mediaType("json", MediaType.APPLICATION_JSON)
			.mediaType("xml", MediaType.APPLICATION_XML);
	}

}
