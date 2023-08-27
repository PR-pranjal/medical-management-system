package com.hbox.medicalmanagementsystem.service;

import com.hbox.medicalmanagementsystem.dto.RequestDto;
import com.hbox.medicalmanagementsystem.dto.SearchRequestDto;
import com.hbox.medicalmanagementsystem.entity.Prescription;
import com.hbox.medicalmanagementsystem.repository.PrescriptionRepository;
import com.hbox.medicalmanagementsystem.response.PrescriptionResponse;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpecificationServiceImpl<T> implements SpecificationService<T> {

    private final PrescriptionRepository prescriptionRepository;

    public SpecificationServiceImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDto, RequestDto.GlobalOperator globalOperator) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (SearchRequestDto requestDto : searchRequestDto) {
                String[] columnPath = requestDto.getColumn().split("\\.");

                Expression<?> attributeExpression;
                int end = columnPath.length - 1;
                int start = 0;
                String attributeName = columnPath[end];

                if (columnPath.length > 1) {
                    Join<?, ?> joinedTable = root.join(columnPath[start], JoinType.LEFT);
                    start++;
                    while (start < end) {
                        joinedTable = joinedTable.join(columnPath[start], JoinType.LEFT);
                        start++;
                    }
                    attributeExpression = joinedTable.get(columnPath[end]);
                } else {
                    attributeExpression = root.get(attributeName);
                }

                switch (requestDto.getOperation()) {
                    case EQUAL:
                        Predicate equal = criteriaBuilder.equal(attributeExpression, requestDto.getValue());
                        predicates.add(equal);
                        break;


                    case LIKE:
                        Predicate like = criteriaBuilder.like(attributeExpression.as(String.class), "%" + requestDto.getValue() + "%");
                        predicates.add(like);
                        break;


                    case IN:
                        String[] inValues = requestDto.getValue().split(",");
                        Predicate in = attributeExpression.in((Object[]) inValues);
                        predicates.add(in);
                        break;

                    case GREATER_THAN_OR_EQUAL:
                        Predicate greaterThanOrEqual = criteriaBuilder.greaterThanOrEqualTo(attributeExpression.as(LocalDateTime.class), LocalDateTime.parse(requestDto.getValue(), DateTimeFormatter.ISO_DATE_TIME));
                        predicates.add(greaterThanOrEqual);
                        break;

                    case LESS_THAN_OR_EQUAL:
                        Predicate lessThanOrEqual = criteriaBuilder.lessThanOrEqualTo(attributeExpression.as(LocalDateTime.class), LocalDateTime.parse(requestDto.getValue(), DateTimeFormatter.ISO_DATE_TIME));
                        predicates.add(lessThanOrEqual);
                        break;


                    default:
                        throw new IllegalStateException("Unexpected value: " + requestDto.getOperation());
                }
            }

            Predicate[] predicateArray = predicates.toArray(new Predicate[0]);

            if (globalOperator.equals(RequestDto.GlobalOperator.AND)) {
                return criteriaBuilder.and(predicateArray);
            } else {
                return criteriaBuilder.or(predicateArray);
            }
        };
    }

    @Override
    public Page<PrescriptionResponse> getPrescriptionsUsingSpecificationAndPagination(
            Specification<Prescription> specification, Pageable pageable) {
        Page<Prescription> prescriptionsPage = prescriptionRepository.findAll(specification, pageable);
        List<PrescriptionResponse> prescriptionResponses = new ArrayList<>();
        for (Prescription prescription : prescriptionsPage.getContent()) {
            PrescriptionResponse response = mapToPrescriptionResponse(prescription);
            prescriptionResponses.add(response);
        }
        return PageableExecutionUtils.getPage(
                prescriptionResponses, pageable,
                () -> prescriptionRepository.count(specification));

    }

    private PrescriptionResponse mapToPrescriptionResponse(Prescription prescription) {
        PrescriptionResponse response = new PrescriptionResponse();
        response.setPrescriptionId(prescription.getPrescriptionId());
        response.setPatientFullName(prescription.getPatient().getFirstName() + " " + prescription.getPatient().getLastName());
        response.setDoctorFullName(prescription.getDoctor().getFirstName() + " " + prescription.getDoctor().getLastName());
        response.setClinicName(prescription.getDoctor().getClinic().getName());
        response.setCreatedDateTime(prescription.getCreatedDateTime());
        response.setCause(prescription.getCause());
        response.setPatientEmrNumber(prescription.getPatient().getEmrNumber());
        response.setNotes(prescription.getNotes());
        return response;
    }
}
