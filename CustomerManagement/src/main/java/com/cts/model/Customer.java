package com.cts.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("customer")
public class Customer {

    @Id
    @Column("customer_id")
    private Long customerId; // Do NOT use @GeneratedValue with R2DBC

    @NotBlank(message = "Name is mandatory")
    @Column("name")
    private String name;

    @Email(message = "Invalid email format")
    @Column("email")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    @Column("phone")
    private String phone;

    @Column("address")
    private String address;
}
