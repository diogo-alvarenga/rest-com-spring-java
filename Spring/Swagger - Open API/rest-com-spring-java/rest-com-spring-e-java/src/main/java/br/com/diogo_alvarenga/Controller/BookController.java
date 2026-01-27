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
import org.springframework.web.bind.annotation.RestController;

import br.com.diogo_alvarenga.Service.BookService;
import br.com.diogo_alvarenga.data.dto.BookDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for Managing Book")
public class BookController {
	
	@Autowired
	private BookService service;
	
	//diz que pode retornar um json ou um xml
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, 
							 MediaType.APPLICATION_XML_VALUE,
							 MediaType.APPLICATION_YAML_VALUE})
	@Operation(
		    summary = "List all Book", //Um resumo curto do que o endpoint faz
		    description = "Find All Book", //Uma descrição mais detalhada.
		    tags = {"Book"}, //Agrupa esse endpoint na documentação sob a categoria People, ele usa um {} porque é um array de strings, porque podemos enviar mais de um
		    responses = { //a partir daqui é mostrado cada uma das possuiveis respostas da api
		        @ApiResponse(
		            description = "Success",
		            responseCode = "200",
		            content = { @Content( //content é o corpo da resposta que o endpoint retorna
		                mediaType = MediaType.APPLICATION_JSON_VALUE, //indica que o retorno será em formato json
		                array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)) //indica que o corpo é um array de objetos, o schema diz que cada item da lista é um PersonDTO
		            		)
		            })
		    }
		)
	public List<BookDTO> findAll() {
		return service.findAll();
	}

	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, 
											 MediaType.APPLICATION_XML_VALUE,
											 MediaType.APPLICATION_YAML_VALUE})
	@Operation(
		    summary = "Find a Book", //Um resumo curto do que o endpoint faz
		    description = "Find a specific Book by your id", //Uma descrição mais detalhada.
		    tags = {"Book"}, //Agrupa esse endpoint na documentação sob a categoria People, ele usa um {} porque é um array de strings, porque podemos enviar mais de um
		    responses = { //a partir daqui é mostrado cada uma das possuiveis respostas da api
		        @ApiResponse(
		            description = "Success",
		            responseCode = "200",
		            content =  @Content(schema = @Schema(implementation = BookDTO.class))
		            )
		    }
		)
	public BookDTO findById(@PathVariable("id") Long id) {
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
	@Operation(
		    summary = "Create a Book", //Um resumo curto do que o endpoint faz
		    description = "Create a Book", //Uma descrição mais detalhada.
		    tags = {"Book"} //Agrupa esse endpoint na documentação sob a categoria People, ele usa um {} porque é um array de strings, porque podemos enviar mais de um
		)
	public BookDTO create(@RequestBody BookDTO book) {//o parametro vem do body e nao do path
		return service.create(book);
	}
	
	
	@PutMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	@Operation(
		    summary = "Uptade a Book", //Um resumo curto do que o endpoint faz
		    description = "Update a specific Book", //Uma descrição mais detalhada.
		    tags = {"Book"} //Agrupa esse endpoint na documentação sob a categoria People, ele usa um {} porque é um array de strings, porque podemos enviar mais de um
		)
	public BookDTO update(@RequestBody BookDTO book) {
		return service.update(book);
	}
	
	
	@DeleteMapping(value= "/{id}")
	@Operation(
		    summary = "Delete a Book", //Um resumo curto do que o endpoint faz
		    description = "Delete a specific Book by your id", //Uma descrição mais detalhada.
		    tags = {"Book"} //Agrupa esse endpoint na documentação sob a categoria People, ele usa um {} porque é um array de strings, porque podemos enviar mais de um
		)
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
