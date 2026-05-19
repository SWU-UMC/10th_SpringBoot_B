package com.example.umc10th.global.apiPayload.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDTO<T> {
    private List<T> content;
    private Integer page;
    private Integer size;
    private Boolean hasNext;
}
