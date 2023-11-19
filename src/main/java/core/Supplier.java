package core;

public class Supplier {
    private String supplierName;
    private String address;
    private int taxNo;

    public Supplier(String supplierName, int taxNo) {
        this.supplierName = supplierName;
        this.taxNo = taxNo;
    }

    public Supplier(String supplierName, String address, int taxNo) {
        this.supplierName = supplierName;
        this.address = address;
        this.taxNo = taxNo;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(int taxNo) {
        this.taxNo = taxNo;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierName='" + supplierName + '\'' +
                ", address='" + address + '\'' +
                ", taxNo=" + taxNo +
                '}';
    }
}
