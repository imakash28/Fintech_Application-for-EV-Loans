package com.kilpi.finayo.Domain;

import com.kilpi.finayo.VO.BankLoanVO;
import lombok.*;

import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@EqualsAndHashCode(callSuper = false,exclude = {"bank","loan"})
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank_entity_loan")
@IdClass(BankLoanCK.class)
public class BankLoanEntity {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="bank_entity_id", referencedColumnName="id")
    @Getter
    @Setter
    private BankEntity bank;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="loan_id", referencedColumnName="id")
    @Getter
    @Setter
    private LoanEntity loan;

    @Column(name = "status")
    @Getter
    @Setter
    private String status;

    @Column(name = "interest_rate")
    @Getter
    @Setter
    private String interest;


    @Column(name = "reason")
    @Getter
    @Setter
    private String reason;



    public BankLoanVO toVo() {
        return BankLoanVO.builder()
                .loan(loan.toVo())
                .bank(bank.toVo())
                .build();
    }
}
