package com.example.demo.rental.dto;

import com.example.demo.book.entity.Book;
import com.example.demo.member.entity.Member;
import com.example.demo.rental.entity.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RentalResponseDto {

    private Long id;

    private Member member;

    private Book book;

    private LocalDate rentalDate;

    private LocalDate dueDate;

    private boolean isReturned;

    public RentalResponseDto(Rental rental){
        this.id = rental.getId();
        this.member = rental.getMember();
        this.rentalDate = rental.getRentalDate();
        this.dueDate = rental.getDueDate();
        this.isReturned = rental.isReturned();

    }

}
