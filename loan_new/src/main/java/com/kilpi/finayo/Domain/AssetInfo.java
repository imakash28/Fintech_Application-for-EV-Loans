package com.kilpi.finayo.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kilpi.finayo.VO.AssetVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "asset_info")
public class AssetInfo {
	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer assetId;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "product_name")
    private String productName;
    
    @Column(name = "product_model")
    private String productModel;
    
    @Column(name = "manufacture_year")
    private String manufactureYear;
    
	@Column(name = "down_payment")
	private Double downPayment;
	
	@Column(name = "vehicle_amount")
	private Double vehicleAmount;

    
    public AssetVO toVo() {
        return AssetVO.builder()
                .assetId(assetId)
                .downPayment(downPayment)
                .manufactureYear(manufactureYear)
                .productModel(productModel)
                .productName(productName)
                .productType(productType)
                .vehicleAmount(vehicleAmount)
				.build();
    }

}



