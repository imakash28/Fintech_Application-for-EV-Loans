package com.kilpi.finayo.Domain;

import com.kilpi.finayo.Domain.BusinessTypeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "dsa")
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE dsa SET active = false WHERE id=?")
@Where(clause = "active=true")
public class DSAEntity {
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "name")
        private String name;

        @Column(name = "unique_name")
        private String uniqueName;

        @Column(name = "code")
        private String code;

        @Column(name = "email")
        private String email;

        @Column(name = "category")
        private String category;

        @Column(name = "city")
        private String city;

        @Column(name = "address")
        private String address;

        @Column(name = "mobile")
        private String mobile;

        @Column(name = "active")
        private Boolean active = Boolean.TRUE;


        @ManyToOne
        @JoinColumn(
                name = "businesstype_id",
                referencedColumnName = "id"
        )
        private BusinessTypeEntity businessType;

        @Column(name = "gstin")
        private String gstin;
}
