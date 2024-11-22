package com.kilpi.finayo.VO;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
public class BankVO {
	private Integer bankid;
	@NotNull
    @Size(min = 3, max = 25, message = "bank name must be of min 3 length")
	private String bankname;
	@NotNull
    @Digits(fraction = 5, integer = 2)
	private Double interestRate;
	@NotNull
    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$",message = "Invalid IFSC code")
	private String ifscCode;
	@NotNull
	private Double acptCibil;
	@NotEmpty
	private String code;
	@NotNull
    @Size(min = 5, max = 250, message = "address must be of min 5")
	private String address;
	@NotNull
	@Email(message = "Invalid Email")
	private String email;
	@NotEmpty
	private String status;

}
