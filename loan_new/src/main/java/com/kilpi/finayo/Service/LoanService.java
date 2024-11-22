package com.kilpi.finayo.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.kilpi.finayo.Constant.LoanStatus;
import com.kilpi.finayo.Constant.ReportType;
import com.kilpi.finayo.VO.*;

public interface LoanService {

	public LoanVO saveLoan(LoanVO pLoanVO);

	public LoanStatsVO getLoanStastistitics();

	public ReportVO loanReportByDays(ReportType pReportType);

	public BranchLoanStatusVO loanStatusForBranch(LoanStatus loanStatus, String pCatagory);

	public ExcecutiveLoanCountVO loanStatuByExecutive(String executiveName, Integer executiveId);

	public DashboardVO displayDashboard();

	public Long applyloanSummary();

	public Long approvedApplication();

	public List<ProfileVO> profileListByExecutiveStatus(LoanStatus loanStatus);

	public List<ProfileVO> profileListByExecutiveStatus(LoanStatus loanStatus, Integer id);

	public long countLoanByBranchName(String brachName,LoanStatus pStatus);

	public long countLoanByBranchId(Integer brachName,LoanStatus pStatus);

	public ExceutiveProfieVO getExceutiveProfieVO(Integer pExceutiveId);

	public ExceutiveProfieVO getBranchProfieVO(Integer pBranchId);

	public List<ProfileVO> getProfileByBranch(Integer pBranchId,LoanStatus loanStatus);

	public LoanProfilePage loanProfilePage();

	public LoanProfilePage executiveProfilePage(Integer pExceutiveId);

	public LoanProfilePage branchProfilePage(Integer pBranchId);

	public ProfileVO updateLoanStatus(StatusVO statusVo);

	public LoanProfilePage loanDailyStatus(Integer pExecutiveId, LocalDateTime startDate,
			LocalDateTime endDate);

	public LoanProfilePage executiveDailyStatusReport(Integer pExecutiveId, LocalDateTime startDate,
			LocalDateTime endDate);

	public LoanProfilePage branchDailyStatusReport(Integer pBranchId, LocalDateTime startDate,
			LocalDateTime endDate);

	public ResultVO gettingYearlyReportLender();

	public ResultVO gettingYearlyReportAdmin();

	public List<LoanVO> getLoanStatusAdmin(LoanStatus status);

	public List<LoanVO> getbranchLoanAdmin(Integer branchId);

	public List<LoanVO> getExecutiveLoanAdmin(Integer executiveId);

	public List<LoanVO> getLoanDateAdmin(String startDate,
										 String endDate);

	public List<LoanVO> getLoansByStatus(LoanStatus status);

	public List<LoanVO> getLoanByDate(String startDate,
										 String endDate);

	public List<LoanVO> getLoansByDSA(Integer id);

}
