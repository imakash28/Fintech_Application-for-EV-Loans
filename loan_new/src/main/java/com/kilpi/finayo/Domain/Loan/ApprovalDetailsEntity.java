package com.kilpi.finayo.Domain.Loan;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kilpi.finayo.Domain.BankEntity;
import com.kilpi.finayo.Domain.BankLoanEntity;
import com.kilpi.finayo.Domain.ExecutiveEntity;
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
@Table(name = "loan_approval_details")
public class ApprovalDetailsEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lander_id", referencedColumnName = "id")
    private BankEntity lender;

    @Column(name = "emi")
    private Double emi;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "approved_amount")
    private Double approvedAmount;

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
