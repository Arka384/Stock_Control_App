package com.mycompany.stock_app_portfolio_5;

import java.util.ArrayList;

public class MMStockManager {
    public ArrayList<MMStockItem> mmStocks = new ArrayList<MMStockItem>();
    
    public StockItem getEquivalantStock(MMStockItem mmStock){
        String stockCode = "";
        switch (mmStock.getDepartmentId()) {
            case 1:
                stockCode = "EVE";
                break;
            case 2:
                stockCode = "CAS";
                break;
            case 3:
                stockCode = "SPO";
                break;
            default:
                break;
        }
        stockCode = stockCode + "-" + mmStock.getProductCode() + "-" +"MM";
        StockItem item = new StockItem(stockCode, mmStock.getProductName(),
                mmStock.getProductDesc(), mmStock.getUnitPricePence(), mmStock.getQuantity());
        return item;
    }
    
    public MMStockItem getEquivalantMMStock(StockItem stock){
        String productCode = stock.getProductCode();
        int i = 0;
        String temp = "";
        while(productCode.charAt(i) != '-')
            i++;
        //get the code
        i++;
        while(productCode.charAt(i) != '-'){
            temp = temp + productCode.charAt(i);
            i++;
        }
        
        MMStockItem mmStock = getStock(temp);
        mmStock.setQuantity(stock.getQuantity());
        
        return mmStock;
    }
    
    public MMStockItem getStock(String code){
        for(int i=0;i<mmStocks.size();i++)
            if(mmStocks.get(i).getProductCode().equals(code))
                return mmStocks.get(i);
        return null;
    }
    
    public void addStock(MMStockItem mmstock){
        mmStocks.add(mmstock);
    }
    
    public void showAllStocks(){
        for(int i=0;i<mmStocks.size();i++)
            System.out.println(mmStocks.get(i));
    }
    
}
