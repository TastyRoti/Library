package com.example.library.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT b.*, AVG(r.score) as totalRating " +
            "FROM Book b LEFT JOIN Rating r ON b.id = r.book_id " +
            "GROUP BY b.id ORDER BY totalRating ?1", nativeQuery = true)
    List<Book> findAllSortedByRating(String order);
}

