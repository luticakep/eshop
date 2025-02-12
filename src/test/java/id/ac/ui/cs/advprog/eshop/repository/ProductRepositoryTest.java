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
    void testDeleteProduct() {
        Product product3 = new Product();
        product3.setProductId("eb558e9f-1c39-1205-8860-71af6af63bd6");
        product3.setProductName("Es Krim Karambol");
        product3.setProductQuantity(100);
        productRepository.create(product3);

        assertNotNull(productRepository.findById("eb558e9f-1c39-1205-8860-71af6af63bd6"));
        productRepository.delete("eb558e9f-1c39-1205-8860-71af6af63bd6");
        assertNull(productRepository.findById("eb558e9f-1c39-1205-8860-71af6af63bd6"));
    }
    @Test
    void testDeleteProductIfEmpty() {
        productRepository.delete("1234");
        assertNull(productRepository.findById("1234"));
    }

    @Test
    void testEditProduct() {
        Product product4 = new Product();
        product4.setProductId("eb558e9f-1c39-460e-3321-71af6af63bd6");
        product4.setProductName("Kucing");
        product4.setProductQuantity(10);
        productRepository.create(product4);

        Product updated =  new Product();
        updated.setProductQuantity(20);
        productRepository.edit(updated);

        Product editedProduct = productRepository.findById("eb558e9f-1c39-460e-3321-71af6af63bd6");
        assertNotNull(editedProduct);
        assertEquals(20, editedProduct.getProductQuantity());
    }
    @Test
    void testEditProductIfEmpty() {
        Product product6 = new Product();
        product6.setProductId("3se58e9f-1c39-460e-3321-71af6af63bd6");
        product6.setProductName("Kucing");
        product6.setProductQuantity(10);

        productRepository.create(product6);
        Product updated =  productRepository.edit(product6);

        assertNull(updated);
    }
}