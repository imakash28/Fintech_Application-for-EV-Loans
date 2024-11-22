package com.kilpi.finayo.VO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kilpi.finayo.Domain.ExecutiveEntity;

import com.kilpi.finayo.Domain.LoanEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileVO {
	private Long profileId;

	@NotEmpty
    @Size(min = 3, max = 25, message = "name must be of min 3 length")
	private String name;

	@NotEmpty
	@Size(min = 3, max = 25, message = "name must be of min 3 length")
	private LocalDate dob;


	@NotEmpty
	@Size(min = 3, max = 25, message = "Father/Spouse name must be of min 3 length")
	private String fsName;

	@NotEmpty
	private String gender;


	@NotEmpty
	@Pattern(regexp = "^([1-9]){9}[0-9]$",message = "Invalid Mobile No.")
	private String phoneNo;

	@NotEmpty
	@Email
	private String email;

	private String pan;

	private Boolean isPanVerified;

	private String aadhar;

	private Boolean isAadharVerified;

	private String electricity;

	private Boolean isElectricityVerified;

	private String drivingLicence;

	private Boolean isDrivingLicenceVerified;

	private String cibil;

	private LoanVO loanVO;

}
