package com.rest.retailerapi.service;
import com.rest.retailerapi.exception.CustomerNotFoundException;
import com.rest.retailerapi.model.Customer;
import com.rest.retailerapi.model.Rewards;
import com.rest.retailerapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.lang.NumberFormatException;
import java.time.ZoneId;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Rewards getRewards(int id) throws CustomerNotFoundException, NumberFormatException {
        Rewards rewards = new Rewards();
        LocalDate currentDate = LocalDate.now();
        int currentMonthValue = currentDate.getMonthValue();
        LocalDate threeMonthsAgo = currentDate.minusMonths(3);
        List<Customer> transactions = customerRepository.findLastThreeMonthsTransactions(id);
        HashMap<Integer, Integer> monthlyPoints = new HashMap<>();
        for (Customer transaction : transactions) {
            LocalDate transDate = transaction.getTransdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (transDate.isAfter(threeMonthsAgo)) {
                int month = transDate.getMonthValue();
                monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + calculatePoints(transaction.getPurchaseamt()));
            }
        }
        int[] monthlyRewards = new int[3];
        for (int i = 0; i < 3; i++) {
            int month = currentMonthValue - i;
            if (month <= 0) {
                month += 12;
            }
            if (monthlyPoints.containsKey(month)) {
                monthlyRewards[i] = monthlyPoints.get(month);
            }
        }
        int totalRewards = Arrays.stream(monthlyRewards).sum();
        rewards.setCustId(id);
        rewards.setTotalRewards(totalRewards);
        rewards.setRewardsMonth1(monthlyRewards[0]);
        rewards.setRewardsMonth2(monthlyRewards[1]);
        rewards.setRewardsMonth3(monthlyRewards[2]);

        return rewards;

    }

   /* @Override
    public Rewards getRewards(int id) throws CustomerNotFoundException {

        Customer customer;
        customer = customerRepository.findCustomers(id);
                //.orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));


        Rewards rewards = new Rewards();
        LocalDate currentDate = LocalDate.now();
        int currentMonthValue = currentDate.getMonthValue();
        LocalDate threeMonthsAgo = currentDate.minusMonths(3);
        List<Customer> transactions = customerRepository.findLastThreeMonthsTransactions(id);
        HashMap<Integer, Integer> monthlyPoints = new HashMap<>();
        for (Customer transaction : transactions) {
            LocalDate transDate = transaction.getTransdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (transDate.isAfter(threeMonthsAgo)) {
                int month = transDate.getMonthValue();
                monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + calculatePoints(transaction.getPurchaseamt()));
            }
        }
        int[] monthlyRewards = new int[3];
        for (int i = 0; i < 3; i++) {
            int month = currentMonthValue - i;
            if (month <= 0) {
                month += 12;
            }
            if (monthlyPoints.containsKey(month)) {
                monthlyRewards[i] = monthlyPoints.get(month);
            }
        }
        int totalRewards = Arrays.stream(monthlyRewards).sum();
        rewards.setTotalRewards(totalRewards);
        rewards.setRewardsMonth1(monthlyRewards[0]);
        rewards.setRewardsMonth2(monthlyRewards[1]);
        rewards.setRewardsMonth3(monthlyRewards[2]);

        return rewards;
    }*/

    private int calculatePoints(double transactionAmount) {
        int rewards = 0;
        if (transactionAmount > 100) {
            rewards = 2 * (int)(transactionAmount-100) + 50;
        }
        else if(transactionAmount>50){
            rewards = (int)(transactionAmount - 50);
        }
        return rewards;
    }
}
