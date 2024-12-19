package com.example.demo.rental.repository;

import com.example.demo.rental.dto.BookRentalStat;
import com.example.demo.rental.dto.MemberRentalStat;
import com.example.demo.rental.entity.Rental;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByMemberId(Long memberId);

    long countByMemberIdAndIsReturnedFalse(Long memberId);

    List<Rental> findByBookIdAndIsReturnedFalse(Long memberId);

    @Query("SELECT r.book.id AS bookId, r.book.bookName AS bookName, COUNT(r) AS rentalCount " +
            "FROM Rental r " +
            "WHERE r.rentalDate BETWEEN :startDate AND :endDate " +
            "GROUP BY r.book.id, r.book.bookName " +
            "ORDER BY rentalCount DESC, r.book.id ASC")
    List<BookRentalStat> findTopBooksByRentalCount(@Param("startDate") LocalDate startDate,
                                                     @Param("endDate") LocalDate endDate, Pageable pageable);

    @Query("SELECT r.member.id AS memberId, " +
            "       (SELECT COUNT(r1) FROM Rental r1 WHERE r1.member.id = :memberId) AS totalRentalCount, " +
            "       r.book.bookName AS mostRentedName, " +
            "       COUNT(r.book.bookName) AS rentalCount " +
            "FROM Rental r " +
            "WHERE r.member.id = :memberId AND r.rentalDate BETWEEN :startDate AND :endDate " +
            "GROUP BY r.book.bookName " +
            "ORDER BY rentalCount DESC")
    List<MemberRentalStat> findMostRentedBookForMember(@Param("memberId") Long memberId,
                                                       @Param("startDate") LocalDate startDate,
                                                       @Param("endDate") LocalDate endDate);

}
