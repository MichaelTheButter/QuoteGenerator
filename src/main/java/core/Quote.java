package core;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Quote definition with standard methods
 */
public class Quote {
    private String quoteNumber;
    private LocalDate quoteDate;
    private int supplierId;
    private int customerId;
    private int employeeId;
    private double totalPrice;
    private Map<Integer, Integer> productList;

    public Quote(int supplierId, int customerId, int employeeId) {
        this.supplierId = supplierId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.quoteDate = LocalDate.now();
    }

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public void setQuoteNumber(String quoteNumber) {
        this.quoteNumber = quoteNumber;
    }

    public LocalDate getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(LocalDate quoteDate) {
        this.quoteDate = quoteDate;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<Integer, Integer> getProductList() {
        return productList;
    }

    public void setProductList(Map<Integer, Integer> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "supplierId=" + supplierId +
                ", customerId=" + customerId +
                ", employeeId=" + employeeId +
                '}';
    }
}
