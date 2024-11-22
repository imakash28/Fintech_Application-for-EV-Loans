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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loan_co_profile")
public class COProfileEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "fs_name")
    private String fsName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "pan")
    private String pan;

    @Column(name = "pan_verified")
    private Boolean isPanVerified;

    @Column(name = "aadhar")
    private String aadhar;

    @Column(name = "aadha_varified")
    private Boolean isAadharVerified;

    @Column(name = "electricity")
    private String electricity;

    @Column(name = "electricity_verified")
    private Boolean isElectricityVerified;

    @Column(name = "driving_licence")
    private String drivingLicence;

    @Column(name = "driving_licence_verified")
    private Boolean isDrivingLicenceVerified;

    @Column(name = "cibil")
    private String cibil;

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
