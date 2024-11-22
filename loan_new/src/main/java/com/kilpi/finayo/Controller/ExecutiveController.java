package com.kilpi.finayo.Controller;


import com.kilpi.finayo.Constant.LoanStatus;
import com.kilpi.finayo.Service.ExecutiveService;
import com.kilpi.finayo.Service.UserService;
import com.kilpi.finayo.VO.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "executive")
public class ExecutiveController {

    @Autowired
    private ExecutiveService executiveService;
    
    @Autowired
    private UserService userService;

    @GetMapping(value = "/loans/status")
    public ResponseVO loans(@RequestParam(value = "status", required = true) String loanStatus,
                            @RequestParam(value = "id", required = false) Integer executiveId){
        try {
            return ResponseVO.builder()
                    .data(executiveService.getLoans(LoanStatus.valueOf(loanStatus), executiveId))
                    .status(200)
                    .message("Executive Loan List")
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseVO.builder()
                    .status(200)
                    .error(e.getLocalizedMessage())
                    .build();
        }
    }

    @PutMapping(value = "/loans/apply/{loanId}/{bCode}")
    public ResponseVO apply(@PathVariable Long loanId, @PathVariable String bCode) {
        try {
            return ResponseVO.builder()
                    .data(executiveService.updateLoan(loanId, bCode))
                    .status(200)
                    .message("Loan Application sent to bank")
                    .build();
        }
        catch (Exception e){

            return ResponseVO.builder()
                    .status(200)
                    .message(e.getLocalizedMessage())
                    .build();

        }
    }

    @GetMapping(value = "/stats/pie")
    public ResponseVO getStats() {
        try {
            return ResponseVO.builder()
                    .data(executiveService.getExecutiveStastistitics())
                    .status(200)
                    .message("Loan List")
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
                    .data(executiveService.getLoansByDate(startDate,endDate))
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
