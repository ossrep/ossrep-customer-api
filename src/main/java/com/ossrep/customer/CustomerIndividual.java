package com.ossrep.customer;

import jakarta.validation.constraints.NotEmpty;

public record CustomerIndividual(
        Long customerId,
        @NotEmpty(message = "{CustomerIndividual.firstName.required}")
        String firstName,
        String middleName,
        @NotEmpty(message = "{CustomerIndividual.lastName.required}")
        String lastName,
        Suffix suffix
) implements Customer {

    @Override
    public Long getCustomerId() {
        return customerId;
    }

    @Override
    public String getName() {
        StringBuilder name = new StringBuilder();
        name.append(firstName);
        if (middleName != null && !middleName.isBlank()) {
            name.append(" ");
            name.append(middleName);
        }
        name.append(lastName);
        if (suffix != null) {
            name.append(", ").append(suffix.getDisplayValue());
        }
        return name.toString();
    }

}
