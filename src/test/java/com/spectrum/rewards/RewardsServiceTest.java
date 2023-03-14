package com.spectrum.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
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
import com.spectrum.rewards.model.RewardsDTO;
import com.spectrum.rewards.model.Transaction;
import com.spectrum.rewards.repository.CustomerRepository;
import com.spectrum.rewards.repository.TransactionRepository;
import com.spectrum.rewards.service.RewardsService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RewardsServiceTest {

    @Mock
    private TransactionRepository transactionRepo;

    @Mock
    private CustomerRepository customerRepo;

    @InjectMocks
    private RewardsService rewardsService;

    private Customer customer;
    private Transaction t1;
    private Transaction t2;
    private Transaction t3;
    private Transaction t4;

    @BeforeEach
    public void setup() {
        customer = new Customer(1, "testuser");

        t1 = new Transaction("cake", 50.0, rewardsService.getFirstDayOfXMonthsBehind(3), customer);
        t2 = new Transaction("aple", 75.0, rewardsService.getFirstDayOfXMonthsBehind(2), customer);
        t3 = new Transaction("phone", 100.0, rewardsService.getFirstDayOfXMonthsBehind(1), customer);
        t4 = new Transaction("tv", 150.0, rewardsService.getFirstDayOfXMonthsBehind(0), customer);
    }

    @Test
    public void testGetRewards() {
        List<Transaction> userTransactions = new ArrayList<Transaction>();
        userTransactions.add(t1);
        userTransactions.add(t2);
        userTransactions.add(t3);
        userTransactions.add(t4);

        Mockito.when(customerRepo.findById(1)).thenReturn(java.util.Optional.ofNullable(customer));
        Mockito.when(transactionRepo.findByCustomer(customer)).thenReturn(userTransactions);

        RewardsDTO rewardsDTO = rewardsService.getRewards(1);

        List<Integer> expectedPoints = new ArrayList<Integer>();
        expectedPoints.add(25); // first month
        expectedPoints.add(50); // second month
        expectedPoints.add(150); // third month

        assertEquals("testuser", rewardsDTO.getCustomer().getName());
        assertEquals(expectedPoints, rewardsDTO.getMonths());
        
        // DTO tests
        Customer testC = new Customer(2, "Jorge");
        rewardsDTO.setCustomer(testC);
        List<Integer> otherPoints = new ArrayList<Integer>();
        otherPoints.add(10);
        otherPoints.add(20);
        otherPoints.add(30);
        rewardsDTO.setMonths(otherPoints);
        assertEquals(testC, rewardsDTO.getCustomer());
        assertEquals(otherPoints, rewardsDTO.getMonths());
    }

    @Test
    public void testGetRewardsWithUnknownUser() {
        Mockito.when(customerRepo.findById(1)).thenReturn(java.util.Optional.empty());

        assertThrows(RuntimeException.class, () -> rewardsService.getRewards(1));
    }

    @Test
    public void testCalculatePoints() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(t1);
        transactions.add(t2);
        transactions.add(t3);
        transactions.add(t4);

        List<Integer> expectedPoints = new ArrayList<Integer>();
        expectedPoints.add(25); // first month
        expectedPoints.add(50); // second month
        expectedPoints.add(150); // third month

        List<Integer> points = rewardsService.calculatePoints(transactions);

        assertEquals(expectedPoints, points);
    }
}