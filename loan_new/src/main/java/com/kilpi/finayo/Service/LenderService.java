package com.kilpi.finayo.Service;

import com.kilpi.finayo.Constant.LoanStatus;
import com.kilpi.finayo.VO.*;

import java.util.List;

public interface LenderService {

	public LoanStatsVO getLenderStastistitics();

	public ReportVO lenderReport();

	List<LoanVO> getLoans();

	LoanProfilePage lenderProfilePage();
	public List<ProfileVO> profileListByBankStatus(LoanStatus loanStatus);

	List<LoanVO> getLoanBydate(String startDate,String endDate);
}
