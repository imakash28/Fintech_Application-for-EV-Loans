package com.kilpi.finayo.VO;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankInfoVO {
	
    private Integer bankId;
  
	private Long loanAmount;
	

	private Integer tenure;
	
	private Double interestRate;
	
	private String accountNo;

	private String ifscCode;

	private String bankAddress;

}
