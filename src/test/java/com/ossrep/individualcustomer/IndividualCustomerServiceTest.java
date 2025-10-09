package com.ossrep.individualcustomer;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class IndividualCustomerServiceTest {

    @Inject
    IndividualCustomerService individualCustomerService;

    @Test
    void findAll() {
        var all = this.individualCustomerService.findAll();
        Assertions.assertNotNull(all);
    }

    @Test
    void createAndFind() {
        var individualCustomer = new IndividualCustomer(null, RandomString.make(), null, RandomString.make(), null);
        individualCustomer = this.individualCustomerService.create(individualCustomer);
        Assertions.assertNotNull(individualCustomer);
        Assertions.assertNotNull(individualCustomer.customerId());
        var found = this.individualCustomerService.findById(individualCustomer.customerId());
        Assertions.assertNotNull(found);
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals(individualCustomer.firstName(), found.get().firstName());
    }

    @Test
    void createAndUpdateAndFind() {
        var individualCustomer = new IndividualCustomer(null, RandomString.make(), null, RandomString.make(), null);
        individualCustomer = this.individualCustomerService.create(individualCustomer);
        Assertions.assertNotNull(individualCustomer);
        Assertions.assertNotNull(individualCustomer.customerId());
        var updated = new IndividualCustomer(individualCustomer.customerId(), RandomString.make(), null, individualCustomer.lastName(), null);
        this.individualCustomerService.update(updated);
        var found = this.individualCustomerService.findById(individualCustomer.customerId());
        Assertions.assertNotNull(found);
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals(updated.firstName(), found.get().firstName());
    }

    @Test
    void delete() {
        var individualCustomer = new IndividualCustomer(null, RandomString.make(), null, RandomString.make(), null);
        individualCustomer = this.individualCustomerService.create(individualCustomer);
        Assertions.assertNotNull(individualCustomer);
        Assertions.assertNotNull(individualCustomer.customerId());
        this.individualCustomerService.delete(individualCustomer.customerId());
        var found = this.individualCustomerService.findById(individualCustomer.customerId());
        Assertions.assertNotNull(found);
        Assertions.assertFalse(found.isPresent());
    }

}
