package com.kilpi.finayo.Domain;

import com.kilpi.finayo.VO.UserVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "uid")
    private String uniqueId;

    @Column(name = "email")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @ManyToOne
    private DSAEntity company;

    @Column(name = "role")
    private String role;

    @Column(name = "is_active")
    private Boolean active;

    public UserVO toVo(){
        return UserVO.builder()
                .fname(firstName)
                .lname(lastName)
                .uniqueId(uniqueId)
                .email(username)
                .mobile(mobile)
                .city(city)
                .address(address)
                .role(role)
                .btype(company.getCategory())
                .company(company.getName())
                .role(role)
                .build();
    }
}
