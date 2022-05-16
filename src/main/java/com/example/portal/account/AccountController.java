package com.example.portal.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAccount(@PathVariable("id") long id) {
        return accountService.getAccount(id);
    }

    @PostMapping
    public ResponseEntity<Object> createAccount(@Valid @RequestBody Account account) {
        return accountService.createAccount(account);
    }

}