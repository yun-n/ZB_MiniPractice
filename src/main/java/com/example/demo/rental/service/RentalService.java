package com.example.demo.rental.service;

import com.example.demo.book.entity.Book;
import com.example.demo.book.repository.JpaBookManageRepository;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.rental.dto.BookRentalStat;
import com.example.demo.rental.dto.MemberRentalStat;
import com.example.demo.rental.dto.RentalResponseDto;
import com.example.demo.rental.entity.Rental;
import com.example.demo.rental.repository.RentalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import static com.example.demo.rental.Contant.RENTAL_LIMIT_COUNT;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final MemberRepository memberRepository;
    private final JpaBookManageRepository jpaBookManageRepository;


    @Transactional
    public RentalResponseDto rentBook(Long memberId, Long bookId){

            Member member = memberRepository.findById(memberId).orElseThrow(() -> new AppException(ErrorCode.NOT_EXISTS_ACCOUNT_ID, memberId));
//        Pessimistic Lock 제거
//        Book book = jpaBookManageRepository.findByIdWithLock(bookId).orElseThrow(()->new IllegalArgumentException("도서 Id " + bookId + " 가 존재하지 않습니다."));

            Book book = jpaBookManageRepository.findBookWithVersion(bookId)
                    .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_BOOK_ID, bookId));

            if(!rentalRepository.findByBookIdAndIsReturnedFalse(bookId).isEmpty()){
                throw new AppException(ErrorCode.ALREADY_RENTED_BOOK);
            }

            if(rentalRepository.countByMemberIdAndIsReturnedFalse(memberId) >= RENTAL_LIMIT_COUNT){
                throw new AppException(ErrorCode.LIMITED_COUNT_RENTAL_BOOK, RENTAL_LIMIT_COUNT);
            }

        book.rentBook();
        try{

            Rental rental = new Rental(member, book);
            Rental savedRental = rentalRepository.save(rental);

            return new RentalResponseDto(savedRental);
        } catch (OptimisticLockingFailureException|OptimisticEntityLockException e){
            throw new AppException(ErrorCode.FAILED_RENTAL_BOOK_WITH_LOCK, e);
        }
    }

    @Transactional
    public RentalResponseDto returnBook(Long rentalId){
        try{
            Rental rental = rentalRepository.findById(rentalId).orElseThrow(()-> new IllegalArgumentException("대여 Id" + rentalId +  "를 찾을 수 없습니다."));

            if(rental.isReturned()){
                throw new AppException(ErrorCode.ALREADY_RETURNED_BOOK);
            }

            rental.getBook().returnBook();
            rental.setReturned(true);

            return new RentalResponseDto(rental);
        } catch (OptimisticLockingFailureException|OptimisticEntityLockException e){
            throw new AppException(ErrorCode.FAILED_RENTAL_BOOK_WITH_LOCK, e);
        }
    }

    @Cacheable(value = "topBooks", key = "{#startDate, #endDate}")
    public List<BookRentalStat> getTop10RentedBooks(String startMonth, String endMonth) {
        YearMonth startYm = YearMonth.parse(startMonth);
        YearMonth endYm = YearMonth.parse(endMonth);
        LocalDate startDate = startYm.atDay(1);
        LocalDate endDate = endYm.atEndOfMonth();

        Pageable topTen = PageRequest.of(0, 10);
        return rentalRepository.findTopBooksByRentalCount(startDate, endDate, topTen);
    }

    @Cacheable(value = "memberStats", key = "{#memberId, #startDate, #endDate}")
    public MemberRentalStat getMemberRentalStats(Long memberId, String startMonth, String endMonth) {
        YearMonth startYm = YearMonth.parse(startMonth);
        YearMonth endYm = YearMonth.parse(endMonth);
        LocalDate startDate = startYm.atDay(1);
        LocalDate endDate = endYm.atEndOfMonth();

        List<MemberRentalStat> stats = rentalRepository.findMostRentedBookForMember(memberId, startDate, endDate);
        return stats.isEmpty() ? null : stats.get(0);
    }
}
