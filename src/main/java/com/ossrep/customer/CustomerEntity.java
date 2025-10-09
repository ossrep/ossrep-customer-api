package com.ossrep.customer;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Generated;

import java.util.Objects;

@Entity(name = "Customer")
@Table(name = "customer")
public class CustomerEntity extends PanacheEntityBase {

    @Id
    @Generated()
    @Column(name = "customer_id")
    public Long customerId;

    @Column(name = "customer_type")
    @NotNull(message = "{Customer.customerType.required}")
    @Enumerated(EnumType.STRING)
    public CustomerType customerType;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(customerId, that.customerId) && customerType == that.customerType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerType);
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "customerId=" + customerId +
                ", customerType=" + customerType +
                '}';
    }

}
