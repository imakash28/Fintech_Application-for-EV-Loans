package com.kilpi.finayo.Service;

import com.kilpi.finayo.Domain.BankEntity;
import com.kilpi.finayo.Domain.DSAEntity;
import com.kilpi.finayo.Domain.ExecutiveEntity;
import com.kilpi.finayo.Domain.UserEntity;

public interface UserService {
   
	ExecutiveEntity getExecutive();

    BankEntity getBankDetails();

    DSAEntity getDsaDetails();
    
    String getRoleFromAuth();
    
    public UserEntity getUser();
}
