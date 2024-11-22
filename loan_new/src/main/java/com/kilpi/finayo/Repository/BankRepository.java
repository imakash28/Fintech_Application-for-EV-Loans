package com.kilpi.finayo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kilpi.finayo.Domain.BankEntity;

import java.util.Optional;


@Repository
public interface BankRepository extends JpaRepository<BankEntity, Integer> {

	Optional<BankEntity> findByCode(String code);
	

}
