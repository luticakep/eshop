package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    List<Payment> payments;
    private Order order;
    private Map<String, String> voucherData;
    private Map<String, String> invalidVoucherData;
    private Map<String, String> codData;
    private Map<String, String> invalidCodData;

    @BeforeEach
    void setUp() {
        List<Product> productList = new ArrayList<>();

        // Create a dummy Product so products won't be empty
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(1);

        productList.add(product);
        order = new Order("13652556-012a-4c07-b546-54eb1396d79b", productList, 1708560000L, "Caplin");

        voucherData = new HashMap<>();
        voucherData.put("voucherCode", "ESHOP1234ABC5678");

        invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "ABC1234XYZ");

        codData = new HashMap<>();
        codData.put("address", "depok");
        codData.put("deliveryFee", "100");

        invalidCodData = new HashMap<>();
        invalidCodData.put("address", "");
    }

    @Test
    void testAddPaymentVoucher_Success() {
        Payment payment = new Payment("payment1", "Voucher code", voucherData);
        Payment result = paymentService.addPayment(order, "Voucher code", voucherData);

        assertEquals("SUCCESS", result.getStatus());
        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    void testAddPaymentCoD_Success() {
        Payment payment = new Payment("payment2", "Cash on delivery", codData);
        Payment result = paymentService.addPayment(order, "Cash on delivery", codData);

        assertEquals("SUCCESS", result.getStatus());
        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    void testAddPaymentInvalidVoucher() {
        Payment payment = new Payment("payment3", "Voucher code", invalidVoucherData);
        Payment result = paymentService.addPayment(order, "Voucher code", invalidVoucherData);

        assertEquals("REJECTED", result.getStatus());
        assertEquals("FAILED", order.getStatus());
    }

    @Test
    void testAddPaymentInvalidPaymentData() {
        Payment payment = new Payment("payment4", "Cash on delivery", invalidCodData);
        Payment result = paymentService.addPayment(order, "Cash on delivery", invalidCodData);

        assertEquals("REJECTED", result.getStatus());
        assertEquals("FAILED", order.getStatus());
    }

    @Test
    void testSetStatus_Success() {
        Payment payment = new Payment("payment5", "Voucher code", voucherData);
        paymentService.setStatus(payment, "SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }
    @Test
    void testSetStatus_Rejected() {
        Payment existingPayment = new Payment("payment5", "Cash on delivery", codData);

        paymentService.setStatus(existingPayment, "REJECTED");
        assertEquals("REJECTED", existingPayment.getStatus());
    }

    @Test
    void testGetPaymentById_Success() {
        Payment mockPayment = new Payment("payment6", "Voucher Code", voucherData);
        doReturn(mockPayment).when(paymentRepository).findById("payment6");

        Payment result = paymentService.getPayment("payment6");
        assertNotNull(result);
        assertEquals("payment6", result.getId());
    }

    @Test
    void testGetPaymentById_NotFound() {
        doReturn(null).when(paymentRepository).findById("notfound");

        Payment result = paymentService.getPayment("notfound");
        assertNull(result);
        verify(paymentRepository, times(1)).findById("notfound");
    }

    @Test
    void testGetAllPayments() {
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment("p1", "Voucher code", voucherData));
        payments.add(new Payment("p2", "Cash on delivery", codData));
        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> results = paymentService.getAllPayments();
        assertEquals(2, results.size());
        verify(paymentRepository, times(1)).findAll();
    }
}
