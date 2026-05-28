package org.example.hn_ks24_cntt4_doanngocduy2.service;

import org.example.hn_ks24_cntt4_doanngocduy2.entity.Book;
import org.example.hn_ks24_cntt4_doanngocduy2.exception.BookNotFoundException;
import org.example.hn_ks24_cntt4_doanngocduy2.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void getAllBooks_returnList() {
        List<Book> mockBooks = List.of(new Book(1L, "A", "Auth1", "Cat1", 5),
                new Book(2L, "B", "Auth2", "Cat2", 3));
        when(bookRepository.findAll()).thenReturn(mockBooks);

        List<Book> result = bookService.getAllBooks();

        assertThat(result).hasSize(2);
    }

    @Test
    void getBookById_found() {
        Book mock = new Book(1L, "A", "Auth1", "Cat1", 5);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(mock));

        Book result = bookService.findById(1L);

        assertThat(result).isEqualTo(mock);
    }

    @Test
    void getBookById_notFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.findById(1L))
                .isInstanceOf(BookNotFoundException.class);
    }

    @Test
    void createBook_success() {
        Book book = new Book(null, "A", "Auth1", "Cat1", 5);
        Book saved = new Book(1L, "A", "Auth1", "Cat1", 5);
        when(bookRepository.save(book)).thenReturn(saved);

        Book result = bookService.createBook(book);

        assertThat(result).isEqualTo(saved);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void deleteBook_notFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.deleteBook(1L))
                .isInstanceOf(BookNotFoundException.class);

        verify(bookRepository, never()).deleteById(any());
    }
}