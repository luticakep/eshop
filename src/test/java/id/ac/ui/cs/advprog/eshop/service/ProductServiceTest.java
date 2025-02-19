package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        List<String> productIds = new ArrayList<>();
        productRepository.findAll().forEachRemaining(p -> productIds.add(p.getProductId()));

        // Delete all collected products
        for (String id : productIds) {
            productRepository.delete(id);
        }
    }

    @Test
    void createProduct() {
        Product product = new Product();
        product.setProductId("p1");
        product.setProductName("test");
        product.setProductQuantity(10);

        Product created = productService.create(product);

        assertNotNull(created);
        assertEquals("p1", created.getProductId());
        assertEquals("test", created.getProductName());
        assertEquals(10, created.getProductQuantity());
    }

    @Test
    void findByIdNonExisting() {
        Product found = productService.findById("non-existing");
        assertNull(found);
    }

    @Test
    void findAllProducts() {
        // Clear repository
        List<String> productIds = new ArrayList<>();
        productRepository.findAll().forEachRemaining(p -> productIds.add(p.getProductId()));
        productIds.forEach(productRepository::delete);

        Product product1 = new Product();
        product1.setProductId("p1");
        product1.setProductName("Product 1");
        product1.setProductQuantity(5);
        productService.create(product1);

        Product product2 = new Product();
        product2.setProductId("p2");
        product2.setProductName("Product 2");
        product2.setProductQuantity(15);
        productService.create(product2);

        List<Product> products = productService.findAll();
        assertNotNull(products);
        assertEquals(2, products.size(), "Should find exactly 2 products");
        assertTrue(products.stream().anyMatch(p -> "p1".equals(p.getProductId())));
        assertTrue(products.stream().anyMatch(p -> "p2".equals(p.getProductId())));
    }

    @Test
    void editProduct() {
        Product product = new Product();
        product.setProductId("p1");
        product.setProductName("first");
        product.setProductQuantity(5);
        productService.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("p1");
        updatedProduct.setProductName("second");
        updatedProduct.setProductQuantity(20);

        Product result = productService.edit("p1", updatedProduct);
        assertNotNull(result);
        assertEquals("second", result.getProductName());
        assertEquals(20, result.getProductQuantity());
    }

    @Test
    void deleteProduct() {
        Product product = new Product();
        product.setProductId("p1");
        product.setProductName("apus");
        product.setProductQuantity(12);
        productService.create(product);

        assertNotNull(productService.findById("p1"));
        productService.delete("p1");
        assertNull(productService.findById("p1"));
    }

    @Test
    void findByIdExisting() {
        List<String> productIds = new ArrayList<>();
        productRepository.findAll().forEachRemaining(p -> productIds.add(p.getProductId()));
        productIds.forEach(productRepository::delete);

        Product product = new Product();
        product.setProductId("p1");
        product.setProductName("luti");
        product.setProductQuantity(8);
        productService.create(product);

        Product found = productService.findById("p1");
        assertNotNull(found);
        assertEquals("p1", found.getProductId(), "Product ID should match");
        assertEquals("luti", found.getProductName(), "Product name should match");
        assertEquals(8, found.getProductQuantity(), "Product quantity should match");
    }
}
