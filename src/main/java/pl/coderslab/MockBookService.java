package pl.coderslab;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class MockBookService implements BookService {
    private List<Book> list;
    public static Long nextId = 4L;

    public MockBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce	Eckel", "Helion",
                "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        list.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
                "programming"));
    }

    @Override
    public List<Book> getBooks() {
        return list;
    }

    @Override
    public Optional<Book> get(Long id) {
        return Optional.ofNullable(getBook(id));
    }

    @Override
    public void add(Book book) {
        list.add(book);
    }

    @Override
    public void delete(Long id) {
        Book book = getBook(id);
        list.remove(book);
    }

    @Override
    public void update(Book book) {
        Book oldBook = getBook(book.getId());
        if (oldBook != null) {
            int idx = list.indexOf(oldBook);
            delete(book.getId());
            list.add(idx, book);
        }
        else {
            add(book);
        }
    }

    private Book getBook(Long id) {
        for (Book book : list) {
            if (Objects.equals(book.getId(), id)) {
                return book;
            }
        }
        return null;
    }
}
