package com.pharma.service.mapper;

import com.pharma.domain.*;
import com.pharma.service.dto.VendorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Vendor and its DTO VendorDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VendorMapper extends EntityMapper <VendorDTO, Vendor> {
    
    

}
