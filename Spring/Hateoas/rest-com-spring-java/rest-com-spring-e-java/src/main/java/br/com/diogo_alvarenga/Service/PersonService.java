package br.com.diogo_alvarenga.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diogo_alvarenga.Controller.PersonController;
import br.com.diogo_alvarenga.Exception.ResourceNotFoundException;
import br.com.diogo_alvarenga.Model.Person;
import br.com.diogo_alvarenga.Repository.PersonRepository;
import br.com.diogo_alvarenga.data.dto.PersonDTO;
import static br.com.diogo_alvarenga.mapper.ObjectMapper.parseListObjects;
import static br.com.diogo_alvarenga.mapper.ObjectMapper.parseObject;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



@Service
public class PersonService {

	@Autowired
	PersonRepository repository;
	
	//Logger será explicado mais tarde
	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PersonService.class.getName());
	
	
	public PersonDTO findById(Long id) {
		logger.info("Finding one Person!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		//convertendo para personDTO
		var dto = parseObject(entity, PersonDTO.class);
		addHateoasLinks(dto);
		return dto;
	}
	
	
	
	public List<PersonDTO> findAll() {
		
		logger.info("Find all People!");
		
		//converte para personDTO
		var persons=  parseListObjects(repository.findAll(), PersonDTO.class);
		persons.forEach(this:: addHateoasLinks);//para cada objeto que vier :: mande para esse metodo
		return persons;
	
	}
	
	

	public PersonDTO create(PersonDTO person) {
		logger.info("Create one Person!");
		
		//convertendo de DTO para person(entidade)
		var entity = parseObject(person, Person.class);
		
		//salva e converte para DTO
		var dto = parseObject( repository.save(entity), PersonDTO.class);
		addHateoasLinks(dto);
		return dto;
	}
	
	
	
	public PersonDTO update(PersonDTO person) {
		logger.info("Updating one Person!");
			
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var dto = parseObject( repository.save(entity), PersonDTO.class);
		addHateoasLinks(dto);
		return dto;
	}
	
	
	
	public void delete(Long id) {
		logger.info("Deleting one Person!");

		Person entity = repository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}
	
	private void addHateoasLinks(PersonDTO dto) {
		dto.add(linkTo(methodOn(PersonController.class)//diz que o objeto terá um link para ummetodo DE PersonController
				.findById(dto.getId()))// qual metodo? findById
				.withSelfRel()//diz o papel do link, e esse metodo diz que seria o link principal(autoLink)
				.withType("GET"));//diz que o tipo do link é um GET
		
		dto.add(linkTo(methodOn(PersonController.class)
				.delete(dto.getId()))
				.withRel("delete")
				.withType("DELETE"));
		
		dto.add(linkTo(methodOn(PersonController.class)
				.findById())
				.withRel("findById")
				.withType("GET"));
		
		dto.add(linkTo(methodOn(PersonController.class)
				.create(dto))
				.withRel("create")
				.withType("POST"));
		
		dto.add(linkTo(methodOn(PersonController.class)
				.updatePerson(dto))
				.withRel("update")
				.withType("PUT"));
		}
}
