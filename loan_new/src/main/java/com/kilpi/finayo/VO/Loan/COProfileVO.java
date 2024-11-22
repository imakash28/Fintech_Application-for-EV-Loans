package com.kilpi.finayo.VO.Loan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kilpi.finayo.Domain.LoanEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class COProfileVO {

    private Integer id;
    private String name;
    private LocalDate dob;
    private String fsName;
    private String gender;
    private String mobile;
    private String pan;
    private Boolean isPanVerified;
    private String aadhar;
    private Boolean isAadharVerified;
    private String electricity;
    private Boolean isElectricityVerified;
    private String drivingLicence;
    private Boolean isDrivingLicenceVerified;
    private String cibil;
}
