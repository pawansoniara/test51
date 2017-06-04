package com.pharma.web.rest;

import com.pharma.Test51App;

import com.pharma.domain.Product;
import com.pharma.repository.ProductRepository;
import com.pharma.service.ProductService;
import com.pharma.service.dto.ProductDTO;
import com.pharma.service.mapper.ProductMapper;
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

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.pharma.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProductResource REST controller.
 *
 * @see ProductResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Test51App.class)
public class ProductResourceIntTest {

    private static final String DEFAULT_PRODUCT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SALT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SALT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PACKAGING_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PACKAGING_TYPE = "BBBBBBBBBB";

    private static final Double DEFAULT_UNIT_PRICE = 1D;
    private static final Double UPDATED_UNIT_PRICE = 2D;

    private static final Double DEFAULT_QUNTITY = 1D;
    private static final Double UPDATED_QUNTITY = 2D;

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_COMPOSITION = "AAAAAAAAAA";
    private static final String UPDATED_COMPOSITION = "BBBBBBBBBB";

    private static final String DEFAULT_FORMULATION = "AAAAAAAAAA";
    private static final String UPDATED_FORMULATION = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_ID = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_RATINGS = 1;
    private static final Integer UPDATED_RATINGS = 2;

    private static final ZonedDateTime DEFAULT_ETA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ETA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TIME_REQUIRED = "AAAAAAAAAA";
    private static final String UPDATED_TIME_REQUIRED = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_PACKAGING_PROVIDED = false;
    private static final Boolean UPDATED_IS_PACKAGING_PROVIDED = true;

    private static final Double DEFAULT_MIN_ORDER_QUANTITY = 1D;
    private static final Double UPDATED_MIN_ORDER_QUANTITY = 2D;

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR_ID = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_SUB_CATEGORY = "BBBBBBBBBB";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProductMockMvc;

    private Product product;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProductResource productResource = new ProductResource(productService);
        this.restProductMockMvc = MockMvcBuilders.standaloneSetup(productResource)
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
    public static Product createEntity() {
        Product product = new Product()
            .productName(DEFAULT_PRODUCT_NAME)
            .saltName(DEFAULT_SALT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .packagingType(DEFAULT_PACKAGING_TYPE)
            .unitPrice(DEFAULT_UNIT_PRICE)
            .quntity(DEFAULT_QUNTITY)
            .category(DEFAULT_CATEGORY)
            .composition(DEFAULT_COMPOSITION)
            .formulation(DEFAULT_FORMULATION)
            .imageId(DEFAULT_IMAGE_ID)
            .ratings(DEFAULT_RATINGS)
            .eta(DEFAULT_ETA)
            .timeRequired(DEFAULT_TIME_REQUIRED)
            .isPackagingProvided(DEFAULT_IS_PACKAGING_PROVIDED)
            .minOrderQuantity(DEFAULT_MIN_ORDER_QUANTITY)
            .unit(DEFAULT_UNIT)
            .vendorId(DEFAULT_VENDOR_ID)
            .subCategory(DEFAULT_SUB_CATEGORY);
        return product;
    }

    @Before
    public void initTest() {
        productRepository.deleteAll();
        product = createEntity();
    }

    @Test
    public void createProduct() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product
        ProductDTO productDTO = productMapper.toDto(product);
        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productDTO)))
            .andExpect(status().isCreated());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate + 1);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testProduct.getSaltName()).isEqualTo(DEFAULT_SALT_NAME);
        assertThat(testProduct.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testProduct.getPackagingType()).isEqualTo(DEFAULT_PACKAGING_TYPE);
        assertThat(testProduct.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
        assertThat(testProduct.getQuntity()).isEqualTo(DEFAULT_QUNTITY);
        assertThat(testProduct.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testProduct.getComposition()).isEqualTo(DEFAULT_COMPOSITION);
        assertThat(testProduct.getFormulation()).isEqualTo(DEFAULT_FORMULATION);
        assertThat(testProduct.getImageId()).isEqualTo(DEFAULT_IMAGE_ID);
        assertThat(testProduct.getRatings()).isEqualTo(DEFAULT_RATINGS);
        assertThat(testProduct.getEta()).isEqualTo(DEFAULT_ETA);
        assertThat(testProduct.getTimeRequired()).isEqualTo(DEFAULT_TIME_REQUIRED);
        assertThat(testProduct.isIsPackagingProvided()).isEqualTo(DEFAULT_IS_PACKAGING_PROVIDED);
        assertThat(testProduct.getMinOrderQuantity()).isEqualTo(DEFAULT_MIN_ORDER_QUANTITY);
        assertThat(testProduct.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testProduct.getVendorId()).isEqualTo(DEFAULT_VENDOR_ID);
        assertThat(testProduct.getSubCategory()).isEqualTo(DEFAULT_SUB_CATEGORY);
    }

    @Test
    public void createProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product with an existing ID
        product.setId("existing_id");
        ProductDTO productDTO = productMapper.toDto(product);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkProductNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setProductName(null);

        // Create the Product, which fails.
        ProductDTO productDTO = productMapper.toDto(product);

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productDTO)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setCategory(null);

        // Create the Product, which fails.
        ProductDTO productDTO = productMapper.toDto(product);

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productDTO)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProducts() throws Exception {
        // Initialize the database
        productRepository.save(product);

        // Get all the productList
        restProductMockMvc.perform(get("/api/products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(product.getId())))
            .andExpect(jsonPath("$.[*].productName").value(hasItem(DEFAULT_PRODUCT_NAME.toString())))
            .andExpect(jsonPath("$.[*].saltName").value(hasItem(DEFAULT_SALT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].packagingType").value(hasItem(DEFAULT_PACKAGING_TYPE.toString())))
            .andExpect(jsonPath("$.[*].unitPrice").value(hasItem(DEFAULT_UNIT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].quntity").value(hasItem(DEFAULT_QUNTITY.doubleValue())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].composition").value(hasItem(DEFAULT_COMPOSITION.toString())))
            .andExpect(jsonPath("$.[*].formulation").value(hasItem(DEFAULT_FORMULATION.toString())))
            .andExpect(jsonPath("$.[*].imageId").value(hasItem(DEFAULT_IMAGE_ID.toString())))
            .andExpect(jsonPath("$.[*].ratings").value(hasItem(DEFAULT_RATINGS)))
            .andExpect(jsonPath("$.[*].eta").value(hasItem(sameInstant(DEFAULT_ETA))))
            .andExpect(jsonPath("$.[*].timeRequired").value(hasItem(DEFAULT_TIME_REQUIRED.toString())))
            .andExpect(jsonPath("$.[*].isPackagingProvided").value(hasItem(DEFAULT_IS_PACKAGING_PROVIDED.booleanValue())))
            .andExpect(jsonPath("$.[*].minOrderQuantity").value(hasItem(DEFAULT_MIN_ORDER_QUANTITY.doubleValue())))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT.toString())))
            .andExpect(jsonPath("$.[*].vendorId").value(hasItem(DEFAULT_VENDOR_ID.toString())))
            .andExpect(jsonPath("$.[*].subCategory").value(hasItem(DEFAULT_SUB_CATEGORY.toString())));
    }

    @Test
    public void getProduct() throws Exception {
        // Initialize the database
        productRepository.save(product);

        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", product.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(product.getId()))
            .andExpect(jsonPath("$.productName").value(DEFAULT_PRODUCT_NAME.toString()))
            .andExpect(jsonPath("$.saltName").value(DEFAULT_SALT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.packagingType").value(DEFAULT_PACKAGING_TYPE.toString()))
            .andExpect(jsonPath("$.unitPrice").value(DEFAULT_UNIT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.quntity").value(DEFAULT_QUNTITY.doubleValue()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.toString()))
            .andExpect(jsonPath("$.composition").value(DEFAULT_COMPOSITION.toString()))
            .andExpect(jsonPath("$.formulation").value(DEFAULT_FORMULATION.toString()))
            .andExpect(jsonPath("$.imageId").value(DEFAULT_IMAGE_ID.toString()))
            .andExpect(jsonPath("$.ratings").value(DEFAULT_RATINGS))
            .andExpect(jsonPath("$.eta").value(sameInstant(DEFAULT_ETA)))
            .andExpect(jsonPath("$.timeRequired").value(DEFAULT_TIME_REQUIRED.toString()))
            .andExpect(jsonPath("$.isPackagingProvided").value(DEFAULT_IS_PACKAGING_PROVIDED.booleanValue()))
            .andExpect(jsonPath("$.minOrderQuantity").value(DEFAULT_MIN_ORDER_QUANTITY.doubleValue()))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT.toString()))
            .andExpect(jsonPath("$.vendorId").value(DEFAULT_VENDOR_ID.toString()))
            .andExpect(jsonPath("$.subCategory").value(DEFAULT_SUB_CATEGORY.toString()));
    }

    @Test
    public void getNonExistingProduct() throws Exception {
        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProduct() throws Exception {
        // Initialize the database
        productRepository.save(product);
        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product
        Product updatedProduct = productRepository.findOne(product.getId());
        updatedProduct
            .productName(UPDATED_PRODUCT_NAME)
            .saltName(UPDATED_SALT_NAME)
            .description(UPDATED_DESCRIPTION)
            .packagingType(UPDATED_PACKAGING_TYPE)
            .unitPrice(UPDATED_UNIT_PRICE)
            .quntity(UPDATED_QUNTITY)
            .category(UPDATED_CATEGORY)
            .composition(UPDATED_COMPOSITION)
            .formulation(UPDATED_FORMULATION)
            .imageId(UPDATED_IMAGE_ID)
            .ratings(UPDATED_RATINGS)
            .eta(UPDATED_ETA)
            .timeRequired(UPDATED_TIME_REQUIRED)
            .isPackagingProvided(UPDATED_IS_PACKAGING_PROVIDED)
            .minOrderQuantity(UPDATED_MIN_ORDER_QUANTITY)
            .unit(UPDATED_UNIT)
            .vendorId(UPDATED_VENDOR_ID)
            .subCategory(UPDATED_SUB_CATEGORY);
        ProductDTO productDTO = productMapper.toDto(updatedProduct);

        restProductMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productDTO)))
            .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testProduct.getSaltName()).isEqualTo(UPDATED_SALT_NAME);
        assertThat(testProduct.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProduct.getPackagingType()).isEqualTo(UPDATED_PACKAGING_TYPE);
        assertThat(testProduct.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testProduct.getQuntity()).isEqualTo(UPDATED_QUNTITY);
        assertThat(testProduct.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testProduct.getComposition()).isEqualTo(UPDATED_COMPOSITION);
        assertThat(testProduct.getFormulation()).isEqualTo(UPDATED_FORMULATION);
        assertThat(testProduct.getImageId()).isEqualTo(UPDATED_IMAGE_ID);
        assertThat(testProduct.getRatings()).isEqualTo(UPDATED_RATINGS);
        assertThat(testProduct.getEta()).isEqualTo(UPDATED_ETA);
        assertThat(testProduct.getTimeRequired()).isEqualTo(UPDATED_TIME_REQUIRED);
        assertThat(testProduct.isIsPackagingProvided()).isEqualTo(UPDATED_IS_PACKAGING_PROVIDED);
        assertThat(testProduct.getMinOrderQuantity()).isEqualTo(UPDATED_MIN_ORDER_QUANTITY);
        assertThat(testProduct.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testProduct.getVendorId()).isEqualTo(UPDATED_VENDOR_ID);
        assertThat(testProduct.getSubCategory()).isEqualTo(UPDATED_SUB_CATEGORY);
    }

    @Test
    public void updateNonExistingProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Create the Product
        ProductDTO productDTO = productMapper.toDto(product);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProductMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productDTO)))
            .andExpect(status().isCreated());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProduct() throws Exception {
        // Initialize the database
        productRepository.save(product);
        int databaseSizeBeforeDelete = productRepository.findAll().size();

        // Get the product
        restProductMockMvc.perform(delete("/api/products/{id}", product.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Product.class);
        Product product1 = new Product();
        product1.setId("id1");
        Product product2 = new Product();
        product2.setId(product1.getId());
        assertThat(product1).isEqualTo(product2);
        product2.setId("id2");
        assertThat(product1).isNotEqualTo(product2);
        product1.setId(null);
        assertThat(product1).isNotEqualTo(product2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductDTO.class);
        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setId("id1");
        ProductDTO productDTO2 = new ProductDTO();
        assertThat(productDTO1).isNotEqualTo(productDTO2);
        productDTO2.setId(productDTO1.getId());
        assertThat(productDTO1).isEqualTo(productDTO2);
        productDTO2.setId("id2");
        assertThat(productDTO1).isNotEqualTo(productDTO2);
        productDTO1.setId(null);
        assertThat(productDTO1).isNotEqualTo(productDTO2);
    }
}
