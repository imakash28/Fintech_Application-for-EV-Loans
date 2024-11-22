package com.kilpi.finayo.Controller;

import com.kilpi.finayo.Constant.LoanStatus;
import com.kilpi.finayo.Constant.ReportType;
import com.kilpi.finayo.Service.LoanService;
import com.kilpi.finayo.VO.ReportVO;
import com.kilpi.finayo.VO.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("finayo")
public class FinayoController {

    @Autowired
    LoanService loanService;

    @GetMapping(value = "/loans/status")
    public ResponseVO loans(@RequestParam(value = "status", required = true) LoanStatus loanStatus){
        try {
            return ResponseVO.builder()
                    .data(loanService.getLoansByStatus(loanStatus))
                    .status(200)
                    .message("Executive Loan List")
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
                    .data(loanService.getLoanByDate(startDate,endDate))
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

    @GetMapping(value = "/loan/dsa")
    public ResponseVO getExecutiveLoan(@RequestParam(name = "id")Integer id) {
        try {
            return ResponseVO.builder()
                    .data(loanService.getLoansByDSA(id))
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
