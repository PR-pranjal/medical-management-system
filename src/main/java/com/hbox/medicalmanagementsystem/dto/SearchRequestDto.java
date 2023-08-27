package com.hbox.medicalmanagementsystem.dto;

import lombok.Data;

@Data
public class SearchRequestDto {
    String column;
    String value;
    Operation operation;
    String joinTable;


    public enum Operation{
        EQUAL, LIKE, IN, GREATER_THAN_OR_EQUAL, LESS_THAN_OR_EQUAL;
    }
}
