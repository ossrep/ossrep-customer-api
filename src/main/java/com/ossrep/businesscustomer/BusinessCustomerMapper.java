package com.ossrep.businesscustomer;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface BusinessCustomerMapper {

    BusinessCustomer toDomain(CustomerBusinessEntity entity);

    CustomerBusinessEntity toEntity(BusinessCustomer domain);

}
