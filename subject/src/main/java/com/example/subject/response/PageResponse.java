package com.example.subject.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @param <T>
 *
 * T타입 전체 목록 조회 응답 객체
 */
@Data
@AllArgsConstructor
public class PageResponse<T> {

    int recordCount;
    T response;
}
