package com.kilpi.finayo.Domain.Loan;

import com.kilpi.finayo.Domain.LoanEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "co_address_proof")
public class COAddressEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "residence_type")
    private String residenceType;

    @Column(name = "current_address")
    private String crAddress;

    @Column(name = "duration")
    private String duration;

    @Column(name = "permanent_address")
    private String prAddress;

    @Column(name = "relation")
    private String applicantRelation;

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
