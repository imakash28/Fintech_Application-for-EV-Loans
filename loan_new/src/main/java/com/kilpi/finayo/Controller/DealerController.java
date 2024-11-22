package com.kilpi.finayo.Controller;

import com.kilpi.finayo.Constant.LoanStatus;
import com.kilpi.finayo.Service.LoanService;
import com.kilpi.finayo.VO.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dsa")
public class DealerController {

    Logger logger = LoggerFactory.getLogger(FinayoController.class);

    @Autowired
    LoanService loanService;

    @GetMapping(value = "/loan/{status}")
    public ResponseVO getYearLoan(@PathVariable(name = "status")LoanStatus status) {
        try {
            return ResponseVO.builder()
                     .data(loanService.getLoanStatusAdmin(status))
                     .status(200)
                     .message("Status wise Loan")
                     .build();
        } catch (Exception e) {
            return ResponseVO.builder()
                    .status(200)
                    .error(e.getLocalizedMessage())
                    .build();
        }
    }

    @GetMapping(value = "/loan/branch/{id}")
    public ResponseVO getBranchLoan(@PathVariable(name = "id")Integer id) {
        try {
            return ResponseVO.builder()
                    .data(loanService.getbranchLoanAdmin(id))
                    .status(200)
                    .message("Branch Loan")
                    .build();
        } catch (Exception e) {
            return ResponseVO.builder()
                    .status(200)
                    .error(e.getLocalizedMessage())
                    .build();
        }
    }

    @GetMapping(value = "/loan/executive/{id}")
    public ResponseVO getExecutiveLoan(@PathVariable(name = "id")Integer id) {
        try {
            return ResponseVO.builder()
                    .data(loanService.getExecutiveLoanAdmin(id))
                    .status(200)
                    .message("Executive Loan")
                    .build();
        } catch (Exception e) {
            return ResponseVO.builder()
                    .status(200)
                    .error(e.getLocalizedMessage())
                    .build();

        }
    }

    @GetMapping(value = "/loan")
    public ResponseVO getExecutiveLoan(@RequestParam(name = "from")String startDate,
                                       @RequestParam(name = "to")String endDate) {
        try {
            return ResponseVO.builder()
                    .data(loanService.getLoanDateAdmin(startDate,endDate))
                    .status(200)
                    .message("Date wise Loan")
                    .build();
        } catch (Exception e) {
            return ResponseVO.builder()
                    .status(200)
                    .error(e.getLocalizedMessage())
                    .build();
        }
    }

}
