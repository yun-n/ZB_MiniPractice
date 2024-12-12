package com.example.demo.rental.service;

import com.example.demo.book.entity.Book;
import com.example.demo.book.repository.JpaBookManageRepository;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.rental.entity.Rental;
import com.example.demo.rental.repository.RentalRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class RentalServiceTest {

    @InjectMocks
    private RentalService rentalService;
    @Mock
    private RentalRepository rentalRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private JpaBookManageRepository jpaBookManageRepository;

    @Test
    void ConcurrentRentalTest() throws InterruptedException{
        // given
        Long bookId = 1L;
        Long memberId = 1L;

        Member member = new Member();
        member.setId(memberId);
        Book book = new Book();
        book.setId(bookId);

        Rental rental = new Rental(member, book);

        Mockito.when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        Mockito.when(jpaBookManageRepository.findByIdWithLock(bookId)).thenReturn(Optional.of(book));
        Mockito.when(rentalRepository.findByBookIdAndIsReturnedFalse(bookId)).thenReturn(List.of());
        Mockito.when(rentalRepository.countByMemberIdAndIsReturnedFalse(memberId)).thenReturn(0L);
        Mockito.when(rentalRepository.save(Mockito.any(Rental.class))).thenReturn(rental);

        Runnable rentTask = () -> {
            try {
                rentalService.rentBook(memberId, bookId);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        };

        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        // When
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(rentTask);
        }

        executorService.awaitTermination(10, TimeUnit.SECONDS);

        // Then
        Mockito.verify(rentalRepository, Mockito.times(threadCount))
                .save(Mockito.any(Rental.class));

    }
}