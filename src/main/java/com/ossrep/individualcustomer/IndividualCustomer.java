package com.ossrep.individualcustomer;

import jakarta.validation.constraints.NotEmpty;

public record IndividualCustomer(
        Long customerId,
        @NotEmpty(message = "{CustomerIndividual.firstName.required}") String firstName,
        String middleName,
        @NotEmpty(message = "{CustomerIndividual.lastName.required}") String lastName,
        Suffix suffix
) {
}
