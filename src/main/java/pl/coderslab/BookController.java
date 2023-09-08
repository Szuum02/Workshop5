package pl.coderslab;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Book> getBooks() {
        return service.getBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id) {
        Optional<Book> book = service.get(id);
        return book.orElseGet(Book::new);
    }

    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        service.add(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        service.delete(id);
    }

    @PutMapping("")
    public void updateBook(@RequestBody Book book) {
        service.update(book);
    }
}
