package com.pharma.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Vendor.
 */

@Document(collection = "vendor")
public class Vendor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @NotNull
    @Field("company_name")
    private String companyName;

    @Field("address")
    private String address;

    @NotNull
    @Field("city")
    private String city;

    @NotNull
    @Field("state")
    private String state;

    @NotNull
    @Field("pincode")
    private String pincode;

    @NotNull
    @Field("mobile")
    private String mobile;

    @Field("bio_dl_no")
    private String bioDlNo;

    @Field("non_bio_dl_no")
    private String nonBioDlNo;

    @Field("estb")
    private String estb;

    @Field("certification")
    private String certification;

    @Field("web_site")
    private String webSite;

    @Field("about_company")
    private String aboutCompany;

    @NotNull
    @Field("email")
    private String email;

    @Field("category")
    private String category;

    @Field("tin")
    private String tin;

    @Field("alternate_mobile")
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

    public Vendor name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Vendor companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public Vendor address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public Vendor city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public Vendor state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public Vendor pincode(String pincode) {
        this.pincode = pincode;
        return this;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getMobile() {
        return mobile;
    }

    public Vendor mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBioDlNo() {
        return bioDlNo;
    }

    public Vendor bioDlNo(String bioDlNo) {
        this.bioDlNo = bioDlNo;
        return this;
    }

    public void setBioDlNo(String bioDlNo) {
        this.bioDlNo = bioDlNo;
    }

    public String getNonBioDlNo() {
        return nonBioDlNo;
    }

    public Vendor nonBioDlNo(String nonBioDlNo) {
        this.nonBioDlNo = nonBioDlNo;
        return this;
    }

    public void setNonBioDlNo(String nonBioDlNo) {
        this.nonBioDlNo = nonBioDlNo;
    }

    public String getEstb() {
        return estb;
    }

    public Vendor estb(String estb) {
        this.estb = estb;
        return this;
    }

    public void setEstb(String estb) {
        this.estb = estb;
    }

    public String getCertification() {
        return certification;
    }

    public Vendor certification(String certification) {
        this.certification = certification;
        return this;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getWebSite() {
        return webSite;
    }

    public Vendor webSite(String webSite) {
        this.webSite = webSite;
        return this;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getAboutCompany() {
        return aboutCompany;
    }

    public Vendor aboutCompany(String aboutCompany) {
        this.aboutCompany = aboutCompany;
        return this;
    }

    public void setAboutCompany(String aboutCompany) {
        this.aboutCompany = aboutCompany;
    }

    public String getEmail() {
        return email;
    }

    public Vendor email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public Vendor category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTin() {
        return tin;
    }

    public Vendor tin(String tin) {
        this.tin = tin;
        return this;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getAlternateMobile() {
        return alternateMobile;
    }

    public Vendor alternateMobile(String alternateMobile) {
        this.alternateMobile = alternateMobile;
        return this;
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
        Vendor vendor = (Vendor) o;
        if (vendor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Vendor{" +
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
