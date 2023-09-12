package com.mycompany.stock_app_portfolio_5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

public class Logger {
    public void logTransaction(StockItem item){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        
        try {
            FileWriter fw = new FileWriter("log.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            
            out.println("Time: "+dtf.format(now));
            out.println("Product Code: "+item.getProductCode());
            out.println("Quantity: "+1);
            out.println("Product unit price: "+item.getUnitPrice());
            out.println("\n");
            
            out.close();
            bw.close();
            fw.close();
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateCSV(StockManager stockManager, MMStockManager mmStockManager) throws IOException{
        File csvFile = new File("Resources/data.csv");
        int i;
        try (FileWriter fileWriter = new FileWriter(csvFile)) {
            for(i=0;i<stockManager.stockSize;i++){
                StockItem stock = stockManager.stocks.get(i);
                
                StringBuilder line = new StringBuilder();
                line.append(stock.getProductCode());
                line.append(',');
                line.append(stock.getProductTitle());
                line.append(',');
                line.append(stock.getProductDesc());
                line.append(',');
                line.append(stock.getUnitPrice());
                line.append(',');
                line.append(stock.getQuantity());
                line.append(',');
                line.append('\n');
                
                fileWriter.write(line.toString());
            }
            fileWriter.close();
        }
        
        //writing the values of mmSotcks
        csvFile = new File("Resources/mm_data.csv");
        try (FileWriter fileWriter = new FileWriter(csvFile)) {
            for(;i<stockManager.stocks.size();i++){
                StockItem stock = stockManager.stocks.get(i);
                MMStockItem mmStock = mmStockManager.getEquivalantMMStock(stock);
                
                StringBuilder line = new StringBuilder();
                line.append(Integer.toString(mmStock.getDepartmentId()));
                line.append(',');
                line.append(mmStock.getProductCode());
                line.append(',');
                line.append(mmStock.getProductName());
                line.append(',');
                line.append(mmStock.getProductDesc());
                line.append(',');
                line.append(mmStock.getUnitPricePounds());
                line.append(',');
                line.append(mmStock.getUnitPricePence());
                line.append(',');
                line.append(mmStock.getQuantity());
                line.append(',');
                line.append('\n');
                
                fileWriter.write(line.toString());
            }
            fileWriter.close();
        }
    }
  
}
