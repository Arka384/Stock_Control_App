package com.mycompany.stock_app_portfolio_5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GUI {
    private ArrayList<String[]> tableData;
    private String[] colNames = {
        "Code",
        "Title",
	"Description",
	"Unit Price",
	"Quantity"
    };
    
    JFrame frame = new JFrame();
    private DefaultTableModel tableModel;
    private JTable table;
    JButton buyButton;
    JButton sellButton;
    JButton addStockButton;
    private boolean activeAfterAddStock = false;
    
    public GUI(StockManager stockManager, MMStockManager mmStockManager, Logger logger, GUIStockItemInput guiStockItemInput) {	
	frame.setLayout(null);
	frame.setSize(1000, 350);
	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	frame.setTitle("Stock Control with low stock reporting");
        frame.setResizable(false);
		
	buyButton = new JButton("Buy");
        buyButton.setLayout(null);
        buyButton.setBounds(910, 270, 60, 25);
	sellButton = new JButton("Sell");
        sellButton.setLayout(null);
        sellButton.setBounds(830, 270, 60, 25);
        addStockButton = new JButton("New StockItem");
        addStockButton.setLayout(null);
        addStockButton.setBounds(20, 270, 124, 25);
        
        buyButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = getSelectedStockCode();
                stockManager.buyStock(code);
                int col = 4;
                int row = table.getSelectedRow();
                table.getModel().setValueAt(stockManager.getStock(code).getQuantity(), row, col);
                logger.logTransaction(stockManager.getStock(code));
            }
        }
        );
        
        sellButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = getSelectedStockCode();
                stockManager.sellStock(code);
                
                if(stockManager.getStock(code).getQuantity() < 5) {
                    String alertCode = "Low Stock Alert";
                    String alertMessage = stockManager.getStock(code).getProductCode() + " " 
                            + "Quantity Left: " + stockManager.getStock(code).getQuantity();
                    JOptionPane.showMessageDialog(frame, alertMessage, alertCode, JOptionPane.WARNING_MESSAGE);
                }
               
                
                int col = 4;
                int row = table.getSelectedRow();
                table.getModel().setValueAt(stockManager.getStock(code).getQuantity(), row, col);
                logger.logTransaction(stockManager.getStock(code));
            }
        }
        );
        
        addStockButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                activeAfterAddStock = true;
                guiStockItemInput.inputFrame.setVisible(true);
            }
        }
        );
        
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowActivated(WindowEvent e){
                if(activeAfterAddStock) {
                    setTableData(stockManager.generateTableData());
                    fillTable();
                    activeAfterAddStock = false;
                }
            }
        });
        
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                try {
                    logger.updateCSV(stockManager, mmStockManager);
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        });
        
        
	//buttonPanel.add(buyButton);
        //buttonPanel.add(sellButton);
        frame.add(buyButton);
        frame.add(sellButton);
        frame.add(addStockButton);

	//frame.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void fillTable(){
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        
        for(String col:colNames)
            tableModel.addColumn(col);
        
        for(String[] e:tableData)
            tableModel.addRow(e);
        
        table.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 970, 230);
        table.setFillsViewportHeight(true);
		
	frame.add(scrollPane);
        frame.setVisible(true);
    }
    
    public String getSelectedStockCode(){
        int col = 0;
        int row = table.getSelectedRow();
        System.out.println("row" + row);
        String rowData = (String) table.getValueAt(row, col);
        
        return rowData;
    }

    public ArrayList<String[]> getTableData() {
        return tableData;
    }

    public void setTableData(ArrayList<String[]> tableData) {
        this.tableData = tableData;
    }

    public String[] getColNames() {
        return colNames;
    }

    public void setColNames(String[] colNames) {
        this.colNames = colNames;
    }
    
}

