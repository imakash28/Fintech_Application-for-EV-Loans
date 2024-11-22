package com.kilpi.finayo.Domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "showroom_type")
public class ShowroomTypeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value")
    private String value;

    @Column(name = "label")
    private String label;

}

