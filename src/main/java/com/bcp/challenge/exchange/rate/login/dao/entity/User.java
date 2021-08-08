package com.bcp.challenge.exchange.rate.login.dao.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table("users")
@Getter
@Setter
@Builder
public class User {

    @Id
    private UUID id;
    @Column("user_name")
    private String userName;
    private String password;
    private Boolean enabled;
    private String rol;


}
