package com.ossrep.businesscustomer;

import com.ossrep.customer.CustomerEntity;
import com.ossrep.customer.CustomerRepository;
import com.ossrep.customer.CustomerType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BusinessCustomerService {

    private final CustomerBusinessRepository customerBusinessRepository;
    private final BusinessCustomerMapper businessCustomerMapper;
    private final CustomerRepository customerRepository;

    public BusinessCustomerService(CustomerBusinessRepository customerBusinessRepository, BusinessCustomerMapper businessCustomerMapper, CustomerRepository customerRepository) {
        this.customerBusinessRepository = customerBusinessRepository;
        this.businessCustomerMapper = businessCustomerMapper;
        this.customerRepository = customerRepository;
    }

    public List<BusinessCustomer> findAll() {
        return this.customerBusinessRepository.findAll()
                .stream()
                .map(businessCustomerMapper::toDomain)
                .toList();
    }

    public Optional<BusinessCustomer> findById(long customerId) {
        return this.customerBusinessRepository.findByIdOptional(customerId)
                .map(businessCustomerMapper::toDomain);
    }

    @Transactional
    public BusinessCustomer create(@Valid BusinessCustomer businessCustomer) {
        if (businessCustomer.customerId() != null) {
            throw new IllegalArgumentException("Customer ID must be null");
        }
        var customerEntity = new CustomerEntity();
        customerEntity.customerType = CustomerType.BUSINESS;
        this.customerRepository.persist(customerEntity);
        var entity = businessCustomerMapper.toEntity(businessCustomer);
        entity.customerId = customerEntity.customerId;
        this.customerBusinessRepository.persist(entity);
        return businessCustomerMapper.toDomain(entity);
    }

    @Transactional
    public void update(@Valid BusinessCustomer businessCustomer) {
        var optionalEntity = this.customerBusinessRepository.findByIdOptional(businessCustomer.customerId());
        if (optionalEntity.isEmpty()){
            throw new IllegalArgumentException("Customer not found");
        }
        var entity = optionalEntity.get();
        entity.legalName = businessCustomer.legalName();
        this.customerBusinessRepository.persist(entity);
    }

    @Transactional
    public void delete(long customerId) {
        this.customerBusinessRepository.deleteById(customerId);
        this.customerRepository.deleteById(customerId);
    }

}
