package com.example.library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
public class BookService {


    private final BookRepository bookRepository;

    private final RatingRepository ratingRepository;


    @Autowired
    public BookService(BookRepository bookRepository, RatingRepository ratingRepository) {
        this.bookRepository = bookRepository;
        this.ratingRepository = ratingRepository;
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public List<Book> getBooksSorted(String sortBy, String order) {
        List<Book> books = bookRepository.findAll();

        if ("totalRating".equalsIgnoreCase(sortBy)) {
            books.sort((b1, b2) -> {
                Float rating1 = b1.getTotalRating();
                Float rating2 = b2.getTotalRating();
                return order.equalsIgnoreCase("asc") ? rating1.compareTo(rating2) : rating2.compareTo(rating1);
            });
        } else {
            books.sort((b1, b2) -> {
                Comparable value1;
                Comparable value2;

                switch (sortBy.toLowerCase()) {
                    case "name":
                        value1 = b1.getName();
                        value2 = b2.getName();
                        break;
                    case "year":
                        value1 = b1.getYear();
                        value2 = b2.getYear();
                        break;
                    case "author":
                        value1 = b1.getAuthor();
                        value2 = b2.getAuthor();
                        break;
                    default:
                        value1 = b1.getYear();
                        value2 = b2.getYear();
                }

                return order.equalsIgnoreCase("asc") ? value1.compareTo(value2) : value2.compareTo(value1);
            });
        }

        return books;
    }

    public void submitRating(Long bookId, Integer score) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            Rating rating = new Rating(score, book);
            ratingRepository.save(rating);
        } else {
            throw new IllegalArgumentException("Book with ID " + bookId + " not found.");
        }
    }



}
