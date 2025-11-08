package br.com.diogo_alvarenga.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diogo_alvarenga.Model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
