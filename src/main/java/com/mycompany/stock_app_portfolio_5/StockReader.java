package com.mycompany.stock_app_portfolio_5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class StockReader {
    
    public void loadCSV(StockManager stockManager) throws FileNotFoundException, IOException{
        //Scanner sc = new Scanner(new File("Resources/data.csv"));
        //sc.useDelimiter(",");
        BufferedReader reader =new BufferedReader(new FileReader("Resources/data.csv"));
        String line = "";
        int i = 0;
        while((line = reader.readLine()) != null){
            String lineData[] = line.trim().split(",");
            String code = lineData[0];
            String title = lineData[1];
            String desc = lineData[2];
            int price = Integer.parseInt(lineData[3]);
            int quantity = Integer.parseInt(lineData[4]);
            StockItem item = new StockItem(code, title, desc, price, quantity);
            
            stockManager.addStock(item);
            i++;
        }
        stockManager.stockSize = i;
    }
    
    public void loadMMCSV(MMStockManager mmStockManager, StockManager stockManager) throws FileNotFoundException, IOException{
        BufferedReader reader =new BufferedReader(new FileReader("Resources/mm_data.csv"));
        String line = "";
        
        while((line = reader.readLine()) != null){
            String lineData[] = line.trim().split(",");
            int deptId = Integer.parseInt(lineData[0]);
            String code = lineData[1];
            String name = lineData[2];
            String desc = lineData[3];
            int pricePounds = Integer.parseInt(lineData[4]);
            int pricePence = Integer.parseInt(lineData[5]);
            int quantity = Integer.parseInt(lineData[6]);
            
            MMStockItem mmStock = new MMStockItem(deptId, code, name, desc, pricePounds, pricePence, quantity);
            mmStockManager.addStock(mmStock);
        }
        
        stockManager.addMMStockItems(mmStockManager);
    }
    
}
