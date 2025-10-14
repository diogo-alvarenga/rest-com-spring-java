package br.com.diogo_alvarenga.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diogo_alvarenga.Model.Person;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();

	
	//Logger será explicado mais tarde
	private Logger logger = Logger.getLogger(PersonService.class.getName());

	public Person findById(String id) {
		logger.info("Finding one Person!");
		
		Person person = new Person();
		person.setId(counter.incrementAndGet());//vai simular um acesso ao banco
		person.setFirstName("Arthur");
		person.setLastName("Alves");
		person.setAddress("Sao Paulo - Brasil");
		person.setGender("Male");
		return person;

	}
	
	public List<Person> findAll() {
		logger.info("Find all People!");
		List<Person> persons = new ArrayList<Person>();
		
		for(int i =0;i<9;i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		
		return persons;
	}

	public Person create(Person person) {
		logger.info("Create one Person!");
		
		return person;
	}
	
	public Person update(Person person) {
		logger.info("Updating one Person!");
			
		return person;
	}
	
	public void delete(String id) {
		logger.info("Deleting one Person!");

	}
	
	
	private Person mockPerson(int i) {
		
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Firstname "+i);
		person.setLastName("Lastname "+i);
		person.setAddress("Some Address in Brasil");
		person.setGender("Male");
		
		return person;
	}
}
