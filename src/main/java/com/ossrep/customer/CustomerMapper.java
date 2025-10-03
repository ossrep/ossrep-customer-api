package com.ossrep.customer;


import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface CustomerMapper {

    CustomerBusiness toDomain(CustomerBusinessEntity entity);

    CustomerBusinessEntity toEntity(CustomerBusiness domain);

    CustomerIndividual toDomain(CustomerIndividualEntity entity);

    CustomerIndividualEntity toEntity(CustomerIndividual domain);

}