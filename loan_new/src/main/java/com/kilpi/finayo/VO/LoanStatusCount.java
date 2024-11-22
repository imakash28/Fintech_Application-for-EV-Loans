package com.kilpi.finayo.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanStatusCount {

	@JsonProperty("new")
	public Integer newLoan;
	@JsonProperty("apply")
	public Integer apply;
	@JsonProperty("inprogress")
	public Integer inprogress;
	@JsonProperty("hold")
	public Integer hold;
	@JsonProperty("reject")
	public Integer reject;
	@JsonProperty("approve")
	public Integer approve;
	@JsonProperty("disburse")
	public Integer disburse;

}
