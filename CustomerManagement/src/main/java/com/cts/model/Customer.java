package com.cts.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Table("customer")
public class Customer {

    @NotNull
    @Id
    @Column("customer_id")
    private Long customerId;

    @NotBlank
    @Column("name")
    private String name;

    @Email
    @Column("email")
    private String email;

    @Pattern(regexp = "\\d{10}")
    @Column("phone")
    private String phone;

    @Column("address")
    private String address;
}
