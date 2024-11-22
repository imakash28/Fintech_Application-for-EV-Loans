package com.kilpi.finayo.Controller;


import com.kilpi.finayo.Domain.UserEntity;
import com.kilpi.finayo.Repository.UserRepository;
import com.kilpi.finayo.Service.ExecutiveService;
import com.kilpi.finayo.Service.LenderService;
import com.kilpi.finayo.VO.LoanStatsVO;
import com.kilpi.finayo.VO.LoanVO;
import com.kilpi.finayo.VO.ReportVO;
import com.kilpi.finayo.VO.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "lender")
public class BankController {

    @Autowired
    private LenderService lenderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExecutiveService executiveService;

    @GetMapping(value = "/stats/pie")
    public ResponseVO getStats() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User map = (User) auth.getPrincipal();
        UserEntity user = userRepository.findByUsername(map.getUsername());

        return ResponseVO.builder()
                .data(user.getRole().equalsIgnoreCase("LENDER")?lenderService.getLenderStastistitics():executiveService.getExecutiveStastistitics())
                .status(200)
                .message("Loan List")
                .build();

    }


    @GetMapping(value = "/stats/bar")
    public ResponseVO getLoanReport(HttpServletRequest request) {
        return ResponseVO.builder()
                .data(lenderService.lenderReport())
                .status(200)
                .message("Loan List")
                .build();
    }

    @GetMapping(value = "/loans/list")
    public ResponseVO getLoanList() {
        return ResponseVO.builder()
                .data(lenderService.getLoans())
                .status(200)
                .message("Loan List")
                .build();
    }

    @GetMapping(value = "/loan")
    public ResponseVO getExecutiveLoan(@RequestParam(name = "from")String startDate,
                                       @RequestParam(name = "to")String endDate) {
        return ResponseVO.builder()
                .data(lenderService.getLoanBydate(startDate,endDate))
                .status(200)
                .message("Date wise Loan")
                .build();
    }

}
