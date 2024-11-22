package com.kilpi.finayo.Repository;

import com.kilpi.finayo.Domain.BankEntity;
import com.kilpi.finayo.Domain.DSAEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DsaRepository extends JpaRepository<DSAEntity, Integer> {

	Optional<DSAEntity> findByCode(String code);
	

}
