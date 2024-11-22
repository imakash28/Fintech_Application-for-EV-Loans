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
public class ReportVO {
	
	@JsonProperty("error")
	private String error;
	@JsonProperty("type")
	private String type;
	@JsonProperty("result")
	private ResultVO resultVO;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ResultVO getResultVO() {
		return resultVO;
	}
	public void setResultVO(ResultVO resultVO) {
		this.resultVO = resultVO;
	}
	
	

}
