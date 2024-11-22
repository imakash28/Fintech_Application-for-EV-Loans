package com.kilpi.finayo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kilpi.finayo.Domain.*;

public interface BranchRepository extends JpaRepository<BranchEntity, Integer> {

	public Optional<BranchEntity> findById(Integer id);

}
