package com.example.library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "api/v1/book")
public class BookController {

    private final BookService bookService;



    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/sorted")
    public List<Book> getBooksSorted(
            @RequestParam(defaultValue = "year") String sortBy,
            @RequestParam(defaultValue = "desc") String order) {
        return bookService.getBooksSorted(sortBy, order);
    }

    @PostMapping("/{bookId}/rate")
    public void rateBook(@PathVariable Long bookId, @RequestParam Integer score) {
        bookService.submitRating(bookId, score);
    }




//    @PostMapping("/save")
//    public List<Book> saveBook(@RequestBody Book book) {
//        Book savedBook = service.saveBook(book);
//        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
//    }

//    @PutMapping("/{title}/put_rating")
//    public ResponseEntity<Book> addRating(@PathVariable String title, @RequestBody Float newRating) {
//        Book updatedBook = service.updateRating(title, newRating);
//        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete_book/{title}")
//    public ResponseEntity<Void> deleteBook(@PathVariable String title) {
//        service.deleteBook(title);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @GetMapping("/filter/{selection}")
//    public List<Book> filter(@PathVariable String selection) {
//        return service.filter(selection);
//    }
//
}
