package com.example.umc10th.global.dto;

import java.util.List;

// 공용 페이징 Dto
public record CursorPageResDto<T> (
        List<T> content,
        int currentPage,
        boolean hasNext
) {
    public static <T> CursorPageResDto<T> of(List<T> content, int currentPage, boolean hasNext) {
        return new CursorPageResDto<>(content, currentPage, hasNext);
    }
}
