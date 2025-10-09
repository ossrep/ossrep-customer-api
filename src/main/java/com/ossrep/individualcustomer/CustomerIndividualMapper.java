package com.ossrep.individualcustomer;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface CustomerIndividualMapper {

    IndividualCustomer toDomain(CustomerIndividualEntity entity);

    CustomerIndividualEntity toEntity(IndividualCustomer domain);

}
