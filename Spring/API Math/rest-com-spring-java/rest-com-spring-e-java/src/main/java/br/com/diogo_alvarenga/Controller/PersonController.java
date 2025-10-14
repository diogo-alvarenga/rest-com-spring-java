package br.com.diogo_alvarenga.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.diogo_alvarenga.Model.Person;
import br.com.diogo_alvarenga.Service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findById() {
		return service.findAll();
	}

	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(@PathVariable("id") String id) {
		return service.findById(id);
	}
	
	
	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,//irá consumir
			produces = MediaType.APPLICATION_JSON_VALUE//irá produzir
			)
	public Person create (@RequestBody Person person) {//o parametro vem do body e nao do path
		return service.create(person);
	}
	
	@RequestMapping(
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public Person updatePerson(@RequestBody Person person) {
		return service.update(person);
	}
	
	
	@RequestMapping(
			value= "/{id}",
			method = RequestMethod.DELETE
			)
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}

}

