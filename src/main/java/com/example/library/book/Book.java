package com.example.library.book;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence", allocationSize = 1)

    private Long id;
    private String name;
    private Integer year;
    private String author;
    //@Transient
    private Float totalRating;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rating> ratings;

    public Book() {

    }
    public Book(Long id, String name, Integer year, String author, Float totalRating) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.author = author;
        this.totalRating = totalRating;
    }

    public Book(String name, Integer year, String author, Float totalRating) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.totalRating = totalRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", author='" + author + '\'' +
                ", totalRating=" + totalRating +
                '}';
    }

    public Float getTotalRating() {
        if (ratings == null || ratings.isEmpty()) {
            return 0f;
        }
        int total = ratings.stream().mapToInt(Rating::getScore).sum();
        return (float) total / ratings.size();
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

}
