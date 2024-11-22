package com.kilpi.finayo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kilpi.finayo.Domain.AssetInfo;

public interface AssetRepository extends JpaRepository<AssetInfo, Integer> {

}
