package com.kilpi.finayo.VO;

import java.util.List;

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
public class BarWeeklyVO {
	public List<Object> day1;
	public List<Object> day2;
	public List<Object> day3;
	public List<Object> day4;
	public List<Object> day5;
	public List<Object> day6;
	public List<Object> day7;
}
