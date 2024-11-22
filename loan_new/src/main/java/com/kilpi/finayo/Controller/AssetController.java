package com.kilpi.finayo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kilpi.finayo.Service.AssetService;
import com.kilpi.finayo.VO.AssetVO;
import com.kilpi.finayo.VO.ResponseVO;

import javax.validation.Valid;

@RestController
@RequestMapping("asset")
public class AssetController {
	
	@Autowired
	AssetService assetService;
	
	@PostMapping("/create")
	public ResponseVO createAsset(@Valid @RequestBody AssetVO assetVO) {
		return ResponseVO.builder()
				.data(assetService.createAsset(assetVO))
				.status(200)
				.message("Asset Created")
				.build();
	}

}
