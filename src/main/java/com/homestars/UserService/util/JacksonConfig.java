package com.homestars.UserService.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author nchopra
 *
 */
@Configuration
public class JacksonConfig {

	/**
	 * Jackson convertor
	 *
	 * @return MappingJackson2HttpMessageConverter
	 */
	@Bean
	public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() throws Exception {
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		jackson2HttpMessageConverter.setObjectMapper(objectMapper());
		return jackson2HttpMessageConverter;
	}


	/**
	 * ObjectMapper
	 *
	 * @return ObjectMapper
	 */
	@Bean(name = "objectMapper")
	public ObjectMapper objectMapper() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);
		objectMapper.setSerializationInclusion(Include.NON_DEFAULT);
		return objectMapper;
	}
}
