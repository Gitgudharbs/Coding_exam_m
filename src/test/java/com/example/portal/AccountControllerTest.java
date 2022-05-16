package com.example.portal;

import com.example.portal.account.Account;
import com.example.portal.account.AccountController;
import com.example.portal.account.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.example.portal.account.AccountType.Savings;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringJUnit4ClassRunner.class)
public class AccountControllerTest {

    @Mock
    AccountService accountService;

    @InjectMocks
    AccountController accountController;

    MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = standaloneSetup(accountController).build();
    }

    @Test
    public void createAccountTest() throws Exception {

        Account account = new Account(1,
                "John",
                "1234",
                "test@gmail.com",
                "test st",
                "manila",
                Savings);

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("customerNumber ", 1);
        map.put("transactionStatusCode", HttpStatus.CREATED.value());
        map.put("transactionStatusDescription", "Customer account created");
        ResponseEntity res = new ResponseEntity<Object>(map, HttpStatus.CREATED);

        Mockito.when(accountService.createAccount(account)).thenReturn(res);

        mockMvc.perform(MockMvcRequestBuilders.post("/account")
                .content("{\n" +
                        "    \"customerName\": \"John\",\n" +
                        "    \"customerMobile\": \"1234\",\n" +
                        "    \"customerEmail\": \"test@gmail.com\",\n" +
                        "    \"address1\": \"test st\",\n" +
                        "    \"accountType\": \"Savings\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getAccountTest() throws Exception {
        Account account = new Account(1,
                "John",
                "1234",
                "test@gmail.com",
                "test st",
                "manila",
                Savings);

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("Account", account);
        map.put("transactionStatusCode", HttpStatus.OK.value());
        map.put("transactionStatusDescription", "Customer account found");
        ResponseEntity res = new ResponseEntity<Object>(map, HttpStatus.OK);

        Mockito.when(accountService.getAccount(1)).thenReturn(res);

        mockMvc.perform(MockMvcRequestBuilders.get("/account/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{\n" +
                        "    \"Account\": {\n" +
                        "        \"customerNumber\": 1,\n" +
                        "        \"customerName\": \"John\",\n" +
                        "        \"customerMobile\": \"1234\",\n" +
                        "        \"customerEmail\": \"test@gmail.com\",\n" +
                        "        \"address1\": \"test st\",\n" +
                        "        \"address2\": \"manila\",\n" +
                        "        \"accountType\": \"Savings\"\n" +
                        "    },\n" +
                        "    \"transactionStatusCode\": 200,\n" +
                        "    \"transactionStatusDescription\": \"Customer account found\"\n" +
                        "}"));

    }

}
