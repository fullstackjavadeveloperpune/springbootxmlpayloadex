package com.fullstack.controller;

import com.fullstack.entity.Customer;
import com.fullstack.exception.RecordNotFoundException;
import com.fullstack.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Customer> signUp(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.signUp(customer));
    }

    @GetMapping("/signin/{custEmailId}/{custPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String custEmailId, @PathVariable String custPassword) {
        return ResponseEntity.ok(customerService.signIn(custEmailId, custPassword));
    }

    @GetMapping(value = "/findbyid/{custId}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Optional<Customer>> findById(@PathVariable long custId) {
        return ResponseEntity.ok(customerService.findById(custId));
    }

    @GetMapping(value = "/sortbybalance", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Customer>> sortByAccBalance() {
        return ResponseEntity.ok(customerService.findAll().stream().sorted(Comparator.comparing(Customer::getCustAccBalance)).toList());
    }

    @GetMapping(value = "/searchbyname", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Customer>> searchByName(@RequestParam String custName) {
        return ResponseEntity.ok(customerService.findAll().stream().filter(cust -> cust.getCustName().equals(custName)).toList());
    }

    @GetMapping(value = "/findall", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @PutMapping(value = "/update/{custId}", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Customer> update(@PathVariable long custId, @RequestBody Customer customer) {
        Customer customer1 = customerService.findById(custId).orElseThrow(() -> new RecordNotFoundException("Customer #ID Does Not Exist"));

        customer1.setCustName(customer.getCustName());
        customer1.setCustAddress(customer.getCustAddress());
        customer1.setCustContactNumber(customer.getCustContactNumber());
        customer1.setCustAccBalance(customer.getCustAccBalance());
        customer1.setCustDOB(customer.getCustDOB());
        customer1.setCustEmailId(customer.getCustEmailId());
        customer1.setCustPassword(customer.getCustPassword());

        return ResponseEntity.ok(customerService.update(customer1));
    }

    @DeleteMapping("/deletebyid/{custId}")
    public ResponseEntity<String> deleteById(@PathVariable long custId) {
        customerService.deleteById(custId);
        return ResponseEntity.ok("Data Successfully");
    }
}
