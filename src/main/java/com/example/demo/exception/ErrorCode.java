package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // book management
    NOT_FOUND_BOOK_ID("도서 %d를 찾을 수 없습니다.", "000001", HttpStatus.NOT_FOUND),
    NOT_FOUND_CATEGORY_ID("카테고리 %d 찾을 수 없습니다.", "000002", HttpStatus.NOT_FOUND),
    EXCEEDED_LIMIT_RENTAL_BOOK("대여 가능 수량이 부족합니다.", "000003", HttpStatus.BAD_REQUEST),
    LIMITED_COUNT_RENTAL_BOOK("최대 %d권 까지만 대출 가능합니다.", "000004", HttpStatus.BAD_REQUEST),
    ALREADY_RENTED_BOOK("이미 대여중인 도서입니다.", "000005", HttpStatus.BAD_REQUEST),
    FAILED_RENTAL_BOOK_WITH_LOCK("대여 과정에서 오류가 발생하였습니다. 재시도해주세요.", "000006", HttpStatus.INTERNAL_SERVER_ERROR),
    ALREADY_RETURNED_BOOK("이미 반납된 도서입니다.", "000007", HttpStatus.BAD_REQUEST),

    // accounts
    ALREADY_REGISTERED_EMAIL("이미 사용 중인 이메일입니다: %s", "100001", HttpStatus.CONFLICT),
    NOT_FOUND_ACCOUNT_ID("회원 %d를 찾을 수 없습니다.", "100002", HttpStatus.NOT_FOUND),
    NOT_EXISTS_ACCOUNT_ID("회원 ID %d이 존재하지 않습니다", "100003", HttpStatus.NOT_FOUND),
    ;

    private final String message;
    private final String code;
    private final HttpStatus httpStatus;
}
