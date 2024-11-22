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
public class IncomeVO {

    private Integer id;
    private String occupationType;
    private String currentOccupation;
    private String drivingExperience;
    private String vehicleType;
    private String monthlyEarnings;
    private String extraSourceIncome;
    private String expIncomeEarnings;
    private String sourceIncomeRemarks;
    private String sourceTotalIncome;

}
