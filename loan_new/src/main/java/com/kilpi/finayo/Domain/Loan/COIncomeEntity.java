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
@Table(name = "co_income_details")
public class COIncomeEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "occupation_type")
    private String occupationType;

    @Column(name = "current_occupation")
    private String currentOccupation;

    @Column(name = "driving_experience")
    private String drivingExperience;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "monthly_earnings")
    private String monthlyEarnings;

    @Column(name = "extra_source_income")
    private String extraSourceIncome;

    @Column(name = "exp_monthly_earnings")
    private String expIncomeEarnings;

    @Column(name = "source_income_remarks")
    private String sourceIncomeRemarks;

    @Column(name = "source_total_income")
    private String sourceTotalIncome;

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
