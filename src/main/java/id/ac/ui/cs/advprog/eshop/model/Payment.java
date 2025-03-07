package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import java.util.UUID;
import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id != null ? id : UUID.randomUUID().toString();
        this.method = method;
        this.paymentData = paymentData;
        this.status = checkPayment(method, paymentData);
    }

    private String checkPayment(String method, Map<String, String> paymentData) {
        if (method == null || method.isEmpty()) {
            return "REJECTED";
        }

        if (method.contains("Voucher")) {
            String voucher = paymentData.get("voucherCode");
            if (voucher != null && voucher.startsWith("ESHOP") && voucher.length() == 16 &&
                    voucher.replaceAll("\\D", "").length() == 8) {
                return "SUCCESS";
            } else {
                return "REJECTED";
            }
        }
        if (method.contains("Cash on delivery")) {
            String address = paymentData.get("address");
            String fee = paymentData.get("deliveryFee");

            if (address != null && !address.trim().isEmpty() &&
                    fee != null && !fee.trim().isEmpty()) {
                return "SUCCESS";
            } else {
                return "REJECTED";
            }
        }
        return "REJECTED";
    }

    public void setStatus(String status) {
        if (status == "REJECTED" || status == "SUCCESS") {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}