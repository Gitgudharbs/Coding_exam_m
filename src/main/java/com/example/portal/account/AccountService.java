package com.example.portal.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<Object> getAccount(long id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
            map.put("Account", account);
            map.put("transactionStatusCode", HttpStatus.OK.value());
            map.put("transactionStatusDescription", "Customer account found");
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        } catch (Exception e) {
            map.put("transactionStatusCode", HttpStatus.NOT_FOUND.value());
            map.put("transactionStatusDescription", "Customer not found");
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> createAccount(Account account) {
        accountRepository.save(account);
        Account res =  accountRepository.save(account);

        Map<String, Object> map = new LinkedHashMap <String, Object>();
        map.put("customerNumber ", res.getCustomerNumber());
        map.put("transactionStatusCode", HttpStatus.CREATED.value());
        map.put("transactionStatusDescription", "Customer account created");
        return new ResponseEntity<Object>(map, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new LinkedHashMap <>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.put("transactionStatusCode", HttpStatus.BAD_REQUEST.value());
            errors.put("transactionStatusDescription", errorMessage);
        });
        return errors;
    }
}