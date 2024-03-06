package com.example.entity;

import com.example.utility.enums.eRole;
import com.example.utility.enums.eStatue;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Auth extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    private String username;
    @Email
    private String email;
    private String password;
    private String activationCode;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private eRole role = eRole.USER;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private eStatue status = eStatue.PENDING;




    }




