package com.ossrep.customer;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerBusinessRepository implements PanacheRepositoryBase<CustomerBusinessEntity, Long> {
}
