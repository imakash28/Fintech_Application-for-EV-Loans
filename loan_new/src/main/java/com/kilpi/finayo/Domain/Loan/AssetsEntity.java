package com.kilpi.finayo.Domain.Loan;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kilpi.finayo.Domain.BankLoanEntity;
import com.kilpi.finayo.Domain.LoanEntity;
import com.kilpi.finayo.VO.BankVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loan_asset_info")
public class AssetsEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "asset_type")
    private String assetType;

    @Column(name = "name")
    private String name;

    @Column(name = "year_od_manufacturing")
    private String year;

    @Column(name = "warranty")
    private String warranty;

    @Column(name = "chasis_no")
    private String chasisNo;

    @Column(name = "battery_type")
    private String batteryType;

    @Column(name = "battery_number")
    private String batteryNumber;

    @Column(name = "battery_manufacture_name")
    private String bmName;

    @Column(name = "battery_manufacture_year")
    private String bmYear;

    @Column(name = "battery_manufacture_warranty")
    private String bmWarranty;

    @Column(name = "battery_maintenance_type")
    private String bmMaintenanceType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private LoanEntity loan;

    @Column(name = "created_by")
    private String createBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_by")
    private String updateBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

}
