package org.example.hn_ks24_cntt4_doanngocduy2.service;

import lombok.RequiredArgsConstructor;
import org.example.hn_ks24_cntt4_doanngocduy2.entity.Book;
import org.example.hn_ks24_cntt4_doanngocduy2.exception.BookNotFoundException;
import org.example.hn_ks24_cntt4_doanngocduy2.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Không tìm thấy id: " + id));
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existing = findById(id);

        existing.setTitle(updatedBook.getTitle());
        existing.setAuthor(updatedBook.getAuthor());
        existing.setCategory(updatedBook.getCategory());
        existing.setQuantity(updatedBook.getQuantity());

        return bookRepository.save(existing);
    }

    public Book patchBook(Long id, Book partialBook) {
        Book existing = findById(id);

        if (partialBook.getTitle() != null) existing.setTitle(partialBook.getTitle());
        if (partialBook.getAuthor() != null) existing.setAuthor(partialBook.getAuthor());
        if (partialBook.getCategory() != null) existing.setCategory(partialBook.getCategory());
        if (partialBook.getQuantity() != null) existing.setQuantity(partialBook.getQuantity());

        return bookRepository.save(existing);
    }

    public void deleteBook(Long id) {
        findById(id);
        bookRepository.deleteById(id);
    }
}