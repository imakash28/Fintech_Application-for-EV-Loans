package com.kilpi.finayo.Constant;

import java.util.Arrays;
import java.util.List;

public class  Constant {
	
	public final static List<LoanStatus> INPROGRESS = Arrays.asList(LoanStatus.INPROGRESS);
	public final static List<LoanStatus> APPROVE = Arrays.asList(LoanStatus.APPROVE);
	public final static List<LoanStatus> NEW = Arrays.asList(LoanStatus.NEW);
	public final static List<LoanStatus> APPLY = Arrays.asList(LoanStatus.APPLY);
	public final static List<LoanStatus> COMPLETED = Arrays.asList(LoanStatus.DISBURSE);
	public final static List<LoanStatus> REJECT = Arrays.asList(LoanStatus.REJECT);
	public final static List<LoanStatus> HOLD = Arrays.asList(LoanStatus.HOLD);
	public final static List<LoanStatus> DISBURS = Arrays.asList(LoanStatus.DISBURSE);
	public final static List<LoanStatus> PENDING = Arrays.asList(LoanStatus.HOLD,LoanStatus.REJECT);
	public final static List<String> NOT_DISBURSH = Arrays.asList(LoanStatus.REJECT.toString()
			,LoanStatus.REJECT.toString(),LoanStatus.APPLY.toString());
	
	public List<String> inprogrss_setps =  Arrays.asList(LoanStatus.HOLD.toString()
			,LoanStatus.REJECT.toString(),LoanStatus.DISBURSE.toString());
	public List<String> hold_setps =  Arrays.asList(LoanStatus.REJECT.toString(),LoanStatus.DISBURSE.toString());
	

	

	
}
