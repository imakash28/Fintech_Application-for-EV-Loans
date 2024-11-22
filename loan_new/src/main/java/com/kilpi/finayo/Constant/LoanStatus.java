package com.kilpi.finayo.Constant;

import java.util.Optional;



public enum LoanStatus {
	NEW,APPLY, INPROGRESS, HOLD, REJECT, APPROVE, DISBURSE;

	public static Optional<LoanStatus> fromString(String value) {
        try {
            return Optional.of(LoanStatus.valueOf(value.toUpperCase()));
        }catch(Exception e) {
            return Optional.empty();
        }
    }
}
