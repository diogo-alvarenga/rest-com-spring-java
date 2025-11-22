package br.com.diogo_alvarenga.service;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.diogo_alvarenga.Exception.RequiredObjectIsNullException;
import br.com.diogo_alvarenga.Model.Person;
import br.com.diogo_alvarenga.Repository.PersonRepository;
import br.com.diogo_alvarenga.Service.PersonService;
import br.com.diogo_alvarenga.data.dto.PersonDTO;
import br.com.diogo_alvarenga.unitetests.mapper.mocks.MockPerson;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)//ciclo de vida do junit, os objetos irao durar apenas para essa classe
@ExtendWith(MockitoExtension.class)//habilita recursos extras durante a execução de testes
public class PersonServiceTest {

	MockPerson input;
	
	@InjectMocks //a classe que iremos injetar mocks (a que estaremos mockito)
	private PersonService service;
	
	@Mock //mockito vai cuidar dessa instancia
	PersonRepository repository;
	
	@BeforeEach
	void setUp() {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);// this se refere ao objeto da classe PersonServiceTest que está sendo usado
	}
	
	/*
	 * Primeiro o objeto input retorna um person que vai para dentro de person.
	 * assim, voce seta o id manualmente (1L)
	 * repository é um objeto mockado, isso signific aque ele nao envia para o banco de dados
	 * a parte do when é:
	 * quando o findById(do repository) for chamado com 1L, retorne esse objeto person dentro de um Optional
	 * entao, na var result tem o person
	 * cada asserNotNull verifica se tal coisa nao esta nula
	 * */
	//no geral, o metodo cria o mock e verifica se cada campo está preenchidom se nao estiver, da um erro
	@Test
	void findById() {
		Person person = input.mockEntity(1);//numero que será usado no mock
		person.setId(1L);
		when(repository.findById(1L))//when precisa ser importado manualmente com import static org.mockito.Mockito.when;
		.thenReturn(Optional.of(person));
		
		var result = service.findById(1L);
		
		//assertNotNull precisa ser importado estaticamente com import static org.junit.jupiter.api.Assertions.assertNotNull;
		assertNotNull(result); // testa se o retorno não é nulo
		assertNotNull(result.getId()); // testa se o id não é nulo
		assertNotNull(result.getLinks());  // testa se os links não são nulos
		assertNotNull(result.getLinks() //retorna uma lista de links (HATEOAS)
				.stream()// transofmra em um tipo de lista que pode usar anyMatch(esse metodo retorna boolean)
				.anyMatch(link -> link.getRel().value().equals("self")//verifica se o rel é igual a self (rel esta no retorno das listas, verificar no postman)
						&& link.getHref().endsWith("/api/person/v1/1")//verifica se o link termina com
						&& link.getType().equals("GET")));//verifica se o tipo é GET
		
		assertNotNull(result.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("findAll")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("GET")));
		
		assertNotNull(result.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("create")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("POST")));
		
		assertNotNull(result.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("update")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("PUT")));
		
		assertNotNull(result.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("delete")
						&& link.getHref().endsWith("/api/person/v1/1")
						&& link.getType().equals("DELETE")));
		
		//o metodo assertEquals se importa com import static org.junit.jupiter.api.Assertions.assertEquals;
		//assertEquals(o que se espera, o que tem)
		//lembrando que Address Test1 seria parte do mock que se faz na classe MockPerson
		assertEquals("Address Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
		//assertNotNull precisa ser importado estaticamente com import static org.junit.jupiter.api.Assertions.assertNotNull;
		assertNotNull(result); // testa se o retorno não é nulo
		assertNotNull(result.getId()); // testa se o id não é nulo
		assertNotNull(result.getLinks());  // testa se os links não são nulos
		assertNotNull(result.getLinks() //retorna uma lista de links (HATEOAS)
				.stream()// transofmra em um tipo de lista que pode usar anyMatch(esse metodo retorna boolean)
				.anyMatch(link -> link.getRel().value().equals("self")//verifica se o rel é igual a self (rel esta no retorno das listas, verificar no postman)
						&& link.getHref().endsWith("/api/person/v1/1")//verifica se o link termina com
						&& link.getType().equals("GET")));//verifica se o tipo é GET
		
		assertNotNull(result.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("findAll")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("GET")));
		
		assertNotNull(result.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("create")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("POST")));
		
		assertNotNull(result.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("update")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("PUT")));
		
		assertNotNull(result.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("delete")
						&& link.getHref().endsWith("/api/person/v1/1")
						&& link.getType().equals("DELETE")));
		
		//o metodo assertEquals se importa com import static org.junit.jupiter.api.Assertions.assertEquals;
		//assertEquals(o que se espera, o que tem)
		//lembrando que Address Test1 seria parte do mock que se faz na classe MockPerson
		assertEquals("Address Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());

	}
	
	@Test
	void create() {
		Person person = input.mockEntity(1);
		Person persisted = person;
		persisted.setId(1L);
		
		PersonDTO dto = input.mockDTO(1);
		when(repository.save(person))//when precisa ser importado manualmente com import static org.mockito.Mockito.when;
		.thenReturn(persisted);
		
		var result = service.create(dto);
		
		//assertNotNull precisa ser importado estaticamente com import static org.junit.jupiter.api.Assertions.assertNotNull;
		assertNotNull(result); // testa se o retorno não é nulo
		assertNotNull(result.getId()); // testa se o id não é nulo
		assertNotNull(result.getLinks());  // testa se os links não são nulos
		assertNotNull(result.getLinks() //retorna uma lista de links (HATEOAS)
				.stream()// transofmra em um tipo de lista que pode usar anyMatch(esse metodo retorna boolean)
				.anyMatch(link -> link.getRel().value().equals("self")//verifica se o rel é igual a self (rel esta no retorno das listas, verificar no postman)
						&& link.getHref().endsWith("/api/person/v1/1")//verifica se o link termina com
						&& link.getType().equals("GET")));//verifica se o tipo é GET
		
		assertNotNull(result.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("findAll")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("GET")));
		
		assertNotNull(result.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("create")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("POST")));
		
		assertNotNull(result.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("update")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("PUT")));
		
		assertNotNull(result.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("delete")
						&& link.getHref().endsWith("/api/person/v1/1")
						&& link.getType().equals("DELETE")));
		
		//o metodo assertEquals se importa com import static org.junit.jupiter.api.Assertions.assertEquals;
		//assertEquals(o que se espera, o que tem)
		//lembrando que Address Test1 seria parte do mock que se faz na classe MockPerson
		assertEquals("Address Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testCreateWithNullPerson() { //esse codigo ta explicado no metodo mais a baixo, testUpdateWithNullPerson
		Exception exception = assertThrows(RequiredObjectIsNullException.class,
				() -> {
					service.create(null);
				});
		
		String expectedMessage = "It is not allowed to persist a null object!";//mensagem esperada
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void update() {
		Person person = input.mockEntity(1);
		Person persisted = person;
		persisted.setId(1L);
		
		PersonDTO dto = input.mockDTO(1);
		
		when(repository.findById(1L))
		.thenReturn(Optional.of(person));//findbyid ta dentro do update()
		
		when(repository.save(person))
		.thenReturn(persisted);
		
		var result = service.update(dto);
		
		//assertNotNull precisa ser importado estaticamente com import static org.junit.jupiter.api.Assertions.assertNotNull;
		assertNotNull(result); // testa se o retorno não é nulo
		assertNotNull(result.getId()); // testa se o id não é nulo
		assertNotNull(result.getLinks());  // testa se os links não são nulos
		assertNotNull(result.getLinks() //retorna uma lista de links (HATEOAS)
				.stream()// transofmra em um tipo de lista que pode usar anyMatch(esse metodo retorna boolean)
				.anyMatch(link -> link.getRel().value().equals("self")//verifica se o rel é igual a self (rel esta no retorno das listas, verificar no postman)
						&& link.getHref().endsWith("/api/person/v1/1")//verifica se o link termina com
						&& link.getType().equals("GET")));//verifica se o tipo é GET
		
		assertNotNull(result.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("findAll")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("GET")));
		
		assertNotNull(result.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("create")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("POST")));
		
		assertNotNull(result.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("update")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("PUT")));
		
		assertNotNull(result.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("delete")
						&& link.getHref().endsWith("/api/person/v1/1")
						&& link.getType().equals("DELETE")));
		
		//o metodo assertEquals se importa com import static org.junit.jupiter.api.Assertions.assertEquals;
		//assertEquals(o que se espera, o que tem)
		//lembrando que Address Test1 seria parte do mock que se faz na classe MockPerson
		assertEquals("Address Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows( //verifica se um bloco de codigo lança uma exceção especifica
					RequiredObjectIsNullException.class,//a exceção que se espera
						() -> {
							service.update(null);//o codigo que será testado (para ver se lança a exceção)
						});
		
		String expectedMessage = "It is not allowed to persist a null object!";//mensagem esperada
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));//verifica se a expction possui a mesma mensagem da outra string
	}
	
	@Test
	void delete() {//o delete precisa do mock porque dentro do metodo, ele faz um findById
		Person person = input.mockEntity(1);//numero que será usado no mock
		person.setId(1L);
		when(repository.findById(1L))//ele fará o findById dentro do delete()
		.thenReturn(Optional.of(person));
		
		service.delete(1L);
		verify( //verify é um metodo do mockito que verifica se um metodo foi chamado
				repository, //o mock que irá inspecionar
				times(1)) //quantas vezes o metodo deveria ter sido chamado
				.findById( // o metodo do mock que quer verificar
						anyLong());// qualuer valor long passado dentro do metodo (findById)
		verify(repository,times(1)).delete(any(Person.class));
		verifyNoMoreInteractions(repository);// verifica se nao houve outras interações com o mock, alem das que verificou antes

	}
	
	@Test
	void findAll() {
		List<Person> list = input.mockEntityList();
		when(repository.findAll()).thenReturn(list);
		List<PersonDTO> people =service.findAll();
		assertNotNull(people); //verifica se nao esta nulo
		assertEquals(14,people.size()); //verifica se a quantidade é igual a 14 (a quantidade de objetos que o mock gera)
		
		var personOne = people.get(1);//pega a pessoa de indice 1 na lista
		
		assertNotNull(personOne); // testa se o retorno não é nulo
		assertNotNull(personOne.getId()); // testa se o id não é nulo
		assertNotNull(personOne.getLinks());  // testa se os links não são nulos
		assertNotNull(personOne.getLinks() //retorna uma lista de links (HATEOAS)
				.stream()// transofmra em um tipo de lista que pode usar anyMatch(esse metodo retorna boolean)
				.anyMatch(link -> link.getRel().value().equals("self")//verifica se o rel é igual a self (rel esta no retorno das listas, verificar no postman)
						&& link.getHref().endsWith("/api/person/v1/1")//verifica se o link termina com
						&& link.getType().equals("GET")));//verifica se o tipo é GET
		
		assertNotNull(personOne.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("findAll")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("GET")));
		
		assertNotNull(personOne.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("create")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("POST")));
		
		assertNotNull(personOne.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("update")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("PUT")));
		
		assertNotNull(personOne.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("delete")
						&& link.getHref().endsWith("/api/person/v1/1")
						&& link.getType().equals("DELETE")));
		
		//o metodo assertEquals se importa com import static org.junit.jupiter.api.Assertions.assertEquals;
		//assertEquals(o que se espera, o que tem)
		//lembrando que Address Test1 seria parte do mock que se faz na classe MockPerson
		assertEquals("Address Test1", personOne.getAddress());
		assertEquals("First Name Test1", personOne.getFirstName());
		assertEquals("Last Name Test1", personOne.getLastName());
		assertEquals("Female", personOne.getGender());
		
		
		
		
		var personFour = people.get(4);
		
		assertNotNull(personFour); // testa se o retorno não é nulo
		assertNotNull(personFour.getId()); // testa se o id não é nulo
		assertNotNull(personFour.getLinks());  // testa se os links não são nulos
		assertNotNull(personFour.getLinks() //retorna uma lista de links (HATEOAS)
				.stream()// transofmra em um tipo de lista que pode usar anyMatch(esse metodo retorna boolean)
				.anyMatch(link -> link.getRel().value().equals("self")//verifica se o rel é igual a self (rel esta no retorno das listas, verificar no postman)
						&& link.getHref().endsWith("/api/person/v1/4")//verifica se o link termina com
						&& link.getType().equals("GET")));
		
		assertNotNull(personFour.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("findAll")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("GET")));
		
		assertNotNull(personFour.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("create")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("POST")));
		
		assertNotNull(personFour.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("update")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("PUT")));
		
		assertNotNull(personFour.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("delete")
						&& link.getHref().endsWith("/api/person/v1/4")
						&& link.getType().equals("DELETE")));
		

		assertEquals("Address Test4", personFour.getAddress());
		assertEquals("First Name Test4", personFour.getFirstName());
		assertEquals("Last Name Test4", personFour.getLastName());
		assertEquals("Male", personFour.getGender());
		
		
		
		var personSeven = people.get(7);
		
		assertNotNull(personSeven); // testa se o retorno não é nulo
		assertNotNull(personSeven.getId()); // testa se o id não é nulo
		assertNotNull(personSeven.getLinks());  // testa se os links não são nulos
		assertNotNull(personSeven.getLinks() //retorna uma lista de links (HATEOAS)
				.stream()// transofmra em um tipo de lista que pode usar anyMatch(esse metodo retorna boolean)
				.anyMatch(link -> link.getRel().value().equals("self")//verifica se o rel é igual a self (rel esta no retorno das listas, verificar no postman)
						&& link.getHref().endsWith("/api/person/v1/7")//verifica se o link termina com
						&& link.getType().equals("GET")));
		
		assertNotNull(personSeven.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("findAll")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("GET")));
		
		assertNotNull(personSeven.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("create")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("POST")));
		
		assertNotNull(personSeven.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("update")
						&& link.getHref().endsWith("/api/person/v1")
						&& link.getType().equals("PUT")));
		
		assertNotNull(personSeven.getLinks() 
				.stream()
				.anyMatch(link -> link.getRel().value().equals("delete")
						&& link.getHref().endsWith("/api/person/v1/7")
						&& link.getType().equals("DELETE")));
		

		assertEquals("Address Test7", personSeven.getAddress());
		assertEquals("First Name Test7", personSeven.getFirstName());
		assertEquals("Last Name Test7", personSeven.getLastName());
		assertEquals("Female", personSeven.getGender());
	
	
	}
}
