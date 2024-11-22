package com.kilpi.finayo.Service.Impl;

import com.kilpi.finayo.Constant.Constant;
import com.kilpi.finayo.Constant.LoanStatus;
import com.kilpi.finayo.Constant.ReportType;
import com.kilpi.finayo.Domain.*;
import com.kilpi.finayo.Repository.*;
import com.kilpi.finayo.Service.LenderService;
import com.kilpi.finayo.Service.LoanService;
import com.kilpi.finayo.Service.UserService;
import com.kilpi.finayo.VO.*;
import com.kilpi.finayo.exception.NotFoundException;
import com.kilpi.finayo.mapper.DtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
<<<<<<< HEAD
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
=======
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
>>>>>>> 83b38cd (Chanages in serviceImpl by Akash)
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

	Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);

	@Autowired
	BankRepository bankRepository;

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private ExecutiveRepository executiveRepository;
	
	@Autowired
	private AssetRepository assetRepository;
	
	@Autowired
	private BankInfoRepository bankInfoRepository;

	@Autowired
	private DtoConverter dtoConverter;

	@Autowired
	private UserService userService;

	@Autowired
	private LenderService lenderService;

	@Override
	@Transactional
	public LoanVO saveLoan(LoanVO pLoanVO) {
		ExecutiveEntity executive = userService.getExecutive();
		LoanEntity loanEntity = dtoConverter.loanVotoEntity(pLoanVO);
		loanEntity.setExecutive(executive);
		loanEntity.setCreateBy(executive.getEmail());
		loanEntity.setUpdateBy(executive.getEmail());
		return loanRepository.save(loanEntity).toVo();
	}

	@Override
	public LoanStatsVO getLoanStastistitics() {
		LoanStatsVO loanStatsVO = null;
		loanStatsVO = new LoanStatsVO();
		loanStatsVO.setTotal(loanRepository.sumTotalLoan() == null ? 0 : loanRepository.sumTotalLoan());
		loanStatsVO.setDisburse(loanRepository.getSummaryByStatus(Constant.DISBURS) == null ? 0
				: loanRepository.getSummaryByStatus(Constant.DISBURS));
		loanStatsVO.setPending(loanRepository.getSummaryByStatus(Constant.APPLY) == null ? 0
				: loanRepository.getSummaryByStatus(Constant.APPLY));
		loanStatsVO.setReject(loanRepository.getSummaryByStatus(Constant.REJECT) == null ? 0
				: loanRepository.getSummaryByStatus(Constant.REJECT));
		return loanStatsVO;
	}

	@Override
	public ReportVO loanReportByDays(ReportType pReportType) {
		ReportVO reportVO = new ReportVO();
		if (pReportType.equals(ReportType.DAILY)) {
			reportVO.setError(""); // TODO: Need to implemeted
			reportVO.setType(ReportType.DAILY.toString());
			ResultVO resultVO = gettingWeeklyReportExecutive();
			reportVO.setResultVO(resultVO);
		}
		if (pReportType.equals(ReportType.WEEKLY)) {
			reportVO.setError(""); // TODO: Need to implemeted
			reportVO.setType(ReportType.WEEKLY.toString());
			ResultVO resultVO = gettingMonthlyReportExecutive();
			reportVO.setResultVO(resultVO);
		}
		if (pReportType.equals(ReportType.MONTHLY)) {
			reportVO.setError(""); // TODO: Need to implemeted
			reportVO.setType(ReportType.MONTHLY.toString());
			ResultVO resultVO = gettingYearlyReportExecutive();
			reportVO.setResultVO(resultVO);
		}

		return reportVO;
<<<<<<< HEAD
=======

	}

	private ResultVO gettingYearlyReportExecutive() {
		ResultVO resultVO = new ResultVO();

		LocalDate startDate = LocalDate.now().minusMonths(5).withDayOfMonth(1);
		LocalDate lastData = LocalDate.now();
		Integer month = startDate.getMonthValue();
		List<Object> months = new ArrayList<>();
		do {

			month = startDate.getMonthValue();
			LocalDate endDate = startDate.plusDays(startDate.lengthOfMonth() - 1);

			Map<String, List<Object>> mon = new HashMap<>();
			List<Object> data = new ArrayList<>();
			data.add(startDate.getMonth().toString().substring(0, 3));
			data.addAll(extractedByExecutive(startDate.atTime(LocalTime.MIN), endDate.atTime(LocalTime.MAX)));

			months.add(data);

			startDate = startDate.plusMonths(1);
			if (startDate.isAfter(lastData)) {
				startDate = lastData;
			}

		} while (month != lastData.getMonthValue());
		resultVO.setMonths(months);
		return resultVO;
	}

	private ResultVO gettingMonthlyReportExecutive() {
		ResultVO resultVO = new ResultVO();

		LocalDate startDate = LocalDate.now().minusWeeks(5).with(DayOfWeek.SUNDAY);
		LocalDate lastData = LocalDate.now();
		Integer week = 1;

		List<Object> weeks = new ArrayList<>();
		while (week <= 5) {
			LocalDate endDate = startDate.plusWeeks(1).minusDays(1);

			Map<String, List<Object>> mon = new HashMap<>();
			List<Object> data = new ArrayList<>();
			data.add(startDate.getDayOfMonth() + "/" + startDate.getMonthValue() + " - " + endDate.getDayOfMonth() + "/"
					+ endDate.getMonthValue());
			data.addAll(extractedByExecutive(startDate.atTime(LocalTime.MIN), endDate.atTime(LocalTime.MAX)));
			weeks.add(data);

			startDate = startDate.plusWeeks(1);
			if (startDate.isAfter(lastData)) {
				startDate = lastData;
			}
			week++;

		}
		resultVO.setWeeks(weeks);
		return resultVO;
	}

	private ResultVO gettingWeeklyReportExecutive() {
		ResultVO resultVO = new ResultVO();
		LocalDate startDate = LocalDate.now().minusDays(6);
		LocalDate lastData = LocalDate.now();
		Integer day = 1;

		List<Object> days = new ArrayList<>();
		while (day <= 7) {

			Map<String, List<Object>> mon = new HashMap<>();
			List<Object> data = new ArrayList<>();
			data.add(startDate.getDayOfWeek().toString().substring(0, 3));
			data.addAll(extractedByExecutive(startDate.atTime(LocalTime.MIN), startDate.atTime(LocalTime.MAX)));

			days.add(data);

			startDate = startDate.plusDays(1);
			day++;

		}
		resultVO.setDays(days);
		return resultVO;
	}

	private List<Object> extractedByExecutive(LocalDateTime startDate, LocalDateTime endDate) {
		List<Object> day1 = new ArrayList<Object>();
		ExecutiveEntity executive = userService.getExecutive();
		day1.add(loanRepository.getLoanReportByStatusDateAndExecutive(startDate, endDate, Constant.NEW,
				executive.getId()));
		day1.add(loanRepository.getLoanReportByStatusDateAndExecutive(startDate, endDate, Constant.APPLY,
				executive.getId()));
		day1.add(loanRepository.getLoanReportByStatusDateAndExecutive(startDate, endDate, Constant.INPROGRESS,
				executive.getId()));
		day1.add(loanRepository.getLoanReportByStatusDateAndExecutive(startDate, endDate, Constant.REJECT,
				executive.getId()));
		day1.add(loanRepository.getLoanReportByStatusDateAndExecutive(startDate, endDate, Constant.DISBURS,
				executive.getId()));
		return day1;
>>>>>>> 83b38cd (Chanages in serviceImpl by Akash)
	}

	@Override
	public BranchLoanStatusVO loanStatusForBranch(LoanStatus loanStatus, String pCatagory) {
		BranchLoanStatusVO branchLoanStatusVO = new BranchLoanStatusVO();
		branchLoanStatusVO.setError("");
		branchLoanStatusVO.setType(loanStatus.toString().toLowerCase());
		branchLoanStatusVO.setCategory(pCatagory);
		List<Object[]> objects = new ArrayList<Object[]>();
		if (pCatagory.equalsIgnoreCase("all")) {
			if(loanStatus.equals(LoanStatus.APPROVE)) {
				objects = loanRepository.getApproveStatusByBranch(loanStatus.toString());
			}
			else {
				objects = loanRepository.getStatusByBranch(loanStatus);
			}
		} else if (pCatagory.equalsIgnoreCase("daily")) {
			if(loanStatus.equals(LoanStatus.APPROVE)) {
				objects = loanRepository.getDailyAPPROVEStatusByBranch(loanStatus.toString(), LocalDate.now().atTime(0, 0, 0),
						LocalDate.now().plusDays(1).atTime(0, 0, 0));
			}
			else {
				objects = loanRepository.getDailyStatusByBranch(loanStatus, LocalDate.now().atTime(0, 0, 0),
						LocalDate.now().plusDays(1).atTime(0, 0, 0));
			}
		}
		List<BranchLoanCountVO> branchLoanCountList = new ArrayList<>();
		for (Object[] object : objects) {
			if (object != null) {
				BranchLoanCountVO branchLoanCountVO = new BranchLoanCountVO();
				branchLoanCountVO.setCnt(Integer.valueOf(object[0].toString()));
				branchLoanCountVO.setName(object[1].toString());
				branchLoanCountList.add(branchLoanCountVO);
			}
		}
		/*
		 * //TODO for Empty data if (objects != null & objects.isEmpty()) {
		 * BranchLoanCountVO branchLoanCountVO = new BranchLoanCountVO();
		 * branchLoanCountVO.setCnt(0); branchLoanCountVO.setName("");
		 * branchLoanCountList.add(branchLoanCountVO); }
		 */
		branchLoanStatusVO.setResult(branchLoanCountList);
		return branchLoanStatusVO;
	}

	@Override
	public ExcecutiveLoanCountVO loanStatuByExecutive(String executiveName, Integer executiveId) {
		ExcecutiveLoanCountVO excecutiveLoanCountVO = new ExcecutiveLoanCountVO();
		excecutiveLoanCountVO.setError("");
		excecutiveLoanCountVO.setType("year");
		List<LoanCountVO> loanCountVOList = new ArrayList<LoanCountVO>();
		List<Object[]> objects = new ArrayList<Object[]>();
		if (executiveName != null) {
			objects = loanRepository.getCountByExecutiveName(executiveName);
		} else if (executiveId != null) {
			objects = loanRepository.getCountByExecutiveId(executiveId);
		} else {
			objects = loanRepository.getCountAllExecutive();
		}
		for (Object[] object : objects) {
			if (object != null) {
				LoanCountVO loanCountVO = new LoanCountVO();
				List<String> titleList = new ArrayList<>();
				titleList.add("Task");
				titleList.add("Hours per Day");
				loanCountVO.setTitle(titleList);

				List<Object> applied = new ArrayList<>();
				applied.add("Applied");
				applied.add(object[0] != null ? Integer.valueOf(object[0].toString()) : 0);
				loanCountVO.setApplied(applied);

				List<Object> pending = new ArrayList<>();
				pending.add("Pending");
				pending.add(object[1] != null ? Integer.valueOf(object[1].toString()) : 0);
				loanCountVO.setPending(pending);

				List<Object> rejected = new ArrayList<>();
				rejected.add("Reject");
				rejected.add(object[2] != null ? Integer.valueOf(object[2].toString()) : 0);
				loanCountVO.setReject(rejected);

				List<Object> dishburshed = new ArrayList<>();
				dishburshed.add("Disburse");
				dishburshed.add(object[3] != null ? Integer.valueOf(object[3].toString()) : 0);
				loanCountVO.setDisburse(dishburshed);

				loanCountVOList.add(loanCountVO);
			}
		}
		if (objects != null & objects.isEmpty()) {
			LoanCountVO loanCountVO = new LoanCountVO();
			List<String> titleList = new ArrayList<>();
			titleList.add("Task");
			titleList.add("Hours per Day");
			loanCountVO.setTitle(titleList);
			List<Object> applied = new ArrayList<>();
			applied.add("Applied");
			applied.add(0);
			loanCountVO.setApplied(applied);
			List<Object> pending = new ArrayList<>();
			pending.add("Pending");
			pending.add(0);
			loanCountVO.setPending(pending);
			List<Object> rejected = new ArrayList<>();
			rejected.add("Reject");
			rejected.add(0);
			loanCountVO.setReject(rejected);
			List<Object> disburse = new ArrayList<>();
			disburse.add("Disburse");
			disburse.add(0);
			loanCountVO.setDisburse(disburse);
			loanCountVOList.add(loanCountVO);
		}
		excecutiveLoanCountVO.setResult(loanCountVOList);
		return excecutiveLoanCountVO;
	}

	@Override
	public DashboardVO displayDashboard() {
		DashboardVO dashboardVO = new DashboardVO();
		StatisticsVO statisticsVO = new StatisticsVO();
		LoanStatsVO lLoanStatsVO = getLoanStastistitics();
		statisticsVO.setTotal(lLoanStatsVO.getTotal());
		statisticsVO.setApprove(lLoanStatsVO.getPending());
		statisticsVO.setReject(lLoanStatsVO.getReject());
		statisticsVO.setDisbursed(lLoanStatsVO.getDisburse());
		dashboardVO.setStatistics(statisticsVO);
		ResultVO resultVO = gettingWeeklyReport();
		dashboardVO.setBarWeeklyVO(resultVO.getDays());

		LoanStatus loanStatus = LoanStatus.DISBURSE;
		List<BranchLoanCountVO> branchLoanCountList = loanStatusForBranch(loanStatus, "daily").getResult();
		List<DailyDisburseVO> dailyDishburshedVO = new ArrayList();
		for (BranchLoanCountVO branchLoanCountVO : branchLoanCountList) {
			DailyDisburseVO dailyDishburshed = new DailyDisburseVO();
			dailyDishburshed.setName(branchLoanCountVO.getName());
			dailyDishburshed.setCnt(branchLoanCountVO.getCnt());
			dailyDishburshedVO.add(dailyDishburshed);
		}
		dashboardVO.setDailyDisburse(dailyDishburshedVO);

		List<BranchLoanCountVO> allLoanCountList = loanStatusForBranch(loanStatus, "all").getResult();
		List<OveralDisburseVO> overalDishburshedVO = new ArrayList<OveralDisburseVO>();
		for (BranchLoanCountVO branchLoanCountVO : allLoanCountList) {
			OveralDisburseVO overalDishburshed = new OveralDisburseVO();
			overalDishburshed.setName(branchLoanCountVO.getName());
			overalDishburshed.setCnt(branchLoanCountVO.getCnt());
			overalDishburshedVO.add(overalDishburshed);
		}
		dashboardVO.setOveralDisburse(overalDishburshedVO);

		List<ShowroomListVO> showroomListVO = new ArrayList<ShowroomListVO>(); // TODO
		ShowroomListVO showroom = new ShowroomListVO();
		showroom.setName("Dummy ShowRoom");
		showroom.setEmail("dummy@gmail.com");
		showroom.setEmployee(23);
		showroom.setPhone("+918790320821");
		showroom.setAddress("K Road,Pune");
		showroomListVO.add(showroom);
		ShowroomListVO showroom2 = new ShowroomListVO();
		showroom.setName("Dummy ShowRoom2");
		showroom.setEmail("dummy2@gmail.com");
		showroom.setEmployee(21);
		showroom.setPhone("+917777320821");
		showroom.setAddress("p Road,Pune");
		showroomListVO.add(showroom2);
		dashboardVO.setShowroomListVO(showroomListVO);

		return dashboardVO;
	}

	private ResultVO gettingWeeklyReport() {
		ResultVO resultVO = new ResultVO();

		LocalDate startDate = LocalDate.now().minusDays(6);
		LocalDate lastData = LocalDate.now();
		Integer day = 1;

		List<Object> days = new ArrayList<>();
		while (day <= 7) {

			Map<String, List<Object>> mon = new HashMap<>();
			List<Object> data = new ArrayList<>();
			data.add(startDate.getDayOfWeek().toString().substring(0, 3));
			data.addAll(extracted(startDate.atTime(LocalTime.MIN), startDate.atTime(LocalTime.MAX)));

			days.add(data);

			startDate = startDate.plusDays(1);
			day++;

		}
		resultVO.setDays(days);
		return resultVO;
	}

	private List<Object> extracted(LocalDateTime startDate, LocalDateTime endDate) {
		List<Object> day1 = new ArrayList<Object>();
		day1.add(loanRepository.getLoanReportByStatusDate(startDate, endDate, Constant.NEW));
		day1.add(loanRepository.getLoanReportByStatusDate(startDate, endDate, Constant.APPLY));
		day1.add(loanRepository.getLoanReportByStatusDate(startDate, endDate, Constant.INPROGRESS));
		day1.add(loanRepository.getLoanReportByStatusDate(startDate, endDate, Constant.REJECT));
		day1.add(loanRepository.getLoanReportByStatusDate(startDate, endDate, Constant.DISBURS));
		return day1;
	}


	@Override
	public Long applyloanSummary() {
		return loanRepository.getSummaryByStatus(Constant.PENDING) == null ? 0
				: loanRepository.getSummaryByStatus(Constant.PENDING);
	}

	@Override
	public Long approvedApplication() {
		return loanRepository.totalCountByStatus(Constant.DISBURS) == null ? 0
				: loanRepository.totalCountByStatus(Constant.DISBURS);
	}

	@Override
	public List<ProfileVO> profileListByExecutiveStatus(LoanStatus loanStatus) {
		ExecutiveEntity exe = userService.getExecutive();

		List<ProfileVO> profileVOs = new ArrayList<ProfileVO>();
		List<Object[]> objects = new ArrayList<Object[]>();
		if (loanStatus == null) {
			List<LoanEntity> loans = loanRepository.findByExecutiveId(exe.getId());
			for (LoanEntity loan : loans) {
				loan.setBanks(loan.getBanks().stream().sorted().collect(Collectors.toList()));
				ProfileVO profileVO = loan.getProfile().toVo();
				profileVO.setLoanVO(loan.toVo());
				profileVOs.add(profileVO);
			}

		} else {
			List<LoanEntity> loans = loanRepository.findByExecutiveIdAndStatus(exe.getId(), loanStatus);
			for (LoanEntity loan : loans) {
				ProfileVO profileVO = loan.getProfile().toVo();
				profileVO.setLoanVO(loan.toVo());
				profileVOs.add(profileVO);
			}
		}

		return profileVOs;
	}

	@Override
	public List<ProfileVO> profileListByExecutiveStatus(LoanStatus loanStatus, Integer id) {
		ExecutiveEntity executive;
		if(id != null) {
			executive = executiveRepository.findById(id).get();
		} else {
			executive = userService.getExecutive();
		}

		List<ProfileVO> profileVOs = new ArrayList<ProfileVO>();
		List<Object[]> objects = new ArrayList<Object[]>();
		if (loanStatus == null) {
			List<LoanEntity> loans = loanRepository.findByExecutiveId(id);
			for (LoanEntity loan : loans) {
				ProfileVO profileVO = loan.getProfile().toVo();
				profileVO.setLoanVO(loan.toVo());
				profileVOs.add(profileVO);
			}

		} else {
			List<LoanEntity> resp = new ArrayList<>();
			if(loanStatus.equals(LoanStatus.APPROVE)) {
				List<LoanEntity> loans = loanRepository.findByExecutiveIdAndStatus(executive.getId(), LoanStatus.NEW);
				for (LoanEntity loan : loans) {
					if (!loan.getBanks().isEmpty()) {
						resp.add(loan);
					}
				}
			} else if(loanStatus.equals(LoanStatus.NEW) ) {
				List<LoanEntity> loans = loanRepository.findByExecutiveIdAndStatus(executive.getId(), LoanStatus.NEW);
				for (LoanEntity loan : loans) {
					if (loan.getBanks().isEmpty()) {
						resp.add(loan);
					}
				}
			} else {
				List<LoanEntity> loans = loanRepository.findByExecutiveIdAndStatus(executive.getId(), loanStatus);
				resp = loans;
			}

			for (LoanEntity loan : resp) {
				ProfileVO profileVO = loan.getProfile().toVo();
				profileVO.setLoanVO(loan.toVo());
				profileVOs.add(profileVO);
			}
		}

		return profileVOs;
	}

	@Override
	public long countLoanByBranchName(String brachName, LoanStatus pStatus) {
		List<Object[]> objects = new ArrayList<Object[]>();
		long summary = 0;
		if (pStatus.equals(LoanStatus.INPROGRESS)) {
			objects = loanRepository.getCountByBranch(brachName, Constant.NOT_DISBURSH);
			for (Object[] object : objects) {
				if (object != null) {
					summary = Long.valueOf(object[0].toString());
				}
			}
		} else if (pStatus.equals(LoanStatus.DISBURSE)) {
			objects = loanRepository.getCountByBranch(brachName, Arrays.asList(LoanStatus.DISBURSE.toString()));
			for (Object[] object : objects) {
				if (object != null) {
					summary = Long.valueOf(object[0].toString());
				}
			}
		}
		return summary;
	}

	@Override
	public long countLoanByBranchId(Integer id, LoanStatus pStatus) {
		List<Object[]> objects = new ArrayList<Object[]>();
		long summary = 0;
		if (pStatus.equals(LoanStatus.INPROGRESS)) {
			objects = loanRepository.getCountByBranchId(id, Constant.NOT_DISBURSH);
			for (Object[] object : objects) {
				if (object != null) {
					summary = Long.valueOf(object[0].toString());
				}
			}
		} else if (pStatus.equals(LoanStatus.DISBURSE)) {
			objects = loanRepository.getCountByBranchId(id, Arrays.asList(LoanStatus.DISBURSE.toString()));
			for (Object[] object : objects) {
				if (object != null) {
					summary = Long.valueOf(object[0].toString());
				}
			}
		}
		return summary;
	}

	@Override
	public ExceutiveProfieVO getExceutiveProfieVO(Integer pExceutiveId) {
		ExceutiveProfieVO exceutiveProfieVO = null;
		Optional<ExecutiveEntity> executive = executiveRepository.findById(pExceutiveId);
		if (executive.get() != null) {
			exceutiveProfieVO = new ExceutiveProfieVO();
			exceutiveProfieVO.setName(executive.get().getFirstName() + " " + executive.get().getLastName());
			exceutiveProfieVO.setMail(executive.get().getEmail());
			exceutiveProfieVO.setPhone(executive.get().getMobile());
			exceutiveProfieVO.setBranchName(executive.get().getBranch().getName());
			exceutiveProfieVO.setShowroomName(executive.get().getBranch().getName());
			exceutiveProfieVO.setBranchAddress(executive.get().getBranch().getAddress());
			exceutiveProfieVO.setBranchId(executive.get().getBranch().getCode());
		}
		return exceutiveProfieVO;
	}

	@Override
	public ExceutiveProfieVO getBranchProfieVO(Integer pBranchId) {
		ExceutiveProfieVO exceutiveProfieVO = null;
		Optional<BranchEntity> branch = branchRepository.findById(pBranchId);
		if (branch.get() != null) {
			exceutiveProfieVO = new ExceutiveProfieVO();
			exceutiveProfieVO.setBranchName(branch.get().getName());
			exceutiveProfieVO.setShowroomName(branch.get().getName());
			exceutiveProfieVO.setBranchAddress(branch.get().getAddress());
			exceutiveProfieVO.setBranchId(branch.get().getCode());
			exceutiveProfieVO.setBranchCity(branch.get().getCity());
			exceutiveProfieVO.setBranchContact(branch.get().getMobile());
		}
		return exceutiveProfieVO;
	}

	@Override
	public List<ProfileVO> getProfileByBranch(Integer pBranchId, LoanStatus loanStatus) {
		List<ProfileVO> profileVOs = new ArrayList<ProfileVO>();
		List<ExecutiveEntity> listExecutivs = new ArrayList<>();
		Optional<BranchEntity> branch = branchRepository.findById(pBranchId);
		List<Object[]> objects = new ArrayList<Object[]>();
		if (branch.get() != null) {
			listExecutivs = executiveRepository.findByBranch(branch.get());
		}
		for (ExecutiveEntity executiveEntity : listExecutivs) {
			if (loanStatus == null) {

				List<LoanEntity> loans = loanRepository.findByExecutiveId(executiveEntity.getId());
				for (LoanEntity loan : loans) {
					ProfileVO profileVO = loan.getProfile().toVo();
					profileVO.setLoanVO(loan.toVo());
					profileVOs.add(profileVO);
				}
			} else {
				List<LoanEntity> loans = loanRepository.findByExecutiveIdAndStatus(executiveEntity.getId(), loanStatus);
				for (LoanEntity loan : loans) {
					ProfileVO profileVO = loan.getProfile().toVo();
					profileVO.setLoanVO(loan.toVo());
					profileVOs.add(profileVO);
				}
			}
		}
		return profileVOs;
	}

	@Override
	public LoanProfilePage loanProfilePage() {
		LoanProfilePage loanProfilePage = new LoanProfilePage();
		List<ProfileVO> profileVOs = new ArrayList<>();
		List<LoanEntity> loans = loanRepository.findAll();
		for (LoanEntity loan: loans) {
			ProfileVO profileVO = loan.getProfile().toVo();
			profileVOs.add(profileVO);
		}
		loanProfilePage.setProfileVOs(profileVOs);
		LoanSummary loanSummary = new LoanSummary();
		loanSummary.setTotalLoanAmount(loanRepository.sumTotalLoan() == null ? 0 : loanRepository.sumTotalLoan());
		ExcecutiveLoanCountVO excecutiveLoanCountVO = loanStatuByExecutive(null, null);
		List<LoanCountVO> countVOs = excecutiveLoanCountVO.getResult();
		long loansummary = 0;
		for (LoanCountVO loanCountVO : countVOs) {
			loansummary = loansummary + Long.valueOf(loanCountVO.reject.get(1).toString());
			loansummary = loansummary + Long.valueOf(loanCountVO.pending.get(1).toString());
		}
		loanSummary.setApproved(loansummary);
		ArrayList<String> loanStatus = new ArrayList<String>();
<<<<<<< HEAD
		loanStatus.add(LoanStatus.DISBURS.toString());
=======
		loanStatus.add(LoanStatus.DISBURSE.toString());
>>>>>>> 83b38cd (Chanages in serviceImpl by Akash)
		List<LoanVO> profileList = lenderService.getLoans();
		loanSummary.setTotalBorrower((long) profileList.size());
		loanProfilePage.setLoanSummary(loanSummary);
		return loanProfilePage;
	}

	@Override
	public LoanProfilePage executiveProfilePage(Integer pExceutiveId) {
		ExecutiveEntity executive;
		if(pExceutiveId != null) {
			executive = executiveRepository.findById(pExceutiveId).get();
		} else {
			executive = userService.getExecutive();
		}

		pExceutiveId = executive.getId();
		LoanProfilePage loanProfilePage = new LoanProfilePage();
		List<ProfileVO> profileVOs = new ArrayList<>();
<<<<<<< HEAD
<<<<<<< HEAD
		List<ProfileVO> profiles = profileRepository.findByExecutiveId(pExceutiveId).stream().map(Profile::toVo)
				.collect(Collectors.toList());
		for (ProfileVO profileVO : profiles) {
			LoanEntity loan = loanRepository.findByProfile(profileRepository.getById(profileVO.getProfileId()));
=======
		List<ProfileVO> profiles = loanRepository.findByExecutiveId(pExceutiveId).stream().map(Profile::toVo)
				.collect(Collectors.toList());
		for (ProfileVO profileVO : profiles) {
			LoanEntity loan = loanRepository.findById(loanRepository.getById(getLoanByDate()));
>>>>>>> 83b38cd (Chanages in serviceImpl by Akash)
=======
		List<ProfileVO> profiles = loanRepository.findByExecutiveId(pExceutiveId).stream()
				.map(l -> l.getProfile().toVo())
				.collect(Collectors.toList());
		for (ProfileVO profileVO : profiles) {
			LoanEntity loan = loanRepository.findById(profileVO.getLoanVO().getId()).get();
>>>>>>> 2734dd0 (loan service impl fixes)
			loan.setProfile(null);
			profileVO.setLoanVO(loan.toVo());
			profileVOs.add(profileVO);
		}
		loanProfilePage.setProfileVOs(profileVOs);
		LoanSummary loanSummary = new LoanSummary();
		ExcecutiveLoanCountVO excecutiveLoanCountVO = loanStatuByExecutive(null, pExceutiveId);
		List<LoanCountVO> countVOs = excecutiveLoanCountVO.getResult();
		long loansummary = 0;
		for (LoanCountVO loanCountVO : countVOs) {
			loansummary = loansummary + Long.valueOf(loanCountVO.applied.get(1).toString());
			loansummary = loansummary + Long.valueOf(loanCountVO.reject.get(1).toString());
			loansummary = loansummary + Long.valueOf(loanCountVO.pending.get(1).toString());
		}
		loanSummary.setApproved(loansummary);
		long disburshsummary = 0;
		for (LoanCountVO loanCountVO : countVOs) {
			disburshsummary = disburshsummary + Long.valueOf(loanCountVO.disburse.get(1).toString());
		}
		loanSummary.setTotalBorrower(disburshsummary);
		loanProfilePage.setLoanSummary(loanSummary);
		ExceutiveProfieVO exceutiveProfieVO = getExceutiveProfieVO(pExceutiveId);
		loanProfilePage.setExceutiveProfieVO(exceutiveProfieVO);
		return loanProfilePage;
	}

	@Override
	public LoanProfilePage branchProfilePage(Integer pBranchId) {
		return null;
	}

	@Override
	public ProfileVO updateLoanStatus(StatusVO statusVo) {
<<<<<<< HEAD
		LoanEntity loanEntity = loanRepository.findByProfile(profileRepository.getById(statusVo.getId()));
		if (loanEntity == null) {
			throw new NotFoundException("Invalid Loan");
		}
=======
		Optional<LoanEntity>optionalLoanEntity=loanRepository.findById(statusVo.getId());
		if(optionalLoanEntity.isPresent()){
			throw new NotFoundException("Invalid Loan");
		}
		LoanEntity loanEntity =optionalLoanEntity.get();
//		if (loanEntity == null) {
//			throw new NotFoundException("Invalid Loan");
//		}
>>>>>>> 83b38cd (Chanages in serviceImpl by Akash)

		if (statusVo.getStatus().equalsIgnoreCase(LoanStatus.APPROVE.toString())
				|| statusVo.getStatus().equalsIgnoreCase(LoanStatus.REJECT.toString())) {
			BankEntity bank = userService.getBankDetails();
			if (bank != null) {
				List<BankLoanEntity> loans = new ArrayList<>();
//					loans.addAll(loan.getBanks());
<<<<<<< HEAD
				Optional<BankLoanEntity> isBankLoan = loan.getBanks().stream()
=======
				Optional<BankLoanEntity> isBankLoan = loanEntity.getBanks().stream()
>>>>>>> 83b38cd (Chanages in serviceImpl by Akash)
						.filter(bl -> bl.getBank().getId() == bank.getId()).findFirst();
				BankLoanEntity bankLoan;
				if (isBankLoan.isPresent()) {

					bankLoan = isBankLoan.get();
				} else {
					bankLoan = new BankLoanEntity();
				}
<<<<<<< HEAD
				bankLoan.setLoan(loan);
=======
				bankLoan.setLoan(loanEntity);
>>>>>>> 83b38cd (Chanages in serviceImpl by Akash)
				bankLoan.setBank(bank);
				bankLoan.setStatus(statusVo.getStatus());
				bankLoan.setInterest(null);
				bankLoan.setReason(statusVo.getReason());
				loans.add(bankLoan);
				bank.setLoans(loans);
				bankRepository.save(bank);
			}
		} else {

			for (BankLoanEntity bankLoan : loanEntity.getBanks()) {
<<<<<<< HEAD
<<<<<<< HEAD
				if (loanEntity.getBank().getId() == bankLoan.getBank().getId())
					bankLoan.setStatus(statusVo.getStatus().toUpperCase());
			}
			loanEntity.setLoanStatus(LoanStatus.fromString(statusVo.getStatus()).get());
			if (statusVo.getStatus().toUpperCase().equals(LoanStatus.DISBURSE.toString())) {
				loanEntity.setCompleteDate(LocalDateTime.now());
			}

		}
		loanEntity.setUpdatedby(LocalDateTime.now());
=======
				if (loanEntity.getBanks().getId() == bankLoan.getBank().getId())
=======
				if (loanEntity.getApproval().getLender().getId() == bankLoan.getBank().getId())
>>>>>>> 2734dd0 (loan service impl fixes)
					bankLoan.setStatus(statusVo.getStatus().toUpperCase());
			}
			loanEntity.setStatus(LoanStatus.fromString(statusVo.getStatus()).get());
			if (statusVo.getStatus().toUpperCase().equals(LoanStatus.DISBURSE.toString())) {
				loanEntity.getStatus();
			}

		}
<<<<<<< HEAD
		loanEntity.setUpdateBy(LocalDateTime.now());
>>>>>>> 83b38cd (Chanages in serviceImpl by Akash)
=======
		loanEntity.setUpdatedDate(LocalDateTime.now());
>>>>>>> 2734dd0 (loan service impl fixes)
		loanRepository.save(loanEntity);
		ProfileVO rProfileVO = null;
		rProfileVO = loanEntity.getProfile().toVo();
		rProfileVO.setLoanVO(loanEntity.toVo());
		return rProfileVO;
	}

	@Override
	public LoanProfilePage loanDailyStatus(Integer pExecutiveId, LocalDateTime startDate, LocalDateTime endDate) {
		LoanProfilePage loanProfilePage = new LoanProfilePage();
		List<Object[]> objects = new ArrayList<Object[]>();
		if (pExecutiveId == null) {
			objects = loanRepository.getCountByDate(startDate, endDate);
		} else {
			objects = loanRepository.getCountByExecutiveDate(pExecutiveId, startDate, endDate);
		}

		LoanSummary loanSummary = new LoanSummary();
		long approved = 0;
		long disbush = 0;
		for (Object[] object : objects) {
			if (object != null) {
				approved = (object[0] != null ? Integer.valueOf(object[0].toString()) : 0)
						+ (object[1] != null ? Integer.valueOf(object[1].toString()) : 0)
						+ (object[2] != null ? Integer.valueOf(object[2].toString()) : 0);
				disbush = object[3] != null ? Integer.valueOf(object[3].toString()) : 0;
			}
		}
		loanSummary.setApproved(approved);
		loanSummary.setTotalBorrower(disbush);
		loanSummary.setTotalLoanAmount(null);
		loanProfilePage.setLoanSummary(loanSummary);
		return loanProfilePage;
	}

	@Override
	public LoanProfilePage executiveDailyStatusReport(Integer pExecutiveId, LocalDateTime startDate, LocalDateTime endDate) {
		LoanProfilePage loanProfilePage = new LoanProfilePage();
		List<LoanEntity> loans = new ArrayList<>();
		if(pExecutiveId==null) {
			loans = loanRepository.getLoanByStatusDate(startDate, endDate);
		}
		else {
			loans = loanRepository.getLoanByStatusDateAndExecutive( startDate, endDate,pExecutiveId);
		}
		AtomicInteger apply = new AtomicInteger();
		AtomicInteger newL = new AtomicInteger();
		AtomicInteger inprogress=new AtomicInteger();
		AtomicInteger hold=new AtomicInteger();
		AtomicInteger reject=new AtomicInteger();
		AtomicInteger disbush=new AtomicInteger();
		AtomicInteger approve=new AtomicInteger();
		loans.stream().forEach(loan->{
<<<<<<< HEAD
			if (loan.getLoanStatus().equals(LoanStatus.NEW)) {
=======
			if (loan.getStatus().equals(LoanStatus.NEW)) {
>>>>>>> 83b38cd (Chanages in serviceImpl by Akash)
				if(!loan.getBanks().isEmpty()) {
					long count = loan.getBanks().stream().filter(bl->bl.getStatus().equalsIgnoreCase("APPROVE")).count();
					if (count>0) {
						approve.getAndIncrement();
					}
				} else {
					newL.getAndIncrement();
				}
<<<<<<< HEAD
			} else if (loan.getLoanStatus().equals(LoanStatus.REJECT)) {
				reject.getAndIncrement();
			} else if(loan.getLoanStatus().equals(LoanStatus.INPROGRESS)){
				inprogress.getAndIncrement();
			} else if (loan.getLoanStatus().equals(LoanStatus.DISBURSE)) {
				disbush.getAndIncrement();
			} else if(loan.getLoanStatus().equals(LoanStatus.APPLY)){
				apply.getAndIncrement();
			} else if(loan.getLoanStatus().equals(LoanStatus.HOLD)){
=======
			} else if (loan.getStatus().equals(LoanStatus.REJECT)) {
				reject.getAndIncrement();
			} else if(loan.getStatus().equals(LoanStatus.INPROGRESS)){
				inprogress.getAndIncrement();
			} else if (loan.getStatus().equals(LoanStatus.DISBURSE)) {
				disbush.getAndIncrement();
			} else if(loan.getStatus().equals(LoanStatus.APPLY)){
				apply.getAndIncrement();
			} else if(loan.getStatus().equals(LoanStatus.HOLD)){
>>>>>>> 83b38cd (Chanages in serviceImpl by Akash)
				hold.getAndIncrement();
			}
		});
		LoanStatusCount loanStatusCount = new LoanStatusCount();

		loanStatusCount.setNewLoan(newL.get());
		loanStatusCount.setApply(apply.get());
		loanStatusCount.setInprogress(inprogress.get());
		loanStatusCount.setHold(hold.get());
		loanStatusCount.setReject(reject.get());
		loanStatusCount.setApprove(approve.get());
		loanStatusCount.setDisburse(disbush.get());
		loanProfilePage.setLoanStatusCount(loanStatusCount);
		return loanProfilePage;
	}

	@Override
	public LoanProfilePage branchDailyStatusReport(Integer pBranchId, LocalDateTime startDate, LocalDateTime endDate) {
		LoanProfilePage loanProfilePage = new LoanProfilePage();
		List<LoanEntity> loans = loanRepository.getCountByBranchDate(startDate, endDate,pBranchId);
		AtomicInteger apply = new AtomicInteger();
		AtomicInteger newL = new AtomicInteger();
		AtomicInteger inprogress=new AtomicInteger();
		AtomicInteger hold=new AtomicInteger();
		AtomicInteger reject=new AtomicInteger();
		AtomicInteger disbush=new AtomicInteger();
		AtomicInteger approve=new AtomicInteger();
		loans.stream().forEach(loan->{
<<<<<<< HEAD
			if (loan.getLoanStatus().equals(LoanStatus.NEW)) {
=======
			if (loan.getStatus().equals(LoanStatus.NEW)) {
>>>>>>> 83b38cd (Chanages in serviceImpl by Akash)
				if(!loan.getBanks().isEmpty()) {
					long count = loan.getBanks().stream().filter(bl->bl.getStatus().equalsIgnoreCase("APPROVE")).count();
					if (count>0) {
						approve.getAndIncrement();
					}
				}
				else {
					newL.getAndIncrement();
				}
<<<<<<< HEAD
			} else if (loan.getLoanStatus().equals(LoanStatus.REJECT)) {
				reject.getAndIncrement();
			} else if(loan.getLoanStatus().equals(LoanStatus.INPROGRESS)){
				inprogress.getAndIncrement();
			} else if (loan.getLoanStatus().equals(LoanStatus.DISBURS)) {
				disbush.getAndIncrement();
			} else if(loan.getLoanStatus().equals(LoanStatus.APPLY)){
				apply.getAndIncrement();
			} else if(loan.getLoanStatus().equals(LoanStatus.HOLD)){
=======
			} else if (loan.getStatus().equals(LoanStatus.REJECT)) {
				reject.getAndIncrement();
			} else if(loan.getStatus().equals(LoanStatus.INPROGRESS)){
				inprogress.getAndIncrement();
			} else if (loan.getStatus().equals(LoanStatus.DISBURSE)) {
				disbush.getAndIncrement();
			} else if(loan.getStatus().equals(LoanStatus.APPLY)){
				apply.getAndIncrement();
			} else if(loan.getStatus().equals(LoanStatus.HOLD)){
>>>>>>> 83b38cd (Chanages in serviceImpl by Akash)
				hold.getAndIncrement();
			}
		});
		LoanStatusCount loanStatusCount = new LoanStatusCount();
<<<<<<< HEAD

		loanStatusCount.setApply(apply.get());
		loanStatusCount.setInprogress(inprogress.get());
		loanStatusCount.setHold(hold.get());
		loanStatusCount.setReject(reject.get());
		loanStatusCount.setApprove(approve.get());
		loanStatusCount.setDisburse(disbush.get());
		loanStatusCount.setNewLoan(newL.get());
		loanProfilePage.setLoanStatusCount(loanStatusCount);
		return loanProfilePage;
	}
=======
>>>>>>> 83b38cd (Chanages in serviceImpl by Akash)

		loanStatusCount.setApply(apply.get());
		loanStatusCount.setInprogress(inprogress.get());
		loanStatusCount.setHold(hold.get());
		loanStatusCount.setReject(reject.get());
		loanStatusCount.setApprove(approve.get());
		loanStatusCount.setDisburse(disbush.get());
		loanStatusCount.setNewLoan(newL.get());
		loanProfilePage.setLoanStatusCount(loanStatusCount);
		return loanProfilePage;
	}

	@Override
	public ResultVO gettingYearlyReportLender() {
		ResultVO resultVO = new ResultVO();

		LocalDate startDate = LocalDate.now().minusMonths(5).withDayOfMonth(1);
		LocalDate lastData = LocalDate.now();
		Integer month = startDate.getMonthValue();
		List<Object> months = new ArrayList<>();
		do {

			month = startDate.getMonthValue();
			LocalDate endDate = startDate.plusDays(startDate.lengthOfMonth() - 1);

			Map<String, List<Object>> mon = new HashMap<>();
			List<Object> data = new ArrayList<>();
			data.add(startDate.getMonth().toString().substring(0, 3));
			data.addAll(extractedByLender(startDate.atTime(LocalTime.MIN), endDate.atTime(LocalTime.MAX)));

			months.add(data);

			startDate = startDate.plusMonths(1);
			if (startDate.isAfter(lastData)) {
				startDate = lastData;
			}

		} while (month != lastData.getMonthValue());
		resultVO.setMonths(months);
		return resultVO;
<<<<<<< HEAD
=======
	}

	private List<Object> extractedByLender(LocalDateTime startDate, LocalDateTime endDate) {
		List<Object> day1 = new ArrayList<Object>();
		BankEntity bank = userService.getBankDetails();
		day1.add(loanRepository.getLoanReportByStatusDateNew(startDate, endDate, Constant.NEW));
		day1.add(loanRepository.getLoanReportByStatusDateAndBank(startDate, endDate, Constant.APPLY, bank));
		day1.add(loanRepository.getLoanReportByStatusDateAndBank(startDate, endDate, Constant.INPROGRESS, bank));
		day1.add(loanRepository.getLoanReportByStatusDateAndBankReject(startDate, endDate, LoanStatus.REJECT.toString(), bank.getId()));
		day1.add(loanRepository.getLoanReportByStatusDateAndBank(startDate, endDate, Constant.DISBURS, bank));
		return day1;
>>>>>>> 83b38cd (Chanages in serviceImpl by Akash)
	}

	@Override
	public ResultVO gettingYearlyReportAdmin() {
		ResultVO resultVO = new ResultVO();

		LocalDate startDate = LocalDate.now().minusMonths(5).withDayOfMonth(1);
		LocalDate lastData = LocalDate.now();
		Integer month = startDate.getMonthValue();
		List<Object> months = new ArrayList<>();
		do {

			month = startDate.getMonthValue();
			LocalDate endDate = startDate.plusDays(startDate.lengthOfMonth() - 1);

			Map<String, List<Object>> mon = new HashMap<>();
			List<Object> data = new ArrayList<>();
			data.add(startDate.getMonth().toString().substring(0, 3));
<<<<<<< HEAD
			data.addAll(extractedByAdmin(startDate.atTime(LocalTime.MIN), endDate.atTime(LocalTime.MAX)));
=======
			data.addAll(extractedByAdmin(startDate.atTime(LocalTime.MIN), startDate.atTime(LocalTime.MAX)));
>>>>>>> 83b38cd (Chanages in serviceImpl by Akash)

			months.add(data);

			startDate = startDate.plusMonths(1);
			if (startDate.isAfter(lastData)) {
				startDate = lastData;
			}

		} while (month != lastData.getMonthValue());
		resultVO.setMonths(months);
		return resultVO;
<<<<<<< HEAD
=======
	}

	private List<Object> extractedByAdmin(LocalDateTime startDate, LocalDateTime endDate) {
		List<Object> day1 = new ArrayList<Object>();
		UserEntity user = userService.getUser();
		DSAEntity company = user.getCompany();
		day1.add(loanRepository.getLoanReportByStatusDateAndAdmin(startDate, endDate, LoanStatus.NEW, company.getId()));
		day1.add(loanRepository.getLoanReportByStatusDateAndAdmin(startDate, endDate, LoanStatus.APPLY, company.getId()));
		day1.add(loanRepository.getLoanReportByStatusDateAndAdmin(startDate, endDate, LoanStatus.INPROGRESS, company.getId()));
		day1.add(loanRepository.getLoanReportByStatusDateAndAdmin(startDate, endDate, LoanStatus.REJECT, company.getId()));
		day1.add(loanRepository.getLoanReportByStatusDateAndAdmin(startDate, endDate, LoanStatus.DISBURSE, company.getId()));
		return day1;
>>>>>>> 83b38cd (Chanages in serviceImpl by Akash)
	}

	@Override
	public List<LoanVO> getLoanStatusAdmin(LoanStatus status) {
		UserEntity user = userService.getUser();
		DSAEntity company = user.getCompany();
		List<LoanEntity> resp = new ArrayList<>();
		if(status.equals(LoanStatus.APPROVE)) {
			List<LoanEntity> loans = loanRepository.getLoanByStatusAdmin(LoanStatus.NEW, company.getId());
			for (LoanEntity loan : loans) {
				if (!loan.getBanks().isEmpty()) {
					resp.add(loan);
				}
			}
		}
		else if(status.equals(LoanStatus.NEW) ) {
			List<LoanEntity> loans = loanRepository.getLoanByStatusAdmin(LoanStatus.NEW,company.getId());
			for (LoanEntity loan : loans) {
				if (loan.getBanks().isEmpty()) {
					resp.add(loan);
				}
			}
		} else {
			List<LoanEntity> loans = loanRepository.getLoanByStatusAdmin(LoanStatus.NEW,company.getId());
			resp = loans;
		}
		return resp
				.stream()
				.map(loan -> loan.toVo())
				.collect(Collectors.toList());
	}

	@Override
	public List<LoanVO> getbranchLoanAdmin(Integer branchId) {
		UserEntity user = userService.getUser();
		DSAEntity company = user.getCompany();
		return loanRepository
				.getLoanByBranchAdmin(branchId,company.getId())
				.stream()
				.map(loan -> loan.toVo())
				.collect(Collectors.toList());
	}

	@Override
	public List<LoanVO> getExecutiveLoanAdmin(Integer executiveId) {
		UserEntity user = userService.getUser();
		DSAEntity company = user.getCompany();
		return loanRepository
				.getLoanByExecutiveAdmin(executiveId,company.getId())
				.stream()
				.map(loan -> loan.toVo())
				.collect(Collectors.toList());
	}

	@Override
	public List<LoanVO> getLoanDateAdmin(String startDate, String endDate) {
		UserEntity user = userService.getUser();
		DSAEntity company = user.getCompany();
		LocalDateTime startD = dateFormatter(startDate).atTime(LocalTime.MIN);
		LocalDateTime endD = dateFormatter(endDate).atTime(LocalTime.MAX);
		return loanRepository
				.getLoanByDateAdmin(startD,endD,company.getId())
				.stream()
				.map(loan -> loan.toVo())
				.collect(Collectors.toList());
	}

	@Override
	public List<LoanVO> getLoansByStatus(LoanStatus status) {
		List<LoanEntity> resp = new ArrayList<>();
		if(status.equals(LoanStatus.APPROVE)) {
			List<LoanEntity> loans = loanRepository.findByStatus( LoanStatus.NEW);
			for (LoanEntity loan : loans) {
				if (!loan.getBanks().isEmpty()) {
					resp.add(loan);
				}
			}
		} else if(status.equals(LoanStatus.NEW) ) {
			List<LoanEntity> loans = loanRepository.findByStatus(LoanStatus.NEW);
			for (LoanEntity loan : loans) {
				if (loan.getBanks().isEmpty()) {
					resp.add(loan);
				}
			}
		} else {
			List<LoanEntity> loans = loanRepository.findByStatus(status);
			resp = loans;
		}
		return resp.stream().map(LoanEntity::toVo).collect(Collectors.toList()) ;
	}

	@Override
	public List<LoanVO> getLoanByDate(String startDate, String endDate) {
		LocalDateTime startD = dateFormatter(startDate).atTime(LocalTime.MIN);
		LocalDateTime endD = dateFormatter(endDate).atTime(LocalTime.MAX);
		return loanRepository
				.getLoanByDate(startD,endD)
				.stream()
				.map(loan -> loan.toVo())
				.collect(Collectors.toList());
	}

	private LocalDate dateFormatter(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(date, formatter);
	}

	@Override
	public List<LoanVO> getLoansByDSA(Integer id) {
		return loanRepository
				.getLoanByDealer(id)
				.stream()
				.map(loan -> loan.toVo())
				.collect(Collectors.toList());
	}


}
