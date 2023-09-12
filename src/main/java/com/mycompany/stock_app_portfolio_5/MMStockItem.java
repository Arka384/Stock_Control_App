package com.mycompany.stock_app_portfolio_5;


public class MMStockItem {
    private int departmentId;
    private String productCode;
    private String productName;
    private String productDesc;
    private int unitPricePounds;
    private int unitPricePence;
    private int quantity;

    public MMStockItem(int departmentId, String productCode, String productName, String productDesc, int unitPricePounds, int unitPricePence, int quantity) {
        this.departmentId = departmentId;
        this.productCode = productCode;
        this.productName = productName;
        this.productDesc = productDesc;
        this.unitPricePounds = unitPricePounds;
        this.unitPricePence = unitPricePence;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public int getUnitPricePounds() {
        return unitPricePounds;
    }

    public int getUnitPricePence() {
        return unitPricePence;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getProductCode() {
        return productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "MMStockItem{" + "departmentId=" + departmentId + ", productCode=" + productCode + ", productName=" + productName + ", productDesc=" + productDesc + ", unitPricePounds=" + unitPricePounds + ", unitPricePence=" + unitPricePence + ", quantity=" + quantity + '}';
    }
    
    
}
