package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService service;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProductPage() {
        String viewName = controller.createProductPage(model);
        verify(model).addAttribute(eq("product"), any(Product.class));
        assertEquals("CreateProduct", viewName);
    }

    @Test
    void createProductPost() {
        Product product = new Product();
        String viewName = controller.createProductPost(product, model);
        verify(service).create(product);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void productListPage() {
        List<Product> mockProducts = Arrays.asList(new Product(), new Product());
        when(service.findAll()).thenReturn(mockProducts);

        String viewName = controller.productListPage(model);
        verify(model).addAttribute("products", mockProducts);
        assertEquals("ProductList", viewName);
    }

    @Test
    void editProductPageFound() {
        Product mockProduct = new Product();
        when(service.findById("123")).thenReturn(mockProduct);

        String viewName = controller.editProductPage("123", model);
        verify(model).addAttribute("product", mockProduct);
        assertEquals("EditProduct", viewName);
    }

    @Test
    void editProductPageNotFound() {
        when(service.findById("1234")).thenReturn(null);

        String viewName = controller.editProductPage("1234", model);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void editProduct() {
        Product product = new Product();
        String viewName = controller.editProduct(product, model);
        verify(service).edit(product);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void deleteProduct() {
        String viewName = controller.deleteProduct("123");
        verify(service).delete("123");
        assertEquals("redirect:/product/list", viewName);
    }
}
