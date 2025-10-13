package br.com.diogo_alvarenga.Service;

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
}
