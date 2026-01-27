package br.com.diogo_alvarenga.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diogo_alvarenga.Model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
