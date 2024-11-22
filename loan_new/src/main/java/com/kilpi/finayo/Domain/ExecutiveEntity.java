package com.kilpi.finayo.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "executive")
public class ExecutiveEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email")
    String email;

    @Column(name = "mobile")
    String mobile;

    @Column(name = "address")
    String address;

    @Column(name = "city")
    String city;

    @Column(name = "code")
    String code;

    @Column(name = "active")
    Boolean active;

    @ManyToOne
    BranchEntity branch;

   
}
