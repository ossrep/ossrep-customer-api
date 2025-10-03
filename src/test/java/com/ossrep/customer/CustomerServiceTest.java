package com.ossrep.customer;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@QuarkusTest
public class CustomerServiceTest {

    @Inject
    CustomerService customerService;

    @Test
    void findByCustomerId() {
        var created = new CustomerIndividual(null, RandomStringUtils.insecure().nextAlphabetic(10), null, RandomStringUtils.insecure().nextAlphabetic(10), null);
        created = customerService.save(created);
        Optional<CustomerIndividual> foundOptional = customerService.findByCustomerId(created.customerId());
        Assertions.assertTrue(foundOptional.isPresent());
        Assertions.assertEquals(created.customerId(), foundOptional.get().customerId());
    }

}
