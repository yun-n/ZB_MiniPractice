package com.example.demo.rental.controller;

import com.example.demo.rental.entity.Rental;
import com.example.demo.rental.service.RentalService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/api/v1/rentals")
    public Rental rentBook(@RequestParam(value = "memberId") Long memberId, @RequestParam(value = "bookId") Long bookId){
        return rentalService.rentBook(memberId, bookId);
    }

    @PutMapping("/api/v1/rentals/return/{rentalId}")
    public Rental returnBook(@Parameter(name = "rentalId", description = "rentalId", example = "1",in = ParameterIn.PATH) @PathVariable("rentalId")  Long rentalId){
        return rentalService.returnBook(rentalId);
    }
}
