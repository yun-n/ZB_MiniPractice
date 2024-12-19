package com.example.demo.rental.dto;

public interface MemberRentalStat {
    Long getMemberId();
    Long getTotalRentalCount();
    String getMostRentedName();
    Long getRentalCount();
}
