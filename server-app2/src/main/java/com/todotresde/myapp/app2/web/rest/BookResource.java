package com.todotresde.myapp.app2.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.todotresde.myapp.app2.domain.Book;

import com.todotresde.myapp.app2.repository.BookRepository;
import com.todotresde.myapp.app2.web.rest.errors.BadRequestAlertException;
import com.todotresde.myapp.app2.web.rest.util.HeaderUtil;
import com.todotresde.myapp.app2.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Book.
 */
@RestController
@RequestMapping("/api")
public class BookResource {

    private final Logger log = LoggerFactory.getLogger(BookResource.class);

    private static final String ENTITY_NAME = "book";

    private final BookRepository bookRepository;

    public BookResource(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * POST  /books : Create a new book.
     *
     * @param book the book to create
     * @return the ResponseEntity with status 201 (Created) and with body the new book, or with status 400 (Bad Request) if the book has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/books")
    @Timed
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) throws URISyntaxException {
        log.debug("REST request to save Book : {}", book);
        if (book.getId() != null) {
            throw new BadRequestAlertException("A new book cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.created(new URI("/api/books/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /books : Updates an existing book.
     *
     * @param book the book to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated book,
     * or with status 400 (Bad Request) if the book is not valid,
     * or with status 500 (Internal Server Error) if the book couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/books")
    @Timed
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book) throws URISyntaxException {
        log.debug("REST request to update Book : {}", book);
        if (book.getId() == null) {
            return createBook(book);
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, book.getId().toString()))
            .body(result);
    }

    /**
     * GET  /books : get all the books.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of books in body
     */
    @GetMapping("/books")
    @Timed
    public ResponseEntity<List<Book>> getAllBooks(Pageable pageable) {
        log.debug("REST request to get a page of Books");
        Page<Book> page = bookRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/books");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /books/:id : get the "id" book.
     *
     * @param id the id of the book to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the book, or with status 404 (Not Found)
     */
    @GetMapping("/books/{id}")
    @Timed
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        log.debug("REST request to get Book : {}", id);
        Book book = bookRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(book));
    }

    /**
     * DELETE  /books/:id : delete the "id" book.
     *
     * @param id the id of the book to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/books/{id}")
    @Timed
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        log.debug("REST request to delete Book : {}", id);
        bookRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
