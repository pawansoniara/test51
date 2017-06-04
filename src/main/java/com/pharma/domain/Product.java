package com.pharma.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Product.
 */

@Document(collection = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("product_name")
    private String productName;

    @Field("salt_name")
    private String saltName;

    @Field("description")
    private String description;

    @Field("packaging_type")
    private String packagingType;

    @Field("unit_price")
    private Double unitPrice;

    @Field("quntity")
    private Double quntity;

    @NotNull
    @Field("category")
    private String category;

    @Field("composition")
    private String composition;

    @Field("formulation")
    private String formulation;

    @Field("image_id")
    private String imageId;

    @Field("ratings")
    private Integer ratings;

    @Field("eta")
    private ZonedDateTime eta;

    @Field("time_required")
    private String timeRequired;

    @Field("is_packaging_provided")
    private Boolean isPackagingProvided;

    @Field("min_order_quantity")
    private Double minOrderQuantity;

    @Field("unit")
    private String unit;

    @Field("vendor_id")
    private String vendorId;

    @Field("sub_category")
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

    public Product productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSaltName() {
        return saltName;
    }

    public Product saltName(String saltName) {
        this.saltName = saltName;
        return this;
    }

    public void setSaltName(String saltName) {
        this.saltName = saltName;
    }

    public String getDescription() {
        return description;
    }

    public Product description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackagingType() {
        return packagingType;
    }

    public Product packagingType(String packagingType) {
        this.packagingType = packagingType;
        return this;
    }

    public void setPackagingType(String packagingType) {
        this.packagingType = packagingType;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Product unitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getQuntity() {
        return quntity;
    }

    public Product quntity(Double quntity) {
        this.quntity = quntity;
        return this;
    }

    public void setQuntity(Double quntity) {
        this.quntity = quntity;
    }

    public String getCategory() {
        return category;
    }

    public Product category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComposition() {
        return composition;
    }

    public Product composition(String composition) {
        this.composition = composition;
        return this;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getFormulation() {
        return formulation;
    }

    public Product formulation(String formulation) {
        this.formulation = formulation;
        return this;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }

    public String getImageId() {
        return imageId;
    }

    public Product imageId(String imageId) {
        this.imageId = imageId;
        return this;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Integer getRatings() {
        return ratings;
    }

    public Product ratings(Integer ratings) {
        this.ratings = ratings;
        return this;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }

    public ZonedDateTime getEta() {
        return eta;
    }

    public Product eta(ZonedDateTime eta) {
        this.eta = eta;
        return this;
    }

    public void setEta(ZonedDateTime eta) {
        this.eta = eta;
    }

    public String getTimeRequired() {
        return timeRequired;
    }

    public Product timeRequired(String timeRequired) {
        this.timeRequired = timeRequired;
        return this;
    }

    public void setTimeRequired(String timeRequired) {
        this.timeRequired = timeRequired;
    }

    public Boolean isIsPackagingProvided() {
        return isPackagingProvided;
    }

    public Product isPackagingProvided(Boolean isPackagingProvided) {
        this.isPackagingProvided = isPackagingProvided;
        return this;
    }

    public void setIsPackagingProvided(Boolean isPackagingProvided) {
        this.isPackagingProvided = isPackagingProvided;
    }

    public Double getMinOrderQuantity() {
        return minOrderQuantity;
    }

    public Product minOrderQuantity(Double minOrderQuantity) {
        this.minOrderQuantity = minOrderQuantity;
        return this;
    }

    public void setMinOrderQuantity(Double minOrderQuantity) {
        this.minOrderQuantity = minOrderQuantity;
    }

    public String getUnit() {
        return unit;
    }

    public Product unit(String unit) {
        this.unit = unit;
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getVendorId() {
        return vendorId;
    }

    public Product vendorId(String vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public Product subCategory(String subCategory) {
        this.subCategory = subCategory;
        return this;
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
        Product product = (Product) o;
        if (product.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Product{" +
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
