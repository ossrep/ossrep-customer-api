package com.ossrep.businesscustomer;

import jakarta.validation.constraints.NotEmpty;

public record BusinessCustomer(
        Long customerId,
        @NotEmpty(message = "{CustomerBusiness.legalName.required}") String legalName
) {}
