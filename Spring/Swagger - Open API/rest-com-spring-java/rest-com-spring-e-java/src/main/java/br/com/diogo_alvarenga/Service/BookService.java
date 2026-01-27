package br.com.diogo_alvarenga.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diogo_alvarenga.Controller.BookController;
import br.com.diogo_alvarenga.Exception.RequiredObjectIsNullException;
import br.com.diogo_alvarenga.Exception.ResourceNotFoundException;
import br.com.diogo_alvarenga.Model.Book;
import br.com.diogo_alvarenga.Repository.BookRepository;
import br.com.diogo_alvarenga.data.dto.BookDTO;
import static br.com.diogo_alvarenga.mapper.ObjectMapper.parseListObjects;
import static br.com.diogo_alvarenga.mapper.ObjectMapper.parseObject;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    // Logger será explicado mais tarde
    private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BookService.class.getName());

    public BookDTO findById(Long id) {
        logger.info("Finding one Book!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        // Convertendo para BookDTO
        var dto = parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public List<BookDTO> findAll() {
        logger.info("Find all Books!");

        // Converte para BookDTO
        var books = parseListObjects(repository.findAll(), BookDTO.class);
        books.forEach(this::addHateoasLinks); // Para cada objeto que vier :: mande para esse metodo
        return books;
    }

    public BookDTO create(BookDTO book) {
        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Create one Book!");

        // Convertendo de DTO para Book(entidade)
        var entity = parseObject(book, Book.class);

        // Salva e converte para DTO
        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO update(BookDTO book) {
        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one Book!");

        Book entity = repository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting one Book!");

        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class)
                .findById(dto.getId()))
                .withSelfRel()
                .withType("GET"));

        dto.add(linkTo(methodOn(BookController.class)
                .delete(dto.getId()))
                .withRel("delete")
                .withType("DELETE"));

        dto.add(linkTo(methodOn(BookController.class)
                .findAll())
                .withRel("findAll")
                .withType("GET"));

        dto.add(linkTo(methodOn(BookController.class)
                .create(dto))
                .withRel("create")
                .withType("POST"));

        dto.add(linkTo(methodOn(BookController.class)
                .update(dto)) // Controller ainda chama updatePerson
                .withRel("update")
                .withType("PUT"));
    }
}
