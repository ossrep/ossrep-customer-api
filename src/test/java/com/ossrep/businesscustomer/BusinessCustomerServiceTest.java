package com.ossrep.businesscustomer;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class BusinessCustomerServiceTest {

    @Inject
    BusinessCustomerService businessCustomerService;

    @Test
    void findAll() {
        var all = this.businessCustomerService.findAll();
        Assertions.assertNotNull(all);
    }

    @Test
    void createAndFind() {
        var businessCustomer = new BusinessCustomer(null, RandomString.make());
        businessCustomer = this.businessCustomerService.create(businessCustomer);
        Assertions.assertNotNull(businessCustomer);
        Assertions.assertNotNull(businessCustomer.customerId());
        var found = this.businessCustomerService.findById(businessCustomer.customerId());
        Assertions.assertNotNull(found);
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals(businessCustomer.legalName(), found.get().legalName());
    }

    @Test
    void createAndUpdateAndFind() {
        var businessCustomer = new BusinessCustomer(null, RandomString.make());
        businessCustomer = this.businessCustomerService.create(businessCustomer);
        Assertions.assertNotNull(businessCustomer);
        Assertions.assertNotNull(businessCustomer.customerId());
        var updated = new BusinessCustomer(businessCustomer.customerId(), RandomString.make());
        this.businessCustomerService.update(updated);
        var found = this.businessCustomerService.findById(businessCustomer.customerId());
        Assertions.assertNotNull(found);
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals(updated.legalName(), found.get().legalName());
    }

    @Test
    void delete() {
        var businessCustomer = new BusinessCustomer(null, RandomString.make());
        businessCustomer = this.businessCustomerService.create(businessCustomer);
        Assertions.assertNotNull(businessCustomer);
        Assertions.assertNotNull(businessCustomer.customerId());
        this.businessCustomerService.delete(businessCustomer.customerId());
        var found = this.businessCustomerService.findById(businessCustomer.customerId());
        Assertions.assertNotNull(found);
        Assertions.assertFalse(found.isPresent());
    }

}
