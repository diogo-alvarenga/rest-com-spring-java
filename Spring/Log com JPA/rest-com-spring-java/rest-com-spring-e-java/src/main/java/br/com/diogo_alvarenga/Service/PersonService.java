package br.com.diogo_alvarenga.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diogo_alvarenga.Exception.ResourceNotFoundException;
import br.com.diogo_alvarenga.Model.Person;
import br.com.diogo_alvarenga.Repository.PersonRepository;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();

	@Autowired
	PersonRepository repository;
	
	//Logger será explicado mais tarde
	private Logger logger = Logger.getLogger(PersonService.class.getName());

	
	
	public Person findById(Long id) {
		logger.info("Finding one Person!");
		
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

	}
	
	
	
	public List<Person> findAll() {
		
		logger.info("Find all People!");

		return repository.findAll();
	}
	
	

	public Person create(Person person) {
		logger.info("Create one Person!");
		
		return repository.save(person);
	}
	
	
	
	public Person update(Person person) {
		logger.info("Updating one Person!");
			
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return repository.save(entity);
	}
	
	
	
	public void delete(Long id) {
		logger.info("Deleting one Person!");

		Person entity = repository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}
	
	
}
