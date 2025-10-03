package com.ossrep.customer;

import jakarta.validation.constraints.NotEmpty;

public record CustomerBusiness(
        Long customerId,
        @NotEmpty(message = "{CustomerBusiness.legalName.required}")
        String legalName
) implements Customer {
    @Override
    public Long getCustomerId() {
        return customerId;
    }

    @Override
    public String getName() {
        return legalName;
    }
}
