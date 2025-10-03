package com.ossrep.customer;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerIndividualRepository implements PanacheRepositoryBase<CustomerIndividualEntity, Long> {
}
