package com.hbox.medicalmanagementsystem.service;

import com.hbox.medicalmanagementsystem.dto.RequestDto;
import com.hbox.medicalmanagementsystem.dto.SearchRequestDto;
import com.hbox.medicalmanagementsystem.entity.Prescription;
import com.hbox.medicalmanagementsystem.response.PrescriptionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface SpecificationService<T> {
    public Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDto, RequestDto.GlobalOperator globalOperator);

    public Page<PrescriptionResponse> getPrescriptionsUsingSpecificationAndPagination(
            Specification<Prescription> specification, Pageable pageable);
}
