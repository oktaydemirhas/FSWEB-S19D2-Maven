package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;

    // Test kodunda kullanılan tek parametreli constructor
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.customerService = null; // Test için null olabilir
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
    }

    @Override
    public Account save(Long customerId, Account account) {
        Customer customer = customerService.findById(customerId);
        account.setCustomer(customer);
        return accountRepository.save(account);
    }

    @Override
    public Account update(Long customerId, Account account) {
        Customer customer = customerService.findById(customerId);
        Account existingAccount = findById(account.getId());
        existingAccount.setAccountName(account.getAccountName());
        existingAccount.setMoneyAmount(account.getMoneyAmount());
        existingAccount.setCustomer(customer);
        return accountRepository.save(existingAccount);
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    // Test kodunda kullanılan find metodu
    public Account find(Long id) {
        return findById(id);
    }

    // Test kodunda kullanılan tek parametreli save metodu
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    // Test kodunda kullanılan delete metodu - Customer döndürür
    public Customer deleteAndReturn(Long id) {
        Optional<Account> accountOpt = accountRepository.findById(id);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            accountRepository.deleteById(id);
            return account.getCustomer();
        }
        return null;
    }
} 