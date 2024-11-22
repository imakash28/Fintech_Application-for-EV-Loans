package com.kilpi.finayo.Domain;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "branch")
public class BranchEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;

    @Column(name = "code")
    String code;

    @Column(name = "first_name")
    String name;

    @Column(name = "mobile")
    String mobile;

    @Column(name = "address")
    String address;

    @Column(name = "city")
    String city;

    @ManyToOne
    @JoinColumn(
            name = "business_type_id",
            referencedColumnName = "id"
    )
    DSAEntity dsa;

    @Column(name = "active")
    Boolean active;

    @ManyToMany
    List<ShowroomTypeEntity> showroomTypes;

    @OneToMany
    List<ExecutiveEntity> executiveEntities;

}
