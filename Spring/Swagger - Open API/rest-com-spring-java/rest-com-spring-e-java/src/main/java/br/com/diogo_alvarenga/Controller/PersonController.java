package br.com.diogo_alvarenga.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.diogo_alvarenga.Service.PersonService;
import br.com.diogo_alvarenga.data.dto.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;


@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	//diz que pode retornar um json ou um xml
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, 
								MediaType.APPLICATION_XML_VALUE,
								MediaType.APPLICATION_YAML_VALUE})
	@Operation(
		    summary = "", //Um resumo curto do que o endpoint faz
		    description = "Find All People", //Uma descrição mais detalhada.
		    tags = {"People"}, //Agrupa esse endpoint na documentação sob a categoria People, ele usa um {} porque é um array de strings, porque podemos enviar mais de um
		    responses = { //a partir daqui é mostrado cada uma das possuiveis respostas da api
		        @ApiResponse(
		            description = "Success",
		            responseCode = "200",
		            content = @Content( //content é o corpo da resposta que o endpoint retorna
		                mediaType = MediaType.APPLICATION_JSON_VALUE, //indica que o retorno será em formato json
		                array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class)) //indica que o corpo é um array de objetos, o schema diz que cada item da lista é um PersonDTO
		            )
		        ),
		        @ApiResponse(description = "No Content", responseCode = "204", content = @Content()),
		        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
		        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
		        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
		        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content())
		    }
		)
	public List<PersonDTO> findById() {
		return service.findAll();
	}

	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, 
												MediaType.APPLICATION_XML_VALUE,
												MediaType.APPLICATION_YAML_VALUE})
	public PersonDTO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	
	@PostMapping(
			consumes = { MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE,
							MediaType.APPLICATION_YAML_VALUE},//irá consumir
			produces = { MediaType.APPLICATION_JSON_VALUE, 
							MediaType.APPLICATION_XML_VALUE,
							MediaType.APPLICATION_YAML_VALUE}//irá produzir
			)
	public PersonDTO create (@RequestBody PersonDTO person) {//o parametro vem do body e nao do path
		return service.create(person);
	}
	
	@PutMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public PersonDTO updatePerson(@RequestBody PersonDTO person) {
		return service.update(person);
	}
	
	
	@DeleteMapping(value= "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}

