package com.kilpi.finayo.VO;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardVO {
	@JsonProperty("Statistics")
	public StatisticsVO statistics;
	@JsonProperty("BarWeekly")
	public Object barWeeklyVO;
	@JsonProperty("DailyDisburse")
	public List<DailyDisburseVO> dailyDisburse;
	@JsonProperty("OveralDisburse")
	public List<OveralDisburseVO> overalDisburse;
	@JsonProperty("ShowroomList")
	public List<ShowroomListVO> showroomListVO;
}
