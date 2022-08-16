package com.example.houserentingsystem.model.admin;


import com.example.houserentingsystem.enums.Gender;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "admin_register", uniqueConstraints = {
        @UniqueConstraint(name = "unique_Register_email", columnNames = "email"),
        @UniqueConstraint(name = "unique_Register_contact", columnNames = "contact")
})
public class AdminRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Register_seq_gen")
    @SequenceGenerator(name = "Register_seq_gen", sequenceName = "Register_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @NotEmpty
    @Column(name = "email", nullable = false)
    private String email;

    @NotEmpty
    @Column(name = "contact", nullable = false)
    private String contact;

    @NotEmpty
    @Column(name = "id_number", nullable = false)
    private String idNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;


}
