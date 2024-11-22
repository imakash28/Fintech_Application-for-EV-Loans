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
public class LoanStatsVO {
	
	private Long total;
	private Long disburse;
	private Long pending;
	private Long reject;
	private Long inProgress;
	private Long newLoan;
	private Long approve;
	private Long apply;

}
