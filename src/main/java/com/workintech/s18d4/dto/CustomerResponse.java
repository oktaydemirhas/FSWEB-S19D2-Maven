package com.workintech.s18d4.dto;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.entity.Account;

import java.util.List;

public record CustomerResponse(
    Long id,
    String firstName,
    String lastName,
    String email,
    Double salary,
    Address address,
    List<Account> accounts
) {} 