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
@Table(name = "loan_co_kyc")
public class COKYCEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "path")
    private String path;

    @ManyToOne(cascade = CascadeType.ALL)
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
