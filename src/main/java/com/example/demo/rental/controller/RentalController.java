package com.example.demo.rental.controller;

import com.example.demo.rental.dto.BookRentalStat;
import com.example.demo.rental.dto.MemberRentalStat;
import com.example.demo.rental.dto.RentalResponseDto;
import com.example.demo.rental.service.RentalService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/api/v1/rentals")
    public RentalResponseDto rentBook(@RequestParam(value = "memberId") Long memberId, @RequestParam(value = "bookId") Long bookId){
        return rentalService.rentBook(memberId, bookId);
    }

    @PutMapping("/api/v1/rentals/{rentalId}/return")
    public RentalResponseDto returnBook(@Parameter(name = "rentalId", description = "rentalId", example = "1",in = ParameterIn.PATH) @PathVariable("rentalId")  Long rentalId){
        return rentalService.returnBook(rentalId);
    }

    @GetMapping("/api/v1/rentals/topBooks")
    public List<BookRentalStat> getTopBooks(@RequestParam(name = "startDate")  @DateTimeFormat(pattern = "yyyy-MM") String startDate,
                                            @RequestParam(name = "endDate")  @DateTimeFormat(pattern = "yyyy-MM") String endDate) {
        return rentalService.getTop10RentedBooks(startDate, endDate);
    }

    @GetMapping("/api/v1/rentals/{memberId}/MemberStats")
    public MemberRentalStat getMemberStats(@Parameter(name = "memberId", description = "memberId", example = "1",in = ParameterIn.PATH) @PathVariable("memberId")  Long memberId,
                                           @RequestParam(name = "startDate") @DateTimeFormat(pattern = "yyyy-MM") String startDate,
                                           @RequestParam(name = "endDate") @DateTimeFormat(pattern = "yyyy-MM") String endDate) {
        return rentalService.getMemberRentalStats(memberId, startDate, endDate);
    }
}
