package com.pharma.web.rest;

import com.pharma.Test51App;

import com.pharma.domain.Vendor;
import com.pharma.repository.VendorRepository;
import com.pharma.service.VendorService;
import com.pharma.service.dto.VendorDTO;
import com.pharma.service.mapper.VendorMapper;
import com.pharma.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VendorResource REST controller.
 *
 * @see VendorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Test51App.class)
public class VendorResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_PINCODE = "AAAAAAAAAA";
    private static final String UPDATED_PINCODE = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_BIO_DL_NO = "AAAAAAAAAA";
    private static final String UPDATED_BIO_DL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_NON_BIO_DL_NO = "AAAAAAAAAA";
    private static final String UPDATED_NON_BIO_DL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ESTB = "AAAAAAAAAA";
    private static final String UPDATED_ESTB = "BBBBBBBBBB";

    private static final String DEFAULT_CERTIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_CERTIFICATION = "BBBBBBBBBB";

    private static final String DEFAULT_WEB_SITE = "AAAAAAAAAA";
    private static final String UPDATED_WEB_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_ABOUT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_ABOUT_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_TIN = "AAAAAAAAAA";
    private static final String UPDATED_TIN = "BBBBBBBBBB";

    private static final String DEFAULT_ALTERNATE_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_ALTERNATE_MOBILE = "BBBBBBBBBB";

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private VendorMapper vendorMapper;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restVendorMockMvc;

    private Vendor vendor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        VendorResource vendorResource = new VendorResource(vendorService);
        this.restVendorMockMvc = MockMvcBuilders.standaloneSetup(vendorResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vendor createEntity() {
        Vendor vendor = new Vendor()
            .name(DEFAULT_NAME)
            .companyName(DEFAULT_COMPANY_NAME)
            .address(DEFAULT_ADDRESS)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .pincode(DEFAULT_PINCODE)
            .mobile(DEFAULT_MOBILE)
            .bioDlNo(DEFAULT_BIO_DL_NO)
            .nonBioDlNo(DEFAULT_NON_BIO_DL_NO)
            .estb(DEFAULT_ESTB)
            .certification(DEFAULT_CERTIFICATION)
            .webSite(DEFAULT_WEB_SITE)
            .aboutCompany(DEFAULT_ABOUT_COMPANY)
            .email(DEFAULT_EMAIL)
            .category(DEFAULT_CATEGORY)
            .tin(DEFAULT_TIN)
            .alternateMobile(DEFAULT_ALTERNATE_MOBILE);
        return vendor;
    }

    @Before
    public void initTest() {
        vendorRepository.deleteAll();
        vendor = createEntity();
    }

    @Test
    public void createVendor() throws Exception {
        int databaseSizeBeforeCreate = vendorRepository.findAll().size();

        // Create the Vendor
        VendorDTO vendorDTO = vendorMapper.toDto(vendor);
        restVendorMockMvc.perform(post("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isCreated());

        // Validate the Vendor in the database
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeCreate + 1);
        Vendor testVendor = vendorList.get(vendorList.size() - 1);
        assertThat(testVendor.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVendor.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testVendor.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testVendor.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testVendor.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testVendor.getPincode()).isEqualTo(DEFAULT_PINCODE);
        assertThat(testVendor.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testVendor.getBioDlNo()).isEqualTo(DEFAULT_BIO_DL_NO);
        assertThat(testVendor.getNonBioDlNo()).isEqualTo(DEFAULT_NON_BIO_DL_NO);
        assertThat(testVendor.getEstb()).isEqualTo(DEFAULT_ESTB);
        assertThat(testVendor.getCertification()).isEqualTo(DEFAULT_CERTIFICATION);
        assertThat(testVendor.getWebSite()).isEqualTo(DEFAULT_WEB_SITE);
        assertThat(testVendor.getAboutCompany()).isEqualTo(DEFAULT_ABOUT_COMPANY);
        assertThat(testVendor.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testVendor.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testVendor.getTin()).isEqualTo(DEFAULT_TIN);
        assertThat(testVendor.getAlternateMobile()).isEqualTo(DEFAULT_ALTERNATE_MOBILE);
    }

    @Test
    public void createVendorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vendorRepository.findAll().size();

        // Create the Vendor with an existing ID
        vendor.setId("existing_id");
        VendorDTO vendorDTO = vendorMapper.toDto(vendor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVendorMockMvc.perform(post("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendorRepository.findAll().size();
        // set the field null
        vendor.setName(null);

        // Create the Vendor, which fails.
        VendorDTO vendorDTO = vendorMapper.toDto(vendor);

        restVendorMockMvc.perform(post("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isBadRequest());

        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCompanyNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendorRepository.findAll().size();
        // set the field null
        vendor.setCompanyName(null);

        // Create the Vendor, which fails.
        VendorDTO vendorDTO = vendorMapper.toDto(vendor);

        restVendorMockMvc.perform(post("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isBadRequest());

        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendorRepository.findAll().size();
        // set the field null
        vendor.setCity(null);

        // Create the Vendor, which fails.
        VendorDTO vendorDTO = vendorMapper.toDto(vendor);

        restVendorMockMvc.perform(post("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isBadRequest());

        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendorRepository.findAll().size();
        // set the field null
        vendor.setState(null);

        // Create the Vendor, which fails.
        VendorDTO vendorDTO = vendorMapper.toDto(vendor);

        restVendorMockMvc.perform(post("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isBadRequest());

        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkPincodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendorRepository.findAll().size();
        // set the field null
        vendor.setPincode(null);

        // Create the Vendor, which fails.
        VendorDTO vendorDTO = vendorMapper.toDto(vendor);

        restVendorMockMvc.perform(post("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isBadRequest());

        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkMobileIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendorRepository.findAll().size();
        // set the field null
        vendor.setMobile(null);

        // Create the Vendor, which fails.
        VendorDTO vendorDTO = vendorMapper.toDto(vendor);

        restVendorMockMvc.perform(post("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isBadRequest());

        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendorRepository.findAll().size();
        // set the field null
        vendor.setEmail(null);

        // Create the Vendor, which fails.
        VendorDTO vendorDTO = vendorMapper.toDto(vendor);

        restVendorMockMvc.perform(post("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isBadRequest());

        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllVendors() throws Exception {
        // Initialize the database
        vendorRepository.save(vendor);

        // Get all the vendorList
        restVendorMockMvc.perform(get("/api/vendors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vendor.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].bioDlNo").value(hasItem(DEFAULT_BIO_DL_NO.toString())))
            .andExpect(jsonPath("$.[*].nonBioDlNo").value(hasItem(DEFAULT_NON_BIO_DL_NO.toString())))
            .andExpect(jsonPath("$.[*].estb").value(hasItem(DEFAULT_ESTB.toString())))
            .andExpect(jsonPath("$.[*].certification").value(hasItem(DEFAULT_CERTIFICATION.toString())))
            .andExpect(jsonPath("$.[*].webSite").value(hasItem(DEFAULT_WEB_SITE.toString())))
            .andExpect(jsonPath("$.[*].aboutCompany").value(hasItem(DEFAULT_ABOUT_COMPANY.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].tin").value(hasItem(DEFAULT_TIN.toString())))
            .andExpect(jsonPath("$.[*].alternateMobile").value(hasItem(DEFAULT_ALTERNATE_MOBILE.toString())));
    }

    @Test
    public void getVendor() throws Exception {
        // Initialize the database
        vendorRepository.save(vendor);

        // Get the vendor
        restVendorMockMvc.perform(get("/api/vendors/{id}", vendor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vendor.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.pincode").value(DEFAULT_PINCODE.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
            .andExpect(jsonPath("$.bioDlNo").value(DEFAULT_BIO_DL_NO.toString()))
            .andExpect(jsonPath("$.nonBioDlNo").value(DEFAULT_NON_BIO_DL_NO.toString()))
            .andExpect(jsonPath("$.estb").value(DEFAULT_ESTB.toString()))
            .andExpect(jsonPath("$.certification").value(DEFAULT_CERTIFICATION.toString()))
            .andExpect(jsonPath("$.webSite").value(DEFAULT_WEB_SITE.toString()))
            .andExpect(jsonPath("$.aboutCompany").value(DEFAULT_ABOUT_COMPANY.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.toString()))
            .andExpect(jsonPath("$.tin").value(DEFAULT_TIN.toString()))
            .andExpect(jsonPath("$.alternateMobile").value(DEFAULT_ALTERNATE_MOBILE.toString()));
    }

    @Test
    public void getNonExistingVendor() throws Exception {
        // Get the vendor
        restVendorMockMvc.perform(get("/api/vendors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateVendor() throws Exception {
        // Initialize the database
        vendorRepository.save(vendor);
        int databaseSizeBeforeUpdate = vendorRepository.findAll().size();

        // Update the vendor
        Vendor updatedVendor = vendorRepository.findOne(vendor.getId());
        updatedVendor
            .name(UPDATED_NAME)
            .companyName(UPDATED_COMPANY_NAME)
            .address(UPDATED_ADDRESS)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .pincode(UPDATED_PINCODE)
            .mobile(UPDATED_MOBILE)
            .bioDlNo(UPDATED_BIO_DL_NO)
            .nonBioDlNo(UPDATED_NON_BIO_DL_NO)
            .estb(UPDATED_ESTB)
            .certification(UPDATED_CERTIFICATION)
            .webSite(UPDATED_WEB_SITE)
            .aboutCompany(UPDATED_ABOUT_COMPANY)
            .email(UPDATED_EMAIL)
            .category(UPDATED_CATEGORY)
            .tin(UPDATED_TIN)
            .alternateMobile(UPDATED_ALTERNATE_MOBILE);
        VendorDTO vendorDTO = vendorMapper.toDto(updatedVendor);

        restVendorMockMvc.perform(put("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isOk());

        // Validate the Vendor in the database
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeUpdate);
        Vendor testVendor = vendorList.get(vendorList.size() - 1);
        assertThat(testVendor.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVendor.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testVendor.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testVendor.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testVendor.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testVendor.getPincode()).isEqualTo(UPDATED_PINCODE);
        assertThat(testVendor.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testVendor.getBioDlNo()).isEqualTo(UPDATED_BIO_DL_NO);
        assertThat(testVendor.getNonBioDlNo()).isEqualTo(UPDATED_NON_BIO_DL_NO);
        assertThat(testVendor.getEstb()).isEqualTo(UPDATED_ESTB);
        assertThat(testVendor.getCertification()).isEqualTo(UPDATED_CERTIFICATION);
        assertThat(testVendor.getWebSite()).isEqualTo(UPDATED_WEB_SITE);
        assertThat(testVendor.getAboutCompany()).isEqualTo(UPDATED_ABOUT_COMPANY);
        assertThat(testVendor.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testVendor.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testVendor.getTin()).isEqualTo(UPDATED_TIN);
        assertThat(testVendor.getAlternateMobile()).isEqualTo(UPDATED_ALTERNATE_MOBILE);
    }

    @Test
    public void updateNonExistingVendor() throws Exception {
        int databaseSizeBeforeUpdate = vendorRepository.findAll().size();

        // Create the Vendor
        VendorDTO vendorDTO = vendorMapper.toDto(vendor);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVendorMockMvc.perform(put("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isCreated());

        // Validate the Vendor in the database
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteVendor() throws Exception {
        // Initialize the database
        vendorRepository.save(vendor);
        int databaseSizeBeforeDelete = vendorRepository.findAll().size();

        // Get the vendor
        restVendorMockMvc.perform(delete("/api/vendors/{id}", vendor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vendor.class);
        Vendor vendor1 = new Vendor();
        vendor1.setId("id1");
        Vendor vendor2 = new Vendor();
        vendor2.setId(vendor1.getId());
        assertThat(vendor1).isEqualTo(vendor2);
        vendor2.setId("id2");
        assertThat(vendor1).isNotEqualTo(vendor2);
        vendor1.setId(null);
        assertThat(vendor1).isNotEqualTo(vendor2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VendorDTO.class);
        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setId("id1");
        VendorDTO vendorDTO2 = new VendorDTO();
        assertThat(vendorDTO1).isNotEqualTo(vendorDTO2);
        vendorDTO2.setId(vendorDTO1.getId());
        assertThat(vendorDTO1).isEqualTo(vendorDTO2);
        vendorDTO2.setId("id2");
        assertThat(vendorDTO1).isNotEqualTo(vendorDTO2);
        vendorDTO1.setId(null);
        assertThat(vendorDTO1).isNotEqualTo(vendorDTO2);
    }
}
