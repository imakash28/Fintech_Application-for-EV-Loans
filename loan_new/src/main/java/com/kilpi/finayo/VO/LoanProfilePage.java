package com.kilpi.finayo.VO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanProfilePage {

	@JsonProperty("error")
	public String error;

	@JsonProperty("response")
	public String response;

	@JsonProperty("loanSummary")
	public LoanSummary loanSummary;

	@JsonProperty("details")
	public ExceutiveProfieVO exceutiveProfieVO;

	@JsonProperty("profiles")
	List<ProfileVO> profileVOs;
	
	@JsonProperty("loancount")
	public LoanStatusCount loanStatusCount;
	
	

}
