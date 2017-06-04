package com.pharma.service.mapper;

import com.pharma.domain.*;
import com.pharma.service.dto.ProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Product and its DTO ProductDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductMapper extends EntityMapper <ProductDTO, Product> {
    
    

}
