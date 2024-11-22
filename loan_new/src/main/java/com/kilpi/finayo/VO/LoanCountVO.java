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
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanCountVO {

	@JsonProperty("title")
	public List<String> title;
	@JsonProperty("Applied")
	public List<Object> applied;
	@JsonProperty("Pending")
	public List<Object> pending;
	@JsonProperty("Reject")
	public List<Object> reject;
	@JsonProperty("Disburse")
	public List<Object> disburse;
	
}
