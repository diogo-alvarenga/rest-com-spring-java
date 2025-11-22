Como integrar o Swagger a aplicação



>Swagger é uma das ferramentas mais importantes para APIs RESTful



>Swagger facilita o processo de definir, criar, documentar, e consumir serviços RESTful



>>Esse padrão descreve endpoints, dados enviados e recebidos, status codes e métodos de autenticação de forma unificada 



>Swagger permite testar endpoints, enviar requisições e ver respostas em tempo real



>O coração do Swagger é um arquivo JSON ou YAML que define toda a API









> Coloque a dependencia no pom.xml



&nbsp;		<dependency>

&nbsp;		    <groupId>org.springdoc</groupId>

&nbsp;		    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>

&nbsp;		    <version>${springdoc.version}</version>

&nbsp;		</dependency>



> Agora dentro da tag <properties> coloque essa tag-variavel, contendo a versão, que é mencionada na dependencia acima



&nbsp;		<springdoc.version>3.0.0</springdoc.version>





**> Crie a classe onde irá passar as informações da sua api ao Swagger**





@Configuration

public class OpenApiConfig {

&nbsp;	

&nbsp;	@Bean 

&nbsp;	/\*

&nbsp;	 \* Bean é um objeto instanciado que é montado e gerenciado pelo spring

&nbsp;	 \*

&nbsp;	 \* Essa classe é necessaria para que o Swagger tenha as informações da API, como nome e etc.

&nbsp;	 \* 

&nbsp;	 \* Explicação do metodo abaixo:

&nbsp;	 \* 1 - Crio a instancia de um objeto OpenAPI

&nbsp;	 \* 2 - Atraves desse objeto, acesso o metodo info()

&nbsp;	 \* 3 - Crio a instancia de um objeto da classe Info 

&nbsp;	 \* 4 - Atraves desse objeto da classe Info acesso metodos que passo informaçoes

&nbsp;	 \* 5 - Essas informações sao alocadas dentro desse objeto da classe Info

&nbsp;	 \* 6 - Esse objeto da classe Info é depositado dentro do metodo info()

&nbsp;	 \* 7 - Esse metodo customOPenAPI retorna o objeto OpenAPI que possui os dados de Info dentro

&nbsp;	 \* \*/

&nbsp;	

&nbsp;	OpenAPI customOPenAPI() {

&nbsp;		return new OpenAPI()

&nbsp;				.info(new Info()

&nbsp;						.title("REST API'S RESTful from 0 with Java, Spring Boot, Kubernetes and Docker")

&nbsp;							.version("V1")

&nbsp;								.description("REST API'S RESTful from 0 with Java, Spring Boot, Kubernetes and Docker")

&nbsp;									.termsOfService("https://store.steampowered.com/?l=portuguese")

&nbsp;										.license(new License()

&nbsp;												.name("Apache 2.0")

&nbsp;												.url("https://store.steampowered.com/?l=portuguese")

&nbsp;										)

&nbsp;				);

&nbsp;	}

}



> Essas informações serão retornadas em Json se voce acessar http://localhost:8080/v3/api-docs

> Sobre a linha acima, o v3 não é relacionado ao versionamento da sua api, mas sim do Swagger, **você não programa isso**

> Ou voce pode acessar por http://localhost:8080/swagger-ui/index.html

> As informações estarão lá, e voce poderá testar sua api atraves de uma interface, semelhante ao Postman

