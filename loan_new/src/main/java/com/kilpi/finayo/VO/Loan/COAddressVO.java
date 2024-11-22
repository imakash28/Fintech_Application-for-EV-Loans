package com.kilpi.finayo.VO.Loan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kilpi.finayo.Domain.LoanEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class COAddressVO {

    private Integer id;
    private String residenceType;
    private String crAddress;
    private String duration;
    private String prAddress;
    private String applicantRelation;

}
