package br.com.diogo_alvarenga.data.dto;

import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

public class BookDTO extends RepresentationModel<BookDTO>{


		private Long id;
		
		private String author;
		
		private String launchDate;
		
		private Double price;
		
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
			BookDTO other = (BookDTO) obj;
			return Objects.equals(author, other.author) && Objects.equals(id, other.id)
					&& Objects.equals(launchDate, other.launchDate) && Objects.equals(price, other.price)
					&& Objects.equals(title, other.title);
		}

		public BookDTO() {
			
		}
	

}
