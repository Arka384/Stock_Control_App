package com.mycompany.stock_app_portfolio_5;

import java.util.ArrayList;

public class StockManager {
    public ArrayList<StockItem> stocks = new ArrayList<StockItem>();
    public int stockSize = 0;
    
    public ArrayList<String[]> generateTableData(){
        ArrayList<String[]> data = new ArrayList<String[]>();
        
        for (StockItem stock : stocks) {
            String[] temp = new String[5];
            temp[0] = stock.getProductCode();
            temp[1] = stock.getProductTitle();
            temp[2] = stock.getProductDesc();
            temp[3] = Integer.toString(stock.getUnitPrice());
            temp[4] = Integer.toString(stock.getQuantity());
            data.add(temp);
        }
        return data;
    }
    
    public void addMMStockItems(MMStockManager mmStockManager){
        for(MMStockItem mmStock : mmStockManager.mmStocks){
            StockItem temp = mmStockManager.getEquivalantStock(mmStock);
            stocks.add(temp);
        }
    }
    
    public StockItem getStock(String code){
        for(int i=0;i<stocks.size();i++)
            if(stocks.get(i).getProductCode().equals(code))
                return stocks.get(i);
        return null;
    }
    
    public void addStock(StockItem stock){
        stocks.add(stock);
    }
    
    public void addStock(StockItem stock, int differ) {
        stocks.add(0, stock);
        stockSize++;
    }
    
    public void buyStock(String code){
        for(int i=0;i<stocks.size();i++){
             if(stocks.get(i).getProductCode().equals(code)){
                stocks.get(i).setQuantity(stocks.get(i).getQuantity()+1);
             }
        }
    }
    
    public void sellStock(String code){
        for(int i=0;i<stocks.size();i++){
            if(stocks.get(i).getProductCode().equals(code)){
                if(stocks.get(i).getQuantity() == 0)
                    return;
                stocks.get(i).setQuantity(stocks.get(i).getQuantity()-1);
            }
        }
    }
    
    public void showAllStocks(){
        for(int i=0;i<stocks.size();i++)
            System.out.println(stocks.get(i));
    }
}
