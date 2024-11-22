package com.kilpi.finayo.Domain.Loan;

import javax.persistence.*;

import com.kilpi.finayo.Domain.LoanEntity;
import com.kilpi.finayo.VO.AssetVO;
import com.kilpi.finayo.VO.BankInfoVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loan_bank_info")
public class BankInfo {
	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer bankId;
    
	@Column(name = "loan_amount")
	private Long loanAmount;
	
	@Column(name = "tenure")
	private Integer tenure;
	
	@Column(name = "interest_rate")
	private Double interestRate;
	
	@Column(name = "account_no")
	private String accountNo;

	@Column(name = "ifsc_code")
	private String ifscCode;

	@Column(name = "bank_address")
	private String bankAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "loan_id", referencedColumnName = "id")
	private LoanEntity loan;
	
    public BankInfoVO toVo() {
        return BankInfoVO.builder()
        		.bankId(bankId)
        		.accountNo(accountNo)
        		.bankAddress(bankAddress)
        		.ifscCode(ifscCode)
        		.interestRate(interestRate)
        		.loanAmount(loanAmount)
        		.tenure(tenure)
				.build();
    }

}
