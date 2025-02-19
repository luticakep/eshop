package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }
    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-0000-460e-8860-71af6af63777");
        product.setProductName("Old Name");
        product.setProductQuantity(50);

        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Imud Product");
        updatedProduct.setProductQuantity(75);

        productRepository.edit("eb558e9f-0000-460e-8860-71af6af63777", updatedProduct);
        Product modifiedProduct = productRepository.findById("eb558e9f-0000-460e-8860-71af6af63777");

        assertEquals("Imud Product", modifiedProduct.getProductName());
        assertEquals(75, modifiedProduct.getProductQuantity());
    }
    @Test
    void testEditProductNotFound() {
        Product nonExistingProduct = new Product();
        nonExistingProduct.setProductId("aaa58e9f-1c39-460e-8860-71af6af63kms");
        nonExistingProduct.setProductName("None");
        nonExistingProduct.setProductQuantity(10);

        Product result = productRepository.edit("aaa58e9f-1c39-460e-8860-71af6af63kms", nonExistingProduct);
        assertNull(result);
    }
    @Test
    void testDeleteProductSuccess() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-4687-8860-71af6af63bl9");
        product.setProductName("Teh");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertNotNull(productRepository.findById("eb558e9f-1c39-4687-8860-71af6af63bl9"));
        productRepository.delete("eb558e9f-1c39-4687-8860-71af6af63bl9");
        assertNull(productRepository.findById("eb558e9f-1c39-4687-8860-71af6af63bl9"));
    }
    @Test
    void testDeleteProductNotFound() {
        productRepository.delete("1234");
        assertNull(productRepository.findById("1234"));
    }
}