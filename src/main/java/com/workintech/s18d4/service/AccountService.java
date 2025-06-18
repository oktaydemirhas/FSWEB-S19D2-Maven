package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account findById(Long id);
    Account save(Long customerId, Account account);
    Account update(Long customerId, Account account);
    void delete(Long id);
} 