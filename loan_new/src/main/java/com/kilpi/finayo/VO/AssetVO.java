package com.kilpi.finayo.VO;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssetVO {
	
	    private Integer assetId;
		@NotEmpty
	    private String productType;
	    @NotEmpty
	    private String productName;
		@NotEmpty
	    private String productModel;
		@NotEmpty
	    private String manufactureYear;
		@NotNull
	    private Double downPayment;
		@NotNull
		private Double vehicleAmount;
}
