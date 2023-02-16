package com.rest.retailerapi.service;
import com.rest.retailerapi.exception.CustomerNotFoundException;
import com.rest.retailerapi.model.Rewards;
public interface CustomerService {
    Rewards getRewards(int id) throws CustomerNotFoundException;
}

