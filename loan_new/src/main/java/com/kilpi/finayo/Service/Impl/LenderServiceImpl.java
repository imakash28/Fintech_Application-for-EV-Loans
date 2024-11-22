package com.kilpi.finayo.Service.Impl;

import com.kilpi.finayo.Constant.Constant;
import com.kilpi.finayo.Constant.LoanStatus;
import com.kilpi.finayo.Constant.ReportType;
import com.kilpi.finayo.Domain.*;
import com.kilpi.finayo.Domain.LoanEntity;
import com.kilpi.finayo.Repository.BankRepository;
import com.kilpi.finayo.Repository.LoanRepository;
import com.kilpi.finayo.Service.LenderService;
import com.kilpi.finayo.Service.LoanService;
import com.kilpi.finayo.Service.UserService;
import com.kilpi.finayo.VO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LenderServiceImpl implements LenderService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    BankRepository bankRepository;

    @Autowired
    UserService userService;


    @Autowired
    LoanService loanService;


    @Override
    public LoanStatsVO getLenderStastistitics() {
        LoanStatsVO loanStatsVO = null;
        loanStatsVO = new LoanStatsVO();

        BankEntity bank = userService.getBankDetails();

        List<LoanVO> loans = this.getLoans();

        Long total = (long) loans.size();
        Long disburse = 0L, pending = 0L, reject = 0L, inProgress = 0L, approve = 0L, apply = 0L, newL = 0L;

//        for (LoanVO loan: loans) {
//            if (loan.getLoanStatus().equals(LoanStatus.DISBURSE)) {
//                disburse++;
//            } else if (loan.getLoanStatus().equals(LoanStatus.HOLD)) {
//                pending++;
//            } else if (loan.getLoanStatus().equals(LoanStatus.REJECT)) {
//                reject++;
//            } else if (loan.getLoanStatus().equals(LoanStatus.INPROGRESS)) {
//                inProgress++;
//            } else if (loan.getLoanStatus().equals(LoanStatus.APPROVE)) {
//                approve++;
//            } else if (loan.getLoanStatus().equals(LoanStatus.APPLY)) {
//                apply++;
//            } else if (loan.getLoanStatus().equals(LoanStatus.NEW)) {
//                newL++;
//            }
//        }

        loanStatsVO.setTotal( total == null ? 0 : total);
        loanStatsVO.setDisburse( disburse == null ? 0 : disburse);
        loanStatsVO.setPending( pending == null ? 0 : pending);
        loanStatsVO.setReject(reject == null ? 0 : reject);
        loanStatsVO.setApply(apply == null ? 0 : apply);
        loanStatsVO.setApprove(approve == null ? 0 : approve);
        loanStatsVO.setNewLoan(newL == null ? 0 : newL);
        loanStatsVO.setInProgress(inProgress == null ? 0 : inProgress);
        return loanStatsVO;
    }

    @Override
    public ReportVO lenderReport() {
        ReportVO reportVO = new ReportVO();

            reportVO.setError(""); // TODO: Need to implemeted
            reportVO.setType(ReportType.MONTHLY.toString());
            ResultVO resultVO = loanService.gettingYearlyReportLender();
            reportVO.setResultVO(resultVO);

        return reportVO;
    }

    @Override
    public List<LoanVO> getLoans() {
        BankEntity bank = userService.getBankDetails();
        List<LoanEntity> loanEntities =  loanRepository.findByStatus(LoanStatus.NEW);
        List<LoanEntity> ln = loanRepository.findByBank(bank);
        loanEntities.addAll(ln);
//        for (LoanEntity loanEntity : loanEntities) {
//            if(!loanEntity.getBanks().isEmpty()){
//                for (BankLoanEntity bankLoan: loanEntity.getBanks() ) {
//                    if(bank.getId() == bankLoan.getBank().getId()){
////                        loanEntity.setLoanStatus(LoanStatus.fromString(bankLoan.getStatus()).get());
//                    }
//                }
//            }
//        }
        return loanEntities.stream().map(LoanEntity::toVo).collect(Collectors.toList());
    }


    private List<Object> extracted(LocalDateTime startDate, LocalDateTime endDate) {
        BankEntity bank = userService.getBankDetails();

        List<Object> day1 = new ArrayList<Object>();
        day1.add(loanRepository.getLoanReportByStatusDateAndBank(startDate, endDate, Constant.APPLY, bank));
        day1.add(loanRepository.getLoanReportByStatusDateAndBank(startDate, endDate, Constant.INPROGRESS, bank));
        day1.add(loanRepository.getLoanReportByStatusDateAndBank(startDate, endDate, Constant.REJECT, bank));
        day1.add(loanRepository.getLoanReportByStatusDateAndBank(startDate, endDate, Constant.COMPLETED, bank));
        return day1;
    }


    @Override
    public LoanProfilePage lenderProfilePage() {

        BankEntity bank = userService.getBankDetails();
        LoanProfilePage loanProfilePage = new LoanProfilePage();
        List<ProfileVO> profileVOs = new ArrayList<>();
        List<LoanVO> loans = this.getLoans();
//        for (LoanVO loan :  loans) {
//            if(loan.getLoanStatus().equals(LoanStatus.NEW)) {
//                ProfileVO profileVO = loan.getProfileVO();
//                loan.setProfileVO(null);
//                profileVO.setLoanVO(loan);
//                profileVOs.add(profileVO);
//            }
//        }
        loanProfilePage.setProfileVOs(profileVOs);

        LoanSummary loanSummary = new LoanSummary();
        loanSummary.setTotalLoanAmount(loanRepository.sumTotalLoanByBank(bank));

        ExcecutiveLoanCountVO excecutiveLoanCountVO = this.loanStatusByBank(bank);
        List<LoanCountVO> countVOs  = excecutiveLoanCountVO.getResult();

//        loanSummary.setApproved(loans.stream().filter(l->l.getLoanStatus().equals(LoanStatus.APPROVE)).count());

        ArrayList<String> loanStatus = new ArrayList<String>();
        loanStatus.add(LoanStatus.DISBURSE.toString());
        List<LoanVO> profileList = this.getLoans();
//        loanSummary.setTotalBorrower((long) loans.stream().filter(l->l.getLoanStatus().equals(LoanStatus.DISBURSE)).count());
        loanProfilePage.setLoanSummary(loanSummary);
        return loanProfilePage;
    }


    public ExcecutiveLoanCountVO loanStatusByBank(BankEntity bank) {
        ExcecutiveLoanCountVO excecutiveLoanCountVO = new ExcecutiveLoanCountVO();
        excecutiveLoanCountVO.setError("");
        excecutiveLoanCountVO.setType("year");
        List<LoanCountVO> loanCountVOList = new ArrayList<LoanCountVO>();
        List<Object[]> objects = new ArrayList<Object[]>();

        objects = loanRepository.getCountAllByBank(bank.getId());
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
    public List<ProfileVO> profileListByBankStatus(LoanStatus loanStatus){
//        BankEntity exe = userService.getBankDetails();
//
//        List<ProfileVO> profileVOs = new ArrayList<ProfileVO>();
//        List<Object[]> objects = new ArrayList<Object[]>();
//        if (loanStatus == null) {
//            List<LoanEntity> loanEntities = loanRepository.findByBank(exe);
//            for (LoanEntity loanEntity : loanEntities) {
//                ProfileVO profileVO = loanEntity.getProfile().toVo();
//                profileVO.setLoanVO(loanEntity.toVo());
//                profileVOs.add(profileVO);
//            }
//
//        } else {
//            List<LoanVO> loans = this.getLoans();
//
//            if (loanStatus.equals(LoanStatus.DISBURSE)) {
//                loans = loans.stream().filter(l -> l.getLoanStatus().equals(LoanStatus.DISBURSE)).collect(Collectors.toList());
//            } else if (loanStatus.equals(LoanStatus.HOLD)) {
//                loans = loans.stream().filter(l -> l.getLoanStatus().equals(LoanStatus.HOLD)).collect(Collectors.toList());
//            } else if (loanStatus.equals(LoanStatus.REJECT)) {
//                loans = loans.stream().filter(l -> l.getLoanStatus().equals(LoanStatus.REJECT)).collect(Collectors.toList());
//            } else if (loanStatus.equals(LoanStatus.INPROGRESS)) {
//                loans = loans.stream().filter(l -> l.getLoanStatus().equals(LoanStatus.INPROGRESS)).collect(Collectors.toList());
//            } else if (loanStatus.equals(LoanStatus.APPROVE)) {
//                loans = loans.stream().filter(l -> l.getLoanStatus().equals(LoanStatus.APPROVE)).collect(Collectors.toList());
//            } else if (loanStatus.equals(LoanStatus.APPLY)) {
//                loans = loans.stream().filter(l -> l.getLoanStatus().equals(LoanStatus.APPLY)).collect(Collectors.toList());
//            } else if (loanStatus.equals(LoanStatus.NEW)) {
//                loans = loans.stream().filter(l -> l.getLoanStatus().equals(LoanStatus.NEW)).collect(Collectors.toList());
//            }
//
//            for (LoanVO loan : loans) {
//                ProfileVO profileVO = loan.getProfileVO();
//                loan.setProfileVO(null);
//                profileVO.setLoanVO(loan);
//                profileVOs.add(profileVO);
//            }
//        }

        return null;
    }

    @Override
    public List<LoanVO> getLoanBydate(String startDate, String endDate) {
        BankEntity bank = userService.getBankDetails();
        LocalDateTime startD = dateFormatter(startDate).atTime(LocalTime.MIN);
        LocalDateTime endD = dateFormatter(endDate).atTime(LocalTime.MAX);
        return loanRepository
                .getLoanByDateAndBank(startD,endD,bank)
                .stream()
                .map(loan -> loan.toVo())
                .collect(Collectors.toList());
    }

    private LocalDate dateFormatter(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

}
