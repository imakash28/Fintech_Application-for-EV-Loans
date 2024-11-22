package com.kilpi.finayo.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kilpi.finayo.VO.BankVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_entity")
public class BankEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "bank_name")
    private String bankname;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "ifsc_code")
    private String ifscCode;

    @Column(name = "accepted_cibil")
    private Double acptCibil;

    @Column(name = "email")
    String email;

    @Column(name = "mobile")
    String mobile;

    @Column(name = "address")
    String address;

    @Column(name = "city")
    String city;

    @Column(name = "code")
    String code;

    @Column(name = "status")
    Boolean active;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bank")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<BankLoanEntity> loans = new LinkedList<>();

    public BankVO toVo() {
        return BankVO.builder()
                .bankname(bankname)
                .ifscCode(ifscCode)
                .address(address)
                .interestRate(interestRate)
                .code(code)
                .email(email)
                .build();
    }

    public BankVO toBankVo(BankLoanEntity bankLoan) {
        return BankVO.builder()
                .bankname(bankname)
                .ifscCode(ifscCode)
                .address(address)
                .interestRate(interestRate)
                .code(code)
                .email(email)
                .status(bankLoan.getStatus())
                .build();
    }


}
