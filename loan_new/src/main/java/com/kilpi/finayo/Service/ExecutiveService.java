package com.kilpi.finayo.Service;

import com.kilpi.finayo.Constant.LoanStatus;
import com.kilpi.finayo.VO.LoanStatsVO;
import com.kilpi.finayo.VO.LoanVO;

import java.time.LocalDateTime;
import java.util.List;

public interface ExecutiveService {

    List<LoanVO> getLoans(LoanStatus status, Integer executiveId);

    LoanVO updateLoan(Long loanId, String bCode);

    public LoanStatsVO getExecutiveStastistitics();

    List<LoanVO> getLoansByLoanId(Integer loanId,Integer executiveId);

    List<LoanVO> getLoansByDate(String startDate,
                                String endDate);


}
