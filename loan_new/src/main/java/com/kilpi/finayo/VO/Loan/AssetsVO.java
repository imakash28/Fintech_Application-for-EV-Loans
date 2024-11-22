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
public class AssetsVO {

    private Integer id;
    private String vehicleType;
    private String manufacturerName;
    private String manufacturingYear;
    private String manufacturerWarranty;
    private String chasisNo;
    private String batteryType;
    private String batteryNumber;
    private String batteryManufacturerName;
    private String batteryManufacturingYear;
    private String batteryManufacturerWarranty;
    private String batteryManufacturerMaintenanceType;

}
