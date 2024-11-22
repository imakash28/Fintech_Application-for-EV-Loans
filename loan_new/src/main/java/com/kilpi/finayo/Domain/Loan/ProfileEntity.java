package com.kilpi.finayo.Domain.Loan;

import com.kilpi.finayo.Domain.ExecutiveEntity;
import com.kilpi.finayo.Domain.LoanEntity;
import com.kilpi.finayo.VO.LoanVO;
import com.kilpi.finayo.VO.ProfileVO;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "loan_profile_details")
public class ProfileEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "dob")
	private LocalDate dob;

	@Column(name = "fs_name")
	private String fsName;

	@Column(name = "gender")
	private String gender;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "pan")
	private String pan;

	@Column(name = "pan_verified")
	private Boolean isPanVerified;

	@Column(name = "aadhar")
	private String aadhar;

	@Column(name = "aadha_varified")
	private Boolean isAadharVerified;

	@Column(name = "electricity")
	private String electricity;

	@Column(name = "electricity_verified")
	private Boolean isElectricityVerified;

	@Column(name = "driving_licence")
	private String drivingLicence;

	@Column(name = "driving_licence_verified")
	private Boolean isDrivingLicenceVerified;

	@Column(name = "cibil")
	private String cibil;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "loan_id", referencedColumnName = "id")
	private LoanEntity loan;

	@Column(name = "created_by")
	private String createBy;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "updated_by")
	private String updateBy;

	@Column(name = "updated_date")
	private LocalDateTime updatedDate;

	public ProfileVO toVo() {
		return ProfileVO.builder()
				.profileId(id)
				.name(name)
				.dob(dob)
				.loanVO(loan.toVo())
				.build();
	}
}
