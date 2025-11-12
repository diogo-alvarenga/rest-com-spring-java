package br.com.diogo_alvarenga.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//diz para o spring que essa classe é uma configuração e pode conter beans
//assim, ele precisa carregar as configurações da classe
@Configuration 
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		//Nao funciona, Deprecated
		//Via EXTENSION. http://localhost:8080/api/person/v1/2.xml or  http://localhost:8080/api/person/v1/2.xml.JSON Deprecated on Spring Boot 2.6
		
		//Funciona
		//Via QUERY PARAM http://localhost:8080/api/person/v1/2?mediaType=xml  *necessita colocar os parametros no final:?mediaType=xml
		/*configurer.favorParameter(true)
			.parameterName("mediaType")//posso trocar de nome, ai troco na url acima tambem
			.ignoreAcceptHeader(true) //funciona por padrão, então ignoro ele (é um parametro do header)
			.useRegisteredExtensionsOnly(false)//false, pois irei definir manualmente os tipos suportados
			.defaultContentType(MediaType.APPLICATION_JSON)//Se o cliente não especificar o tipo de conteúdo desejado, a resposta será retornada em JSON por padrão
			.mediaType("json", MediaType.APPLICATION_JSON)//nessa e na linha abaixo, ele diz que suporta json e xml
			.mediaType("xml", MediaType.APPLICATION_XML);
			*/
		
		
		//Via HEADER PARAM http://localhost:8080/api/person/v1/2
		//Vá nos headers do Postman e coloque Accept na Key, e no value coloque application/xml,  application/json ou application/yaml
		//para entender o yaml, vá na classe YamlJackson2HttppMessageConverter que está bem comentada
		configurer.favorParameter(false)
			.ignoreAcceptHeader(false) //funciona por padrão, então ignoro ele (é um parametro do header)
			.useRegisteredExtensionsOnly(false)//false, pois irei definir manualmente os tipos suportados
			.defaultContentType(MediaType.APPLICATION_JSON)//Se o cliente não especificar o tipo de conteúdo desejado, a resposta será retornada em JSON por padrão
			.mediaType("json", MediaType.APPLICATION_JSON)//nessa e nas linhas abaixo, ele diz que suporta json, xml e yaml
			.mediaType("xml", MediaType.APPLICATION_XML)
			.mediaType("yaml", MediaType.APPLICATION_YAML);
	}
}
