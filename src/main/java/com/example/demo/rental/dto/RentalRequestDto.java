package com.example.demo.rental.dto;

import com.example.demo.book.entity.Book;
import com.example.demo.member.entity.Member;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RentalRequestDto {

    private Member member;

    private Book book;

    private LocalDate rentalDate;

    private LocalDate dueDate;

    private boolean isReturned;

}
