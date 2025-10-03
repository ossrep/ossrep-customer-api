package com.ossrep.customer;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

@Entity(name = "CustomerBusiness")
@Table(name = "customer_business")
public class CustomerBusinessEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    public Long customerId;

    @Column(name = "legal_name")
    @NotEmpty(message = "{CustomerBusiness.legalName.required}")
    public String legalName;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CustomerBusinessEntity that = (CustomerBusinessEntity) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(legalName, that.legalName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, legalName);
    }

    @Override
    public String toString() {
        return "CustomerBusinessEntity{" +
                "customerId=" + customerId +
                ", legalName='" + legalName + '\'' +
                '}';
    }

}