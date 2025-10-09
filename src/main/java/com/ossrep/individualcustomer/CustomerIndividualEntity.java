package com.ossrep.individualcustomer;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

@Entity(name = "CustomerIndividual")
@Table(name = "customer_individual")
public class CustomerIndividualEntity extends PanacheEntityBase {

    @Id
    @Column(name = "customer_id")
    public Long customerId;

    @Column(name = "first_name")
    @NotEmpty(message = "{CustomerIndividual.firstName.required}")
    public String firstName;

    @Column(name = "middle_name")
    public String middleName;

    @Column(name = "last_name")
    @NotEmpty(message = "{CustomerIndividual.lastName.required}")
    public String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "suffix")
    public Suffix suffix;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CustomerIndividualEntity that = (CustomerIndividualEntity) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName) && Objects.equals(lastName, that.lastName) && suffix == that.suffix;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, middleName, lastName, suffix);
    }

    @Override
    public String toString() {
        return "CustomerIndividualEntity{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", suffix=" + suffix +
                '}';
    }

}
