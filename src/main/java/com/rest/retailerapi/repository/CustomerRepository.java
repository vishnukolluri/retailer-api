package com.rest.retailerapi.repository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.rest.retailerapi.exception.CustomerNotFoundException;
import com.rest.retailerapi.model.Customer;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository {
    @Value("${csv.file.path}")
    private String FILE_PATH;
    public List<Customer> findCustomers(int id) {
        List<Customer> customers = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (Integer.parseInt(line[1]) == id) {
                    Customer customer = new Customer(Integer.parseInt(line[0]), Integer.parseInt(line[1]),
                            Double.parseDouble(line[2]), new Date(line[3]));
                    customers.add(customer);
                }
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    public List<Customer> findLastThreeMonthsTransactions(int id) throws CustomerNotFoundException {
        List<Customer> allTransactions = findCustomers(id);
        if(allTransactions.size()==0){
            throw new CustomerNotFoundException("Customer not found with ID: " + id);
        }
        Date threeMonthsAgo = DateUtils.addMonths(new Date(), -3);
        return allTransactions.stream()
                .filter(customer -> customer.getCustid() == id && customer.getTransdate().after(threeMonthsAgo))
                .collect(Collectors.toList());
    }
}