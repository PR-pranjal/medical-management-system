package com.hbox.medicalmanagementsystem.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
public class PageRequestDto {
    private Integer pageNo;
    private Integer pageSize;
    private Sort.Direction sort;
    private String sortByColumn;


        public Pageable getPageable(PageRequestDto pageRequestDto){
            this.pageSize= pageRequestDto.getPageSize();
            this.pageNo=pageRequestDto.getPageNo();
            this.sort=pageRequestDto.getSort();
            this.sortByColumn=pageRequestDto.getSortByColumn();
            Sort sortObject = Sort.by(this.sort, this.sortByColumn);
            return PageRequest.of(this.pageNo, this.pageSize, sortObject);
        }

    }

