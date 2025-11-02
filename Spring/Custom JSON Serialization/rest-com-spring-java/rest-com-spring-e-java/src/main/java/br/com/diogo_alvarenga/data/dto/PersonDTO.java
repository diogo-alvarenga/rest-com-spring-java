package br.com.diogo_alvarenga.data.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.diogo_alvarenga.serializer.GenderSerializer;



//Definir ordem do json
//@JsonPropertyOrder({"id","address","first_name","last_name","gender"})
@JsonFilter("PersonFilter")
public class PersonDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

	private Long id;
	
	//definir o nome que vai aparecer
	//@JsonProperty("first_name")//mudei o nome, agora tenho que trocar la em cima no PropertyOrder
	private String firstName;
	
	//@JsonProperty("last_name")
	@JsonInclude(JsonInclude.Include.NON_NULL)//esse cambo só será renderizado quando nao estiver nulo
	private String lastName;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)//se tiver vazia, nao será renderizado
	private String phoneNumber;
	
	//Formatar data para padrao br
	@JsonFormat(pattern = "dd/mm/yyyy")
	private Date birthDay;
	
	private String address;
	
	//@JsonIgnore //irá ser ignorado
	@JsonSerialize(using = GenderSerializer.class)//se houver duvida, olhar a classe GenderSerializer
	private String gender;
	
	private String sensitiveData;

	
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

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String number) {
		this.phoneNumber = number;
	}

	public String getSensitiveData() {
		return sensitiveData;
	}

	public void setSensitiveData(String sensitiveData) {
		this.sensitiveData = sensitiveData;
	}
	
	
	@Override
	public int hashCode() {
		//O hashcode faz um calculo para criar um hash para cada dado
		//assim, cada dado diferente tera um hashcode diferente
		//e cada dado igual terá um hashcode igual
		return Objects.hash(address, birthDay, firstName, gender, id, lastName, phoneNumber, sensitiveData);
	}

	@Override
	public boolean equals(Object obj) {
	    // Verifica se 'this' e 'obj' apontam para o mesmo objeto na memória
	    // Exemplo:
	    // Person p1 = new Person("Ana");
	    // Person p2 = p1; // p1 e p2 referenciam o mesmo objeto
		if (this == obj)// 'this' se refere ao objeto que chamou o método, ou seja, a instância atual
			return true;
		
	    // Se o objeto passado for nulo, eles não são iguais
		if (obj == null)
			return false;
		
	    // Verifica se 'obj' é da mesma classe exata que 'this'		
		if (getClass() != obj.getClass()) // compara o tipo real dos objetos
			return false;
		
	    // Faz o cast para 'Person' porque já garantimos que são da mesma classe
		PersonDTO other = (PersonDTO) obj;
		
	    // Compara os atributos relevantes para definir se os objetos são "iguais"
		return Objects.equals(address, other.address) 
				&& Objects.equals(birthDay, other.birthDay)
				&& Objects.equals(firstName, other.firstName) 
				&& Objects.equals(gender, other.gender)
				&& Objects.equals(id, other.id) 
				&& Objects.equals(lastName, other.lastName)
				&& Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(sensitiveData, other.sensitiveData);

	}

	public PersonDTO() {
		
	}
}
