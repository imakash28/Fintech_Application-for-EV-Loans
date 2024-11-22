package com.kilpi.finayo.VO;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceutiveProfieVO {
	
	private String name;
	private String mail;
	private String phone;
	private String showroomName;
	private String branchName;
	private String branchId;
	private String branchAddress;
	private String branchContact;
	private String branchCity;
	
}
