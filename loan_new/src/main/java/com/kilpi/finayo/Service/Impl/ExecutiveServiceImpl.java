package com.kilpi.finayo.Service.Impl;

import com.kilpi.finayo.Constant.LoanStatus;
import com.kilpi.finayo.Domain.*;
import com.kilpi.finayo.Domain.LoanEntity;
import com.kilpi.finayo.Repository.*;
import com.kilpi.finayo.Service.ExecutiveService;
import com.kilpi.finayo.Service.UserService;
import com.kilpi.finayo.VO.LoanStatsVO;
import com.kilpi.finayo.VO.LoanVO;
import com.kilpi.finayo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExecutiveServiceImpl implements ExecutiveService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    BankRepository bankRepository;

    @Autowired
    ExecutiveRepository executiveRepository;

    @Autowired
    UserService userService;

    @Override
    public List<LoanVO> getLoans(LoanStatus status, Integer executiveId) {
        ExecutiveEntity executive;
        if(executiveId != null) {
            executive = executiveRepository.findById(executiveId).get();
        } else {
            executive = userService.getExecutive();
        }

        List<LoanEntity> resp = new ArrayList<>();
        if(status.equals(LoanStatus.APPROVE)) {
            List<LoanEntity> loanEntities = loanRepository.findByExecutiveIdAndStatus(executive.getId(), LoanStatus.NEW);
            for (LoanEntity loanEntity : loanEntities) {
                if (!loanEntity.getBanks().isEmpty()) {
                    for(BankLoanEntity ln: loanEntity.getBanks()){
                        if(ln.getStatus().equalsIgnoreCase(LoanStatus.APPROVE.toString()) ){
                            resp.add(loanEntity);
                        }
                    };
                }
            }
        } else if(status.equals(LoanStatus.NEW) ) {
            List<LoanEntity> loanEntities = loanRepository.findByExecutiveIdAndStatus(executive.getId(), LoanStatus.NEW);
            for (LoanEntity loanEntity : loanEntities) {
                if (loanEntity.getBanks().isEmpty()) {
                    resp.add(loanEntity);
                }
            }
        } else {
            List<LoanEntity> loanEntities = loanRepository.findByExecutiveIdAndStatus(executive.getId(), status);
            resp = loanEntities;
        }
        return resp.stream().map(LoanEntity::toVo).collect(Collectors.toList()) ;
    }

    @Override
    public LoanVO updateLoan(Long loanId, String bCode) {
        ExecutiveEntity executive = userService.getExecutive();
        Optional<LoanEntity> isLoan = loanRepository.findById(loanId);
        Optional<BankEntity> isBank = bankRepository.findByCode(bCode);
        if(!isBank.isPresent()){
            throw new NotFoundException("Invalid Bank");
        }
        if(isLoan.isPresent()){
            LoanEntity loanEntity = isLoan.get();
//            if(executive.getId() != loanEntity.getProfile().getExecutive().getId()){
//                throw new NotFoundException("Invalid Loan for executive");
//            }
//            loanEntity.setBank(isBank.get());
//            loanEntity.setLoanStatus(LoanStatus.APPLY);
//            loanEntity.setUpdatedby(LocalDateTime.now());

            for (BankLoanEntity bankLoan : loanEntity.getBanks() ) {
                if(isBank.get().getId() == bankLoan.getBank().getId())
                    bankLoan.setStatus(LoanStatus.APPLY.toString());
            }

            return loanRepository.save(loanEntity).toVo();
        } else {
            throw new NotFoundException("Invalid Loan");
        }
    }

    @Override
    public LoanStatsVO getExecutiveStastistitics() {
//        LoanStatsVO loanStatsVO = null;
//        loanStatsVO = new LoanStatsVO();
//
//        ExecutiveEntity executive = userService.getExecutive();
//
//        List<LoanEntity> loanEntities = loanRepository.findByExecutiveId(executive.getId());
//
//        Long total = (long) loanEntities.size();
//        Long disburse = 0L, pending = 0L, reject = 0L, inProgress = 0L, approve = 0L, apply = 0L, newL = 0L;
//
//        for (LoanEntity loanEntity : loanEntities) {
//            if (loanEntity.getLoanStatus().equals(LoanStatus.DISBURSE)) {
//                disburse++;
//            } else if (loanEntity.getLoanStatus().equals(LoanStatus.HOLD)) {
//                pending++;
//            } else if (loanEntity.getLoanStatus().equals(LoanStatus.REJECT)) {
//                reject++;
//            } else if (loanEntity.getLoanStatus().equals(LoanStatus.INPROGRESS)) {
//                inProgress++;
//            } else if (loanEntity.getLoanStatus().equals(LoanStatus.APPROVE)) {
//                approve++;
//            } else if (loanEntity.getLoanStatus().equals(LoanStatus.APPLY)) {
//                apply++;
//            } else if (loanEntity.getLoanStatus().equals(LoanStatus.NEW)) {
//                newL++;
//            }
//        }
//
//        loanStatsVO.setTotal( total == null ? 0 : total);
//        loanStatsVO.setDisburse( disburse == null ? 0 : disburse);
//        loanStatsVO.setPending( pending == null ? 0 : pending);
//        loanStatsVO.setReject(reject == null ? 0 : reject);
//        loanStatsVO.setApply(apply == null ? 0 : apply);
//        loanStatsVO.setApprove(approve == null ? 0 : approve);
//        loanStatsVO.setNewLoan(newL == null ? 0 : newL);
//        loanStatsVO.setInProgress(inProgress == null ? 0 : inProgress);
        return null;
    }

    @Override
    public List<LoanVO> getLoansByLoanId(Integer loanId, Integer executiveId) {
        return null;
    }

    @Override
    public List<LoanVO> getLoansByDate(String startDate, String endDate) {
            ExecutiveEntity executive = userService.getExecutive();
            LocalDateTime startD = dateFormatter(startDate).atTime(LocalTime.MIN);
            LocalDateTime endD = dateFormatter(endDate).atTime(LocalTime.MAX);
            List<LoanEntity> loanEntityRes = loanRepository.getLoanByExecutiveDate(executive.getId(), startD, endD);
            return loanEntityRes
                    .stream()
                    .map(loan -> loan.toVo())
                    .collect(Collectors.toList());
    }

    private LocalDate dateFormatter(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }


}
