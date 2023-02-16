package com.rest.retailerapi.controller;
import com.rest.retailerapi.exception.CustomerNotFoundException;
import com.rest.retailerapi.model.Customer;
import com.rest.retailerapi.model.Rewards;
import com.rest.retailerapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class controller {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/")
    public String home() {
        return "Usage: Give 'localhost:8080/rewards/{custid}' -> To display Rewards information of the customer";
    }
    /*@GetMapping("/rewards/{id}")
    public Rewards getRewards(@PathVariable String id) throws CustomerNotFoundException, NumberFormatException {
        return customerService.getRewards(id);
    }*/

    @GetMapping("/rewards/{id}")
public String getRewards(@PathVariable String id) throws CustomerNotFoundException {
    try {
        int customerId = Integer.parseInt(id);
        return customerService.getRewards(customerId).toString();
    } catch (NumberFormatException ex) {

        return "Invalid customer id <br> Check the customer ID";


    }
}

}
