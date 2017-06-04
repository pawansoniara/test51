package com.pharma.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Vendor entity.
 */
public class VendorDTO implements Serializable {

    private String id;

    @NotNull
    private String name;

    @NotNull
    private String companyName;

    private String address;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String pincode;

    @NotNull
    private String mobile;

    private String bioDlNo;

    private String nonBioDlNo;

    private String estb;

    private String certification;

    private String webSite;

    private String aboutCompany;

    @NotNull
    private String email;

    private String category;

    private String tin;

    private String alternateMobile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBioDlNo() {
        return bioDlNo;
    }

    public void setBioDlNo(String bioDlNo) {
        this.bioDlNo = bioDlNo;
    }

    public String getNonBioDlNo() {
        return nonBioDlNo;
    }

    public void setNonBioDlNo(String nonBioDlNo) {
        this.nonBioDlNo = nonBioDlNo;
    }

    public String getEstb() {
        return estb;
    }

    public void setEstb(String estb) {
        this.estb = estb;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getAboutCompany() {
        return aboutCompany;
    }

    public void setAboutCompany(String aboutCompany) {
        this.aboutCompany = aboutCompany;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getAlternateMobile() {
        return alternateMobile;
    }

    public void setAlternateMobile(String alternateMobile) {
        this.alternateMobile = alternateMobile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VendorDTO vendorDTO = (VendorDTO) o;
        if(vendorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendorDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", address='" + getAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", pincode='" + getPincode() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", bioDlNo='" + getBioDlNo() + "'" +
            ", nonBioDlNo='" + getNonBioDlNo() + "'" +
            ", estb='" + getEstb() + "'" +
            ", certification='" + getCertification() + "'" +
            ", webSite='" + getWebSite() + "'" +
            ", aboutCompany='" + getAboutCompany() + "'" +
            ", email='" + getEmail() + "'" +
            ", category='" + getCategory() + "'" +
            ", tin='" + getTin() + "'" +
            ", alternateMobile='" + getAlternateMobile() + "'" +
            "}";
    }
}
