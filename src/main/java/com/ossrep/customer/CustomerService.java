package com.ossrep.customer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jdk.jfr.TransitionFrom;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class CustomerService {

    private final CustomerBusinessRepository customerBusinessRepository;
    private final CustomerIndividualRepository customerIndividualRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerBusinessRepository customerBusinessRepository, CustomerIndividualRepository customerIndividualRepository, CustomerMapper customerMapper) {
        this.customerBusinessRepository = customerBusinessRepository;
        this.customerIndividualRepository = customerIndividualRepository;
        this.customerMapper = customerMapper;
    }

    public List<Customer> findAll() {
        return this.customerIndividualRepository.findAll().stream()
                .map(customerMapper::toDomain)
                .collect(Collectors.toUnmodifiableList());
    }

    public Optional<CustomerIndividual> findByCustomerId(long customerId) {
        return this.customerIndividualRepository.findByIdOptional(customerId).map(customerMapper::toDomain);
    }

    @Transactional
    public CustomerIndividual save(CustomerIndividual customerIndividual) {
        var entity = this.customerMapper.toEntity(customerIndividual);
        this.customerIndividualRepository.persist(entity);
        return this.customerMapper.toDomain(entity);
    }

}
