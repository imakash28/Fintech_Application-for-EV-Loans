package com.kilpi.finayo.Domain;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kilpi.finayo.Constant.LoanStatus;
import com.kilpi.finayo.Domain.Loan.*;
import com.kilpi.finayo.VO.LoanVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loan_details")
public class LoanEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "asset_value")
	private Double assetValue;

	@Column(name = "loan_amount")
	private Double loanAmount;

	@Column(name = "down_payment")
	private Double downPayment;

	@Column(name = "status")
	private LoanStatus status;

	@Column(name = "file_created_date")
	private LocalDateTime fileCreatedDate;

	@Column(name = "file_closed_date")
	private LocalDateTime fileClosedDate;

	@Column(name = "loan_application_number")
	private String loanApplicationNumber;

	@Column(name = "loan_account_number")
	private String loanAccountNumber;

	@Column(name = "created_by", updatable=false)
	private String createBy;

	@CreationTimestamp
	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "updated_by")
	private String updateBy;

	@UpdateTimestamp
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;


	@OneToOne(fetch = FetchType.LAZY)
	private ProfileEntity profile;

	@OneToOne(fetch = FetchType.LAZY)
	private COProfileEntity coProfile;

	@OneToOne(fetch = FetchType.LAZY)
	private AddressEntity address;

	@OneToOne(fetch = FetchType.LAZY)
	private COAddressEntity coAddress;

	@OneToOne(fetch = FetchType.LAZY)
	private ApprovalDetailsEntity approval;

	@OneToOne(fetch = FetchType.LAZY)
	private AssetsEntity asset;

	@OneToOne(fetch = FetchType.LAZY)
	private IncomeEntity income;

	@OneToOne(fetch = FetchType.LAZY)
	private COIncomeEntity coIncome;

	@OneToOne(fetch = FetchType.LAZY)
	private DOEntity deliveryOrder;


	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "loan")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<KYCEntity> kycList = new LinkedList<>();

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "loan")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<COKYCEntity> KycList = new LinkedList<>();

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "loan")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CourierEntity> courier = new LinkedList<>();

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "loan")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<BankLoanEntity> banks = new LinkedList<>();


	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "executive_id", referencedColumnName = "id")
	private ExecutiveEntity executive;

	@OneToOne(fetch = FetchType.LAZY)
	private BankInfo bankInfo;

	@ManyToOne
	BranchEntity branch;
	
	public LoanVO toVo() {
        return LoanVO.builder()
				.build();
    }


}
