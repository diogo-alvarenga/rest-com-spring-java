package br.com.diogo_alvarenga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean 
	/*
	 * Bean é um objeto instanciado que é montado e gerenciado pelo spring
	 *
	 * Essa classe é necessaria para que o Swagger tenha as informações da API, como nome e etc.
	 * 
	 * Explicação do metodo abaixo:
	 * 1 - Crio a instancia de um objeto OpenAPI
	 * 2 - Atraves desse objeto, acesso o metodo info()
	 * 3 - Crio a instancia de um objeto da classe Info 
	 * 4 - Atraves desse objeto da classe Info acesso metodos que passo informaçoes
	 * 5 - Essas informações sao alocadas dentro desse objeto da classe Info
	 * 6 - Esse objeto da classe Info é depositado dentro do metodo info()
	 * 7 - Esse metodo customOPenAPI retorna o objeto OpenAPI que possui os dados de Info dentro
	 * */
	
	OpenAPI customOPenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("REST API'S RESTful from 0 with Java, Spring Boot, Kubernetes and Docker")
							.version("V1")
								.description("REST API'S RESTful from 0 with Java, Spring Boot, Kubernetes and Docker")
									.termsOfService("https://store.steampowered.com/?l=portuguese")
										.license(new License()
												.name("Apache 2.0")
												.url("https://store.steampowered.com/?l=portuguese")
										)
				);
	}
}
