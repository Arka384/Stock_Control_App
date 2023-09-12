package com.mycompany.stock_app_portfolio_5;

import java.io.IOException;
import java.util.logging.Level;

public class Stock_App_Portfolio_5 {

    public static void main(String[] args) {
        
        StockManager stockManager = new StockManager();
        MMStockManager mmStockManager = new MMStockManager();
        
        StockReader stockReader = new StockReader();
        Logger logger = new Logger();
        
        GUIStockItemInput guiStockitemInput = new GUIStockItemInput(stockManager);
        GUI gui = new GUI(stockManager, mmStockManager, logger, guiStockitemInput);
        
        try {
            stockReader.loadCSV(stockManager);
            stockReader.loadMMCSV(mmStockManager, stockManager);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Stock_App_Portfolio_5.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        gui.setTableData(stockManager.generateTableData());
        gui.fillTable();
        
        
    }
}
