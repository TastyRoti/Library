package com.example.library.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository){
        return args -> {
            Book book1 = new Book(
                    "To Kill a Mockingbird",
                    1960,
                    "Harper Lee",
                    0F
            );

            Book book2 = new Book(
                    "1984",
                    1949,
                    "George Orwell",
                    0F
            );

            Book book3 = new Book(
                    "Pride and Prejudice",
                    1813,
                    "Jane Austen",
                    0F
            );

            Book book4 = new Book(
                    "The Great Gatsby",
                    1925,
                    "F. Scott Fitzgerald",
                    0F
            );

            Book book5 = new Book(
                    "Moby Dick",
                    1851,
                    "Herman Melville",
                    0F
            );

            Book book6 = new Book(
                    "The Catcher in the Rye",
                    1951,
                    "J.D. Salinger",
                    0F
            );

            Book book7 = new Book(
                    "The Lord of the Rings",
                    1954,
                    "J.R.R. Tolkien",
                    0F
            );


            bookRepository.saveAll(
                    List.of(book1, book2, book3, book4, book5, book6,book7)
            );
        };
    }
}
