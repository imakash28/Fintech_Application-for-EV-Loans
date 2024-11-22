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
public class ResultVO {
	
	@JsonProperty("day1") 
    public List<Object> day1;
	
	@JsonProperty("day2") 
    public List<Object> day2;
	@JsonProperty("day3") 
    public List<Object> day3;
	@JsonProperty("day4") 
    public List<Object> day4;
	@JsonProperty("day5") 
    public List<Object> day5;
	@JsonProperty("day6") 
    public List<Object> day6;
	@JsonProperty("day7") 
    public List<Object> day7;
    
    
    @JsonProperty("Week1") 
    public List<Object> week1;
    @JsonProperty("Week2") 
    public List<Object> week2;
    @JsonProperty("Week3") 
    public List<Object> week3;
    @JsonProperty("Week4") 
    public List<Object> week4;
    @JsonProperty("Week5") 
    public List<Object> week5;
    
    @JsonProperty("Jan") 
    public List<Object> jan;
    @JsonProperty("Feb") 
    public List<Object> feb;
    @JsonProperty("Mar") 
    public List<Object> mar;
    @JsonProperty("Apr") 
    public List<Object> apr;
    @JsonProperty("May") 
    public List<Object> may;
    @JsonProperty("Jun") 
    public List<Object> jun;
    @JsonProperty("Jul") 
    public List<Object> jul;


	public Object days;
	public Object weeks;
	public Object months;

	public List<Object> getDay1() {
		return day1;
	}
	public void setDay1(List<Object> day1) {
		this.day1 = day1;
	}
	public List<Object> getDay2() {
		return day2;
	}
	public void setDay2(List<Object> day2) {
		this.day2 = day2;
	}
	public List<Object> getDay3() {
		return day3;
	}
	public void setDay3(List<Object> day3) {
		this.day3 = day3;
	}
	public List<Object> getDay4() {
		return day4;
	}
	public void setDay4(List<Object> day4) {
		this.day4 = day4;
	}
	public List<Object> getDay5() {
		return day5;
	}
	public void setDay5(List<Object> day5) {
		this.day5 = day5;
	}
	public List<Object> getDay6() {
		return day6;
	}
	public void setDay6(List<Object> day6) {
		this.day6 = day6;
	}
	public List<Object> getDay7() {
		return day7;
	}
	public void setDay7(List<Object> day7) {
		this.day7 = day7;
	}
	public List<Object> getWeek1() {
		return week1;
	}
	public void setWeek1(List<Object> week1) {
		this.week1 = week1;
	}
	public List<Object> getWeek2() {
		return week2;
	}
	public void setWeek2(List<Object> week2) {
		this.week2 = week2;
	}
	public List<Object> getWeek3() {
		return week3;
	}
	public void setWeek3(List<Object> week3) {
		this.week3 = week3;
	}
	public List<Object> getWeek4() {
		return week4;
	}
	public void setWeek4(List<Object> week4) {
		this.week4 = week4;
	}
	public List<Object> getWeek5() {
		return week5;
	}
	public void setWeek5(List<Object> week5) {
		this.week5 = week5;
	}
	public List<Object> getJan() {
		return jan;
	}
	public void setJan(List<Object> jan) {
		this.jan = jan;
	}
	public List<Object> getFeb() {
		return feb;
	}
	public void setFeb(List<Object> feb) {
		this.feb = feb;
	}
	public List<Object> getMar() {
		return mar;
	}
	public void setMar(List<Object> mar) {
		this.mar = mar;
	}
	public List<Object> getApr() {
		return apr;
	}
	public void setApr(List<Object> apr) {
		this.apr = apr;
	}
	public List<Object> getMay() {
		return may;
	}
	public void setMay(List<Object> may) {
		this.may = may;
	}
	public List<Object> getJun() {
		return jun;
	}
	public void setJun(List<Object> jun) {
		this.jun = jun;
	}
	public List<Object> getJul() {
		return jul;
	}
	public void setJul(List<Object> jul) {
		this.jul = jul;
	}

}
