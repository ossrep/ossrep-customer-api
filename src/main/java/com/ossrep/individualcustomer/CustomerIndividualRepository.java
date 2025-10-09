package com.ossrep.individualcustomer;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerIndividualRepository implements PanacheRepositoryBase<CustomerIndividualEntity, Long> {
}
