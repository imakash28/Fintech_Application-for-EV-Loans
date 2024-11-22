package com.kilpi.finayo.VO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserVO {
    private String fname;
    private String lname;
    private String uniqueId;
    private String password;
    private String email;
    private String mobile;
    private String address;
    private String city;
    private String company;
    private String btype;
    private String role;
}
