package com.example.portal.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    private long customerNumber;

    @NotBlank(message = "Name is required field")
    private String customerName;

    @NotBlank(message = "Mobile is required field")
    private String customerMobile;

    @Email
    @NotBlank(message = "Email is required field")
    private String customerEmail;

    @NotBlank(message = "Address is required field")
    private String address1;

    private String address2;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

}