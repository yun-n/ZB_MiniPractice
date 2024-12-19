package com.example.demo.book.repository;

import com.example.demo.book.entity.Book;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaBookManageRepository extends JpaRepository<Book, Long> {

//    @Lock(LockModeType.OPTIMISTIC)
//    Optional<Book> findById(Long id);

    @Query("SELECT b FROM Book b WHERE b.id = :id")
    Optional<Book> findBookWithVersion(@Param("id") Long id);

    //해당 엔터티를 다른 트랜잭션에서 읽거나 수정하지 못하도록 락을 설정
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    @Query("SELECT b FROM Book b WHERE b.id = :id")
//    Optional<Book> findByIdWithLock(@Param("id") Long id);
}
