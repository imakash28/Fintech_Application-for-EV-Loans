package com.kilpi.finayo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kilpi.finayo.Domain.*;

public interface ExecutiveRepository extends JpaRepository<ExecutiveEntity, Integer> {
	
	public Optional<ExecutiveEntity> findById(Integer id);
	
	@Query("select e from ExecutiveEntity e where e.firstName = ?1 and e.lastName = ?2")
    List<ExecutiveEntity> findbyFristLastName(String firstName,String lastName);
	
	List<ExecutiveEntity> findByBranch(BranchEntity branch);

	Optional<ExecutiveEntity> findByCode(String code);

}
