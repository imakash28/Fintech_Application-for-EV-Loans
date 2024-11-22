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
@Table(name = "loan_courier_details")
public class CourierEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "partner")
    private String partner;

    @Column(name = "tracking_no")
    private String trackingNo;

    @Column(name = "address")
    private String address;

    @Column(name = "received_date")
    private String receivedDate;

    @Column(name = "proof")
    private String proof;

    @Column(name = "file_count")
    private String fileCount;

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
