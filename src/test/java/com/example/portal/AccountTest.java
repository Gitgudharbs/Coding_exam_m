package com.example.portal;

import com.example.portal.account.Account;
import org.junit.jupiter.api.Test;

import static com.example.portal.account.AccountType.Savings;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    Account account = new Account(1,
            "John",
            "1234",
            "test@gmail.com",
            "test st",
            "manila",
            Savings);

    @Test
    public void whenCalledGetAccount_thenCorrect() {
        assertThat(account.getCustomerName()).isEqualTo("John");
        assertThat(account.getCustomerMobile()).isEqualTo("1234");
        assertThat(account.getCustomerEmail()).isEqualTo("test@gmail.com");
        assertThat(account.getAddress1()).isEqualTo("test st");
        assertThat(account.getAddress2()).isEqualTo("manila");
        assertThat(account.getAccountType()).isEqualTo(Savings);
    }

}
