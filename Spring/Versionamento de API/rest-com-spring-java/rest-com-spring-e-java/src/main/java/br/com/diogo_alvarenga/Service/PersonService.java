package br.com.diogo_alvarenga.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.diogo_alvarenga.Exception.ResourceNotFoundException;
import br.com.diogo_alvarenga.Model.Person;
import br.com.diogo_alvarenga.Repository.PersonRepository;
import br.com.diogo_alvarenga.data.dto.v1.PersonDTO;
import br.com.diogo_alvarenga.data.dto.v2.PersonDTOV2;
import br.com.diogo_alvarenga.mapper.custom.PersonMapper;

import static br.com.diogo_alvarenga.mapper.ObjectMapper.parseListObjects;
import static br.com.diogo_alvarenga.mapper.ObjectMapper.parseObject;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();

	@Autowired
	PersonRepository repository;
	@Autowired
	PersonMapper converter;
	
	//Logger será explicado mais tarde
	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PersonService.class.getName());
	
	
	public PersonDTO findById(Long id) {
		logger.info("Finding one Person!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		//convertendo para personDTO
		return parseObject(entity, PersonDTO.class);
	}
	
	
	
	public List<PersonDTO> findAll() {
		
		logger.info("Find all People!");
		
		//converte para personDTO
		return parseListObjects(repository.findAll(), PersonDTO.class);
	}
	
	

	public PersonDTO create(PersonDTO person) {
		logger.info("Create one Person!");
		
		//convertendo de DTO para person(entidade)
		var entity = parseObject(person, Person.class);
		
		//salva e converte para DTO
		return parseObject( repository.save(entity), PersonDTO.class);
	}
	
	public PersonDTOV2 createV2(PersonDTOV2 person) {
		logger.info("Create one Person!");
		
		//convertendo de DTO para person(entidade)
		var entity = converter.converDTOtoEntity(person);
		
		//salva e converte para DTO
		return converter.convertEntityToDTO( repository.save(entity));
	}
	
	
	
	public PersonDTO update(PersonDTO person) {
		logger.info("Updating one Person!");
			
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return parseObject( repository.save(entity), PersonDTO.class);
	}
	
	
	
	public void delete(Long id) {
		logger.info("Deleting one Person!");

		Person entity = repository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}
	
	
}
