package com.example.demo.rental.entity;

import com.example.demo.book.entity.Book;
import com.example.demo.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    @JsonIgnore
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnore
    private Book book;

    private LocalDate rentalDate;

    private LocalDate dueDate;

    private boolean isReturned;

    public Rental(Member member, Book book){
        this.member = member;
        this.book = book;
        this.rentalDate =  LocalDate.now();
        this.dueDate = rentalDate.plusDays(7);
        this.isReturned = false;
    }
}
