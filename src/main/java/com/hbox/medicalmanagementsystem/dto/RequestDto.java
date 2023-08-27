package com.hbox.medicalmanagementsystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class RequestDto {
    private List<SearchRequestDto> searchRequestDto;

    private GlobalOperator globalOperator;
    private PageRequestDto pageRequestDto;

    public enum GlobalOperator{
        AND, OR;
    }
}
