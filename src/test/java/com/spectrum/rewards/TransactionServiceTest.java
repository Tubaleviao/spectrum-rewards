package com.spectrum.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.spectrum.rewards.model.Customer;
import com.spectrum.rewards.model.Transaction;
import com.spectrum.rewards.repository.CustomerRepository;
import com.spectrum.rewards.repository.TransactionRepository;
import com.spectrum.rewards.service.RewardsService;
import com.spectrum.rewards.service.TransactionService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepo;

    @Mock
    private CustomerRepository customerRepo;

    @Mock
    private RewardsService rewardsService;

    @InjectMocks
    private TransactionService transactionService;

    private List<Transaction> transactions;

    @BeforeEach
    public void setup() {
        Customer alvaro = new Customer(1, "Alvaro");
        Customer ashley = new Customer(2, "Ashley");

        List<Date> months = new ArrayList<>();
        months.add(rewardsService.getFirstDayOfXMonthsBehind(3));
        months.add(rewardsService.getFirstDayOfXMonthsBehind(2));
        months.add(rewardsService.getFirstDayOfXMonthsBehind(1));

        transactions = new ArrayList<>();
        transactions.add(new Transaction("cake", 51.1, new Date(), alvaro));
        transactions.add(new Transaction("juice", 101.0, months.get(0), alvaro));
        transactions.add(new Transaction("sausage", 200.1, months.get(1), alvaro));
        transactions.add(new Transaction("guitar", 500.0, new Date(), ashley));
        transactions.add(new Transaction("boots", 150.0, months.get(2), ashley));
        transactions.add(new Transaction("apple", 200.1, months.get(1), ashley));
    }

    @Test
    public void testGetTransactions() {
        Mockito.when(transactionRepo.findAll()).thenReturn(transactions);
        List<Transaction> result = transactionService.getTransactions();
        assertEquals(transactions.size(), result.size());
    }

    @Test
    public void testGetTransactionsWithNoTransactions() {
        Mockito.when(transactionRepo.findAll()).thenReturn(new ArrayList<>());
        List<Transaction> result = transactionService.getTransactions();
        assertEquals(0, result.size());
    }

    @Test
    public void testGetTransactionsWithMockData() {
        List<Transaction> expected = new ArrayList<>();
        expected.add(transactions.get(0));
        Mockito.when(transactionRepo.findAll()).thenReturn(expected);

        List<Transaction> result = transactionService.getTransactions();
        assertEquals(expected.size(), result.size());
    }

    @Test
    public void testGetTransactionsWithUnknownCustomer() {
        List<Transaction> expected = new ArrayList<>();
        Mockito.when(transactionRepo.findAll()).thenReturn(expected);

        List<Transaction> result = transactionService.getTransactions();
        assertEquals(0, result.size());
    }

}