package com.kilpi.finayo.VO;

import com.kilpi.finayo.VO.Loan.ProfileVO;
import com.kilpi.finayo.VO.Loan.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoanVO {

	private Long id;
	private Double assetValue;
	private Double loanAmount;
	private Double downPayment;
	private ProfileVO profile;
	private COProfileVO coProfile;
	private AddressVO address;
	private COAddressVO coAddress;
	private AssetsVO asset;
	private IncomeVO income;
	private COIncomeVO coIncome;
	private DOVO deliveryOrder;
	private KYCVO kyc;
	private COKYCVO coKyc;
	private CourierVO courier;
}

