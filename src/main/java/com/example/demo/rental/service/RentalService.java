package com.example.demo.rental.service;

import com.example.demo.book.entity.Book;
import com.example.demo.book.repository.JpaBookManageRepository;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.rental.dto.RentalResponseDto;
import com.example.demo.rental.entity.Rental;
import com.example.demo.rental.repository.RentalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final MemberRepository memberRepository;
    private final JpaBookManageRepository jpaBookManageRepository;


    @Transactional
    public RentalResponseDto rentBook(Long memberId, Long bookId){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원 Id" + memberId + " 가 존재하지 않습니다" ));
//        Pessimistic Lock 제거
//        Book book = jpaBookManageRepository.findByIdWithLock(bookId).orElseThrow(()->new IllegalArgumentException("도서 Id " + bookId + " 가 존재하지 않습니다."));

        Book book = jpaBookManageRepository.findById(bookId).orElseThrow(()->new IllegalArgumentException("도서 Id " + bookId + " 가 존재하지 않습니다."));

        if(!rentalRepository.findByBookIdAndIsReturnedFalse(bookId).isEmpty()){
            throw new IllegalArgumentException("이미 대여중인 도서입니다.");
        }

        if(rentalRepository.countByMemberIdAndIsReturnedFalse(memberId) >= 3){
            throw new IllegalArgumentException("최대 3권까지만 대출 가능합니다.");
        }

        try{
            Rental rental = new Rental(member, book);
            Rental savedRental = rentalRepository.save(rental);
            return new RentalResponseDto(savedRental);
        }catch (OptimisticEntityLockException e){
            throw new IllegalArgumentException("중복요청, 다시 시도해주세요.");
        }
//        Rental rental = new Rental(member, book);
//        Rental savedRental = rentalRepository.save(rental);
//        return new RentalResponseDto(savedRental);

    }

    public RentalResponseDto returnBook(Long rentalId){
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(()-> new IllegalArgumentException("대여 Id" + rentalId +  "를 찾을 수 없습니다."));

        if(rental.isReturned()){
            throw new IllegalArgumentException("이미 반납된 도서입니다.");
        }

        rental.setReturned(true);
        return new RentalResponseDto(rental);
    }
}
