package br.com.diogo_alvarenga.Model;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String gender;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	@Override
	public int hashCode() {
		//O hashcode faz um calculo para criar um hash para cada dado
		//assim, cada dado diferente tera um hashcode diferente
		//e cada dado igual terá um hashcode igual
		return Objects.hash(address, firstName, gender, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
	    // Verifica se 'this' e 'obj' apontam para o mesmo objeto na memória
	    // Exemplo:
	    // Person p1 = new Person("Ana");
	    // Person p2 = p1; // p1 e p2 referenciam o mesmo objeto
	    if (this == obj) // 'this' se refere ao objeto que chamou o método, ou seja, a instância atual
	        return true;

	    // Se o objeto passado for nulo, eles não são iguais
	    if (obj == null)
	        return false;

	    // Verifica se 'obj' é da mesma classe exata que 'this'
	    if (getClass() != obj.getClass()) // compara o tipo real dos objetos
	        return false;

	    // Faz o cast para 'Person' porque já garantimos que são da mesma classe
	    Person other = (Person) obj;

	    // Compara os atributos relevantes para definir se os objetos são "iguais"
	    return Objects.equals(address, other.address) 
	        && Objects.equals(firstName, other.firstName)
	        && Objects.equals(gender, other.gender) 
	        && Objects.equals(id, other.id)
	        && Objects.equals(lastName, other.lastName);
	}

	
	
	public Person() {
		
	}
}
