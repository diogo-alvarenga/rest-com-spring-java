package br.com.diogo_alvarenga.Model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "person")//entidade vinculada ao banco
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id //não preciso dizer que o id é uma coluna, porque ele ja entende como uma
	@GeneratedValue(strategy = GenerationType.IDENTITY)//incrementa de 1 em 1
	private Long id;
	
	@Column(name= "first_name", nullable= false, length = 80)//nome da coluna no banco, not null, tamanho maximo = 80
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 80)
	private String lastName;
	
	@Column(nullable = false, length =80)//quando nao passo o nome, ele usa o mesmo nome da entidade no banco (address)
	private String address;
	
	@Column(nullable = false, length = 6)
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
