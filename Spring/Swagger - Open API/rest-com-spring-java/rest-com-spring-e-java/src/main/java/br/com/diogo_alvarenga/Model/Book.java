package br.com.diogo_alvarenga.Model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "books")//entidade vinculada ao banco
public class Book implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id //não preciso dizer que o id é uma coluna, porque ele ja entende como uma
	@GeneratedValue(strategy = GenerationType.IDENTITY)//incrementa de 1 em 1
	private Long id;
	
	@Column(nullable= false, length = 180)//nome da coluna no banco, not null, tamanho maximo = 80
	private String author;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "launch_date", nullable = false)
	private String launchDate;
	
	@Column(nullable = false)//quando nao passo o nome, ele usa o mesmo nome da entidade no banco (address)
	private Double price;
	
	@Column(nullable = false, length = 250)
	private String title;
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(String launchDate) {
		this.launchDate = launchDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}



	
	
	@Override
	public int hashCode() {
		return Objects.hash(author, id, launchDate, price, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(id, other.id)
				&& Objects.equals(launchDate, other.launchDate) && Objects.equals(price, other.price)
				&& Objects.equals(title, other.title);
	}

	public Book() {
		
	}
}
