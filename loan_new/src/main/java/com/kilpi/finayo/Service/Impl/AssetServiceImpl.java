package com.kilpi.finayo.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kilpi.finayo.Domain.AssetInfo;
import com.kilpi.finayo.Repository.AssetRepository;
import com.kilpi.finayo.Service.AssetService;
import com.kilpi.finayo.VO.AssetVO;

@Service
public class AssetServiceImpl implements AssetService {
	
	@Autowired
	AssetRepository assetRepository;

	@Override
	public AssetVO createAsset(AssetVO assetVO) {
		
		AssetInfo asset = new AssetInfo();
		asset.setDownPayment(assetVO.getDownPayment());
		asset.setManufactureYear(assetVO.getManufactureYear());
		asset.setProductModel(assetVO.getProductModel());
		asset.setProductName(assetVO.getProductName());
		asset.setProductType(assetVO.getProductType());
		asset.setVehicleAmount(assetVO.getVehicleAmount());
		return assetRepository.save(asset).toVo();
	}

}
