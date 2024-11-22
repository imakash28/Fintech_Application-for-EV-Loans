package com.kilpi.finayo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kilpi.finayo.Domain.Loan.BankInfo;

public interface BankInfoRepository extends JpaRepository<BankInfo, Integer> {

}
