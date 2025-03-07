package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org. junit. jupiter.api.Assertions .*;

class PaymentTest {
    private Map<String, String> voucher;
    private Map<String, String> cod;

    @BeforeEach
    void setUp() {
        voucher = new HashMap<>();
        voucher.put("voucherCode", "ESHOP1234ABC5678");

        cod = new HashMap<>();
        cod.put("address", "depok");
        cod.put("deliveryFee", "100");
    }

    @Test
    void testVoucherPayment_Success() {
        Payment payment = new Payment("1", "Voucher Code", voucher);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testVoucherPaymentNoEshop() {
        voucher.put("voucherCode", "ABCDE1234ABC5678");
        Payment payment = new Payment("2", "Voucher code", voucher);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testVoucherPaymentInvalidLength() {
        voucher.put("voucherCode", "ESHOP1234ABC");
        Payment payment = new Payment("3", "Voucher code", voucher);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testVoucherPaymentInvalidCharacters() {
        voucher.put("voucherCode", "ESHOP12345678999");
        Payment payment = new Payment("4", "Voucher code", voucher);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCodPayment_Success() {
        Payment payment = new Payment("5", "Cash on delivery", cod);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCodPaymentInvalidAddress() {
        cod.put("address", " ");
        Payment payment = new Payment("6", "Cash on delivery", cod);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCodPaymentInvalidDeliveryFee() {
        cod.put("deliveryFee", " ");
        Payment payment = new Payment("7", "Cash on delivery", cod);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment("8", "Voucher code", voucher);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("9", "Cash on delivery", cod);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }
}