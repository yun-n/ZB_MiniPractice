package com.example.demo.rental.repository;

import com.example.demo.rental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByMemberId(Long memberId);

    long countByMemberIdAndIsReturnedFalse(Long memberId);

    List<Rental> findByBookIdAndIsReturnedFalse(Long memberId);
}
