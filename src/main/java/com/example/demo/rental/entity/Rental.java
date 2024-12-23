package com.example.demo.rental.entity;

import com.example.demo.book.entity.Book;
import com.example.demo.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(indexes = {
        @Index(name = "idx_rental_memberid", columnList = "member_id"),
        @Index(name = "idx_rental_bookid", columnList = "book_id")
})
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @JsonIgnore
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @JsonIgnore
    private Book book;

    @Column
    private LocalDate rentalDate;

    @Column
    private LocalDate dueDate;

    @Column
    private boolean isReturned;

    @Column
    @CreatedDate
    private LocalDate createdAt;

    @Column
    @LastModifiedDate
    private LocalDate updatedAt;

    public Rental(Member member, Book book){
        this.member = member;
        this.book = book;
        this.rentalDate =  LocalDate.now();
        this.dueDate = rentalDate.plusDays(7);
        this.isReturned = false;
    }
}
