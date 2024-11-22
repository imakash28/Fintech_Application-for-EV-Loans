package com.kilpi.finayo.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kilpi.finayo.Constant.LoanStatus;
import com.kilpi.finayo.Constant.ReportType;
import com.kilpi.finayo.Domain.UserEntity;
import com.kilpi.finayo.Repository.UserRepository;
import com.kilpi.finayo.Service.CommentService;
import com.kilpi.finayo.Service.LenderService;
import com.kilpi.finayo.Service.LoanService;
import com.kilpi.finayo.Service.UserService;
import com.kilpi.finayo.VO.BranchLoanStatusVO;
import com.kilpi.finayo.VO.CommentVO;
import com.kilpi.finayo.VO.DashboardVO;
import com.kilpi.finayo.VO.ExcecutiveLoanCountVO;
import com.kilpi.finayo.VO.LoanProfilePage;
import com.kilpi.finayo.VO.LoanStatsVO;
import com.kilpi.finayo.VO.LoanVO;
import com.kilpi.finayo.VO.ProfileVO;
import com.kilpi.finayo.VO.ReportVO;
import com.kilpi.finayo.VO.ResponseVO;
import com.kilpi.finayo.VO.StatusVO;

@RestController
@RequestMapping(value = "loan")
public class LoanController {

	Logger logger = LoggerFactory.getLogger(LoanController.class);

	@Autowired
	private LoanService loanService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private LenderService lenderService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentService commentService;

	@PostMapping(value = "/apply")
	public ResponseVO createProfile(@Valid @RequestBody LoanVO pLoanVO) {
		try {

			return ResponseVO.builder()
					.data(loanService.saveLoan(pLoanVO))
					.status(200)
					.message("Loan Created")
					.build();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseVO.builder()
					.status(200)
					.error("Error Applying Loan. Please Contact Finayo")
					.build();
		}
	}

	/**
	 * below code should be reformatted
	 * @return
	 */

//	@GetMapping("/profiles")
//	public ResponseEntity<List<ProfileVO>> getAllProfiles() {
//		try {
//			List<ProfileVO> profiles = loanService.getProfiles();
//			if (profiles.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			return new ResponseEntity<>(profiles, HttpStatus.OK);
//		} catch (Exception e) {
//			logger.error(e.getLocalizedMessage());
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	@GetMapping("/profiles/{executiveId}")
	public ResponseEntity<List<ProfileVO>> getProfileByExecutive(
			@PathVariable(value = "executiveId") Integer executiveId) {
		List<ProfileVO> profiles = new ArrayList<ProfileVO>();
		loanService.profileListByExecutiveStatus(null, executiveId).forEach(profiles::add);
		if (profiles.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(profiles, HttpStatus.OK);
	}



//	@GetMapping("/loans")
//	public ResponseEntity<List<LoanVO>> getAllLoans() {
//		try {
//			List<LoanVO> loans = loanService.getLoans();
//			if (loans.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			return new ResponseEntity<>(loans, HttpStatus.OK);
//		} catch (Exception e) {
//			logger.error(e.getLocalizedMessage());
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	@GetMapping(value = "/stastistitics")
	public ResponseEntity<LoanStatsVO> getLoanStastistitics() {
		try {
			LoanStatsVO loanStatsVO = loanService.getLoanStastistitics();
			if (loanStatsVO == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(loanStatsVO, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@GetMapping(value = { "report/weekly", "report/monthly", "report/yearly" })
//	public ResponseEntity<ReportVO> getLoanReport(HttpServletRequest request) {
//		try {
//			ReportVO reportVO = null;
//			if (request.getServletPath().contains("report/weekly")) {
//				reportVO = loanService.loanReportByDays(ReportType.week);
//			} else if (request.getServletPath().contains("report/monthly")) {
//				reportVO = loanService.loanReportByDays(ReportType.month);
//			} else if (request.getServletPath().contains("report/yearly")) {
//				reportVO = loanService.loanReportByDays(ReportType.year);
//			} else {
//				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//			if (reportVO == null) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			return new ResponseEntity<>(reportVO, HttpStatus.OK);
//		} catch (Exception e) {
//			logger.error(e.getLocalizedMessage());
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
//	@GetMapping(value = { "report/daily", "report/weekly", "report/monthly" })
//	public ResponseEntity<ReportVO> getLoanReport(HttpServletRequest request) {
//		try {
//			ReportVO reportVO = null;
//			if (request.getServletPath().contains("report/daily")) {
//				reportVO = loanService.getReportFromAuth(ReportType.DAILY);
//			} else if (request.getServletPath().contains("report/weekly")) {
//				reportVO = loanService.getReportFromAuth(ReportType.WEEKLY);
//			} else if (request.getServletPath().contains("report/monthly")) {
//				reportVO = loanService.getReportFromAuth(ReportType.MONTHLY);
//			} else {
//				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//			if (reportVO == null) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			return new ResponseEntity<>(reportVO, HttpStatus.OK);
//		} catch (Exception e) {
//			logger.error(e.getLocalizedMessage());
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	@GetMapping(value = { "branch/all/{status}", "branch/daily/{status}" })
	public ResponseEntity<BranchLoanStatusVO> gteBranchLoanStatus(HttpServletRequest request,
																  @PathVariable(value = "status") String status) {

		try {
			BranchLoanStatusVO branchLoanStatusVO = null;
			LoanStatus loanStatus = LoanStatus.fromString(status).get();
			if (request.getServletPath().contains("branch/all")) {
				branchLoanStatusVO = loanService.loanStatusForBranch(loanStatus, "all");
			} else if (request.getServletPath().contains("branch/daily")) {
				branchLoanStatusVO = loanService.loanStatusForBranch(loanStatus, "daily");
			} else {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			if (branchLoanStatusVO == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(branchLoanStatusVO, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = { "executive/{id}", "executive" })
	public ResponseEntity<ExcecutiveLoanCountVO> getLoanCountByExecutive(HttpServletRequest request,
																		 @PathVariable(value = "id", required = false) Integer id) {
		try {
			ExcecutiveLoanCountVO excecutiveLoanCountVO = null;
			if (request.getServletPath().contains("executive")) {
				excecutiveLoanCountVO = loanService.loanStatuByExecutive(null, id);
			} else {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			if (excecutiveLoanCountVO == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(excecutiveLoanCountVO, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "dashboard")
	public ResponseEntity<DashboardVO> dashboard(HttpServletRequest request) {
		try {
			DashboardVO dashboardVO = loanService.displayDashboard();
			if (dashboardVO == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(dashboardVO, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	//verify
	@GetMapping(value = { "branch/pending/{branchname}", "branch/complete/{branchname}" })
	public ResponseEntity<Long> brnachLoanCount(HttpServletRequest request,
			@PathVariable(value = "name", required = false) String branchName) {
		try {
			long summary = 0;
			if (request.getServletPath().contains("branch/pending")) {
				summary = loanService.countLoanByBranchName(branchName, LoanStatus.INPROGRESS);
			} else if (request.getServletPath().contains("branch/complete")) {
				summary = loanService.countLoanByBranchName(branchName, LoanStatus.DISBURSE);
			} else {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(summary, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/loanpage")
	public ResponseEntity<LoanProfilePage> loanLandingPage(HttpServletRequest requestd,
														   @RequestParam(value = "status", required = false) String status) {
		try {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User map = (User) auth.getPrincipal();
			UserEntity user = userRepository.findByUsername(map.getUsername());

			LoanProfilePage loanProfilePage = null;
			LoanStatus loanStatus = null;
			if (status != null) {

				if(user.getRole().equalsIgnoreCase("LENDER")) {
					loanStatus = LoanStatus.fromString(status).get();
					List<ProfileVO> profileList = lenderService.profileListByBankStatus(loanStatus);
					loanProfilePage = new LoanProfilePage();
					loanProfilePage.setProfileVOs(profileList);
				} else if(user.getRole().equalsIgnoreCase("EXECUTIVE")) {
					loanStatus = LoanStatus.fromString(status).get();
					List<ProfileVO> profileList = loanService.profileListByExecutiveStatus(loanStatus);
					loanProfilePage = new LoanProfilePage();
					loanProfilePage.setProfileVOs(profileList);
				}

			} else {
				if(user.getRole().equalsIgnoreCase("LENDER")) {
					loanProfilePage = lenderService.lenderProfilePage();
				} else if(user.getRole().equalsIgnoreCase("EXECUTIVE")) {
					Integer executiveId = userService.getExecutive().getId();
					loanProfilePage = loanService.executiveProfilePage(executiveId);
				}
			}
			if (loanProfilePage == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			loanProfilePage.setError("OK");
			return new ResponseEntity<>(loanProfilePage, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// verify
	@GetMapping("/executivepage")
	public ResponseEntity<LoanProfilePage> executivLandingPage(HttpServletRequest request,
			@RequestParam(value = "id", required = false) Integer pExecutiveId,
			@RequestParam(value = "status", required = false) String status) {
		try {
			LoanProfilePage loanProfilePage = null;
			LoanStatus loanStatus = null;
			if (status != null) {
				loanStatus = LoanStatus.fromString(status).get();
				List<ProfileVO> profileList = loanService.profileListByExecutiveStatus(loanStatus,pExecutiveId);
				loanProfilePage = new LoanProfilePage();
				loanProfilePage.setProfileVOs(profileList);
			} else {
				loanProfilePage = loanService.executiveProfilePage(pExecutiveId);
			}

			if (loanProfilePage == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			loanProfilePage.setError("OK");
			return new ResponseEntity<>(loanProfilePage, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//verify
	@GetMapping("/branchpage")
	public ResponseEntity<LoanProfilePage> branchLandingPage(HttpServletRequest request,
			@RequestParam(value = "id", required = false) Integer pBranchId,
			@RequestParam(value = "status", required = false) String status) {
		try {
			LoanProfilePage loanProfilePage = null;
			LoanStatus loanStatus = null;
			if (status != null) {
				loanStatus = LoanStatus.fromString(status).get();
				List<ProfileVO> profileList = loanService.getProfileByBranch(pBranchId, loanStatus);
				loanProfilePage = new LoanProfilePage();
				loanProfilePage.setProfileVOs(profileList);
			} else {
				loanProfilePage = loanService.branchProfilePage(pBranchId);
			}

			if (loanProfilePage == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			loanProfilePage.setError("OK");
			return new ResponseEntity<>(loanProfilePage, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = {"/loanupdate"}, produces = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<ProfileVO> createProfile( @RequestBody StatusVO statusVo) {
		try {
			ProfileVO profileVO = loanService.updateLoanStatus(statusVo);
			if (profileVO == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(profileVO, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@GetMapping(value = { "/executivestatus/all", "/executivestatus/{id}" })
	public ResponseEntity<LoanProfilePage> executiveLoanStatus(HttpServletRequest request,
			@PathVariable(value = "id", required = false) Integer pExecutiveId) {
		LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
		try {
			LoanProfilePage loanProfilePage = loanService.loanDailyStatus(pExecutiveId, startOfDay,
					startOfDay.plusDays(1));
			if (loanProfilePage == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			loanProfilePage.setError("OK");
			return new ResponseEntity<>(loanProfilePage, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = {"/executivedailystatus/{id}","/executivedailystatus" })
	public ResponseEntity<LoanProfilePage> executivePiaStatus(HttpServletRequest request,
			@PathVariable(value = "id", required = false) Integer pExecutiveId) {
		LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
		try {
			LoanProfilePage loanProfilePage = null;
			if(pExecutiveId==null) {
				int executive_id = userService.getExecutive().getId();
				loanProfilePage = loanService.executiveDailyStatusReport(executive_id, startOfDay,
						startOfDay.plusDays(1));
			}
			else {
			 	loanProfilePage = loanService.executiveDailyStatusReport(pExecutiveId, startOfDay,
						startOfDay.plusDays(1));
			}
			
			if (loanProfilePage == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			loanProfilePage.setError("OK");
			return new ResponseEntity<>(loanProfilePage, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = { "/all-executivedailystatus"})
	public ResponseEntity<LoanProfilePage> allExecutivePiaStatus(HttpServletRequest request,
			@PathVariable(value = "id", required = false) Integer pExecutiveId) {
		LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
		try {
			LoanProfilePage loanProfilePage = loanService.executiveDailyStatusReport(pExecutiveId, startOfDay,
					startOfDay.plusDays(1));
			if (loanProfilePage == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			loanProfilePage.setError("OK");
			return new ResponseEntity<>(loanProfilePage, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = { "/branchsdailytatus/{id}" })
	public ResponseEntity<LoanProfilePage> branchPiaStatus(HttpServletRequest request,
			@PathVariable(value = "id", required = false) Integer pBranchId) {
		LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
		try {
			LoanProfilePage loanProfilePage = loanService.branchDailyStatusReport(pBranchId, startOfDay,
					startOfDay.plusDays(1));
			if (loanProfilePage == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			loanProfilePage.setError("OK");
			return new ResponseEntity<>(loanProfilePage, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/comment")
	public ResponseVO createComment(@RequestBody CommentVO commentVO,
			@RequestParam(value = "id")Integer lid) {
		try {
			return ResponseVO
					.builder()
					.data(commentService.createComment(commentVO,lid))
					.status(200)
					.message("Comment created SuccessFully")
					.build();

		}
		catch(Exception e) {

			return ResponseVO
					.builder()
					.status(200)
					.error(e.getLocalizedMessage())
					.build();
		}
	}

	@GetMapping("/get-comment")
	public ResponseVO getComment(@RequestParam(value = "id") Long loanId) {
		try {
			return ResponseVO
					.builder()
					.data(commentService.getCommByLoanId(loanId))
					.status(200)
					.message("Comment fetched SuccessFully")
					.build();


		}
		catch(Exception e) {
			return ResponseVO
					.builder()
					.status(200)
					.error(e.getLocalizedMessage())
					.build();

		}
	}

	@PostMapping("/edit/comment")
	public ResponseVO updateComment(@RequestBody CommentVO commentVO,@RequestParam(value = "id") Long lId) {
		try {
			return ResponseVO
					.builder()
					.data(commentService.updateComment(commentVO,lId))
					.status(200)
					.message("Comment updated SuccessFully")
					.build();
		}
		catch(Exception e) {
			return ResponseVO
					.builder()
					.status(200)
					.error(e.getLocalizedMessage())
					.build();

		}
	}

	@GetMapping("/executivestatus/date/{id}")
	public ResponseEntity<LoanProfilePage> executivePiaStatus(HttpServletRequest request,
															  @PathVariable(value = "id", required = false) Integer pExecutiveId,
															  @RequestParam(value= "from") String startDate,
															  @RequestParam(value = "to") String endDate) {
		LocalDateTime sDate = dateFormatter(startDate).atTime(LocalTime.MIN);;
		LocalDateTime eDate = dateFormatter(endDate).atTime(LocalTime.MAX);
		try {
			LoanProfilePage loanProfilePage = null;
			if(pExecutiveId==null) {
				int executive_id = userService.getExecutive().getId();
				loanProfilePage = loanService.executiveDailyStatusReport(executive_id, sDate,
						eDate);
			}
			else {
				loanProfilePage = loanService.executiveDailyStatusReport(pExecutiveId, sDate,
						eDate);
			}

			if (loanProfilePage == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			loanProfilePage.setError("OK");
			return new ResponseEntity<>(loanProfilePage, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private LocalDate dateFormatter(String date){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(date, formatter);
	}
}
