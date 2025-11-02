package br.com.diogo_alvarenga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;


@Configuration
public class ObjectMapperConfig {
	
	//Será chamado no DTO, ele faz nao aparecer o sensitiveData no JSON
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		
		SimpleFilterProvider filter = new SimpleFilterProvider()
				.addFilter("PersonFilter", //passarei esse parametro no DTO
						SimpleBeanPropertyFilter.serializeAllExcept("sensitiveData"));
			mapper.setFilterProvider(filter);
		return mapper;
	}
}
