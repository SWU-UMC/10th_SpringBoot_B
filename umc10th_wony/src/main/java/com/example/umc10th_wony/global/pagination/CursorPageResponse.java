package com.example.umc10th_wony.global.pagination;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CursorPageResponse<T> {

    private List<T> content;

    // 다음 페이지 존재 여부
    private Boolean hasNext;

    // 다음 요청 때 사용할 cursor
    private String nextCursor;

    // 현재 데이터 개수
    private Integer size;
}
