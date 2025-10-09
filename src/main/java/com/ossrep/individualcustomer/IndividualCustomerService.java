package com.ossrep.individualcustomer;

import com.ossrep.customer.CustomerEntity;
import com.ossrep.customer.CustomerRepository;
import com.ossrep.customer.CustomerType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class IndividualCustomerService {

    private final CustomerIndividualRepository customerIndividualRepository;
    private final CustomerIndividualMapper customerIndividualMapper;
    private final CustomerRepository customerRepository;

    public IndividualCustomerService(CustomerIndividualRepository customerIndividualRepository, CustomerIndividualMapper customerIndividualMapper, CustomerRepository customerRepository) {
        this.customerIndividualRepository = customerIndividualRepository;
        this.customerIndividualMapper = customerIndividualMapper;
        this.customerRepository = customerRepository;
    }

    public List<IndividualCustomer> findAll() {
        return this.customerIndividualRepository.findAll()
                .stream()
                .map(customerIndividualMapper::toDomain)
                .toList();
    }

    public Optional<IndividualCustomer> findById(long customerId) {
        return this.customerIndividualRepository.findByIdOptional(customerId)
                .map(customerIndividualMapper::toDomain);
    }

    @Transactional
    public IndividualCustomer create(@Valid IndividualCustomer individualCustomer) {
        if (individualCustomer.customerId() != null) {
            throw new IllegalArgumentException("Customer ID must be null");
        }
        var customerEntity = new CustomerEntity();
        customerEntity.customerType = CustomerType.INDIVIDUAL;
        this.customerRepository.persist(customerEntity);
        var entity = customerIndividualMapper.toEntity(individualCustomer);
        entity.customerId = customerEntity.customerId;
        this.customerIndividualRepository.persist(entity);
        return customerIndividualMapper.toDomain(entity);
    }

    @Transactional
    public void update(@Valid IndividualCustomer individualCustomer) {
        var optionalEntity = this.customerIndividualRepository.findByIdOptional(individualCustomer.customerId());
        if (optionalEntity.isEmpty()){
            throw new IllegalArgumentException("Customer not found");
        }
        var entity = optionalEntity.get();
        entity.firstName = individualCustomer.firstName();
        entity.middleName = individualCustomer.middleName();
        entity.lastName = individualCustomer.lastName();
        entity.suffix = individualCustomer.suffix();
        this.customerIndividualRepository.persist(entity);
    }

    @Transactional
    public void delete(long customerId) {
        this.customerIndividualRepository.deleteById(customerId);
        this.customerRepository.deleteById(customerId);
    }

}
