package br.com.diogo_alvarenga.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


import br.com.diogo_alvarenga.Model.Person;
import br.com.diogo_alvarenga.Repository.PersonRepository;
import br.com.diogo_alvarenga.Service.PersonService;
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

	}
	
	@Test
	void create() {
		
	}
	
	@Test
	void update() {
		
	}
	
	@Test
	void delete() {
		
	}
	
	@Test
	void findAll() {
		
	}
}
