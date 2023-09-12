package com.mycompany.stock_app_portfolio_5;

public class StockItem {
    private String productCode;
    private String productTitle;
    private String productDesc;
    private int unitPrice;
    private int quantity;

    public StockItem(String productCode, String productTitle, String productDesc, int unitPrice, int quantity) {
        this.productCode = productCode;
        this.productTitle = productTitle;
        this.productDesc = productDesc;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getProductDesc() {
        return productDesc;
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

    public int getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return "StockItem{" + "productCode=" + productCode + ", productTitle=" + productTitle + ", productDesc=" + productDesc + ", unitPrice=" + unitPrice + ", quantity=" + quantity + '}';
    }
    
}
