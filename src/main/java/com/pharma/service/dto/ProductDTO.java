package com.pharma.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Product entity.
 */
public class ProductDTO implements Serializable {

    private String id;

    @NotNull
    private String productName;

    private String saltName;

    private String description;

    private String packagingType;

    private Double unitPrice;

    private Double quntity;

    @NotNull
    private String category;

    private String composition;

    private String formulation;

    private String imageId;

    private Integer ratings;

    private ZonedDateTime eta;

    private String timeRequired;

    private Boolean isPackagingProvided;

    private Double minOrderQuantity;

    private String unit;

    private String vendorId;

    private String subCategory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSaltName() {
        return saltName;
    }

    public void setSaltName(String saltName) {
        this.saltName = saltName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackagingType() {
        return packagingType;
    }

    public void setPackagingType(String packagingType) {
        this.packagingType = packagingType;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getQuntity() {
        return quntity;
    }

    public void setQuntity(Double quntity) {
        this.quntity = quntity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getFormulation() {
        return formulation;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Integer getRatings() {
        return ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }

    public ZonedDateTime getEta() {
        return eta;
    }

    public void setEta(ZonedDateTime eta) {
        this.eta = eta;
    }

    public String getTimeRequired() {
        return timeRequired;
    }

    public void setTimeRequired(String timeRequired) {
        this.timeRequired = timeRequired;
    }

    public Boolean isIsPackagingProvided() {
        return isPackagingProvided;
    }

    public void setIsPackagingProvided(Boolean isPackagingProvided) {
        this.isPackagingProvided = isPackagingProvided;
    }

    public Double getMinOrderQuantity() {
        return minOrderQuantity;
    }

    public void setMinOrderQuantity(Double minOrderQuantity) {
        this.minOrderQuantity = minOrderQuantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductDTO productDTO = (ProductDTO) o;
        if(productDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
            "id=" + getId() +
            ", productName='" + getProductName() + "'" +
            ", saltName='" + getSaltName() + "'" +
            ", description='" + getDescription() + "'" +
            ", packagingType='" + getPackagingType() + "'" +
            ", unitPrice='" + getUnitPrice() + "'" +
            ", quntity='" + getQuntity() + "'" +
            ", category='" + getCategory() + "'" +
            ", composition='" + getComposition() + "'" +
            ", formulation='" + getFormulation() + "'" +
            ", imageId='" + getImageId() + "'" +
            ", ratings='" + getRatings() + "'" +
            ", eta='" + getEta() + "'" +
            ", timeRequired='" + getTimeRequired() + "'" +
            ", isPackagingProvided='" + isIsPackagingProvided() + "'" +
            ", minOrderQuantity='" + getMinOrderQuantity() + "'" +
            ", unit='" + getUnit() + "'" +
            ", vendorId='" + getVendorId() + "'" +
            ", subCategory='" + getSubCategory() + "'" +
            "}";
    }
}
