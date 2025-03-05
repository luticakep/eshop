package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    void testSavePayment() {
        Map<String, String> voucherData = new HashMap<>();
        voucherData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1", "Voucher Code", voucherData);
        Payment savedPayment = paymentRepository.save(payment);

        assertNotNull(savedPayment, "Saved payment should not be null");
        assertEquals("1", savedPayment.getId());
        assertEquals("SUCCESS", savedPayment.getStatus());
    }

    @Test
    void testPaymentNotFound() {
        Payment payment = paymentRepository.findById("NotFound");
        assertNull(payment, "Payment should be null");
    }

    @Test
    void testFindaAllPayments() {
        Map<String, String> voucherData = new HashMap<>();
        voucherData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("3", "Voucher code", voucherData);
        paymentRepository.save(payment1);

        Map<String, String> codData = new HashMap<>();
        codData.put("address", "depok");
        codData.put("deliveryFee", "100");
        Payment payment2 = new Payment("4", "Cash on delivery", codData);
        paymentRepository.save(payment2);

        List<Payment> payments = paymentRepository.findAll();
        assertNotNull(payments);
        assertTrue(payments.size() >= 2);
    }

    @Test
    void testFindPaymentById() {
        Map<String, String> codData = new HashMap<>();
        codData.put("address", "depok");
        codData.put("deliveryFee", "100");
        Payment payment = new Payment("5", "Cash on delivery", codData);
        paymentRepository.save(payment);

        Payment found = paymentRepository.findById("5");
        assertNotNull(found, "Payment with ID 5 should be found");
        assertEquals("5", found.getId(), "The retrieved payment's ID should be 5");
    }

    @Test
    void testFindPayment_Empty() {
        List<Payment> payments = paymentRepository.findAll();
        assertNotNull(payments, "findAll should not return null even if empty");
        assertTrue(payments.isEmpty(), "return an empty list when no payments have been saved");
    }
}


