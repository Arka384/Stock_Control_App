package com.mycompany.stock_app_portfolio_5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIStockItemInput {
    public JFrame inputFrame = new JFrame();
    JButton submitButton;

    public GUIStockItemInput(StockManager stockManager) {
        inputFrame.setLayout(null);
	inputFrame.setSize(300, 360);
	inputFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	inputFrame.setTitle("Create new StockItem object");
        inputFrame.setResizable(false);
        
        //all labels for input and textFields
        JLabel lCode = new JLabel("Stock code:");
        lCode.setBounds(10, 10, 100, 20);
        JTextField tfCode = new JTextField();
        tfCode.setBounds(10, 35, 260, 20);
        
        JLabel lTitle = new JLabel("Stock title:");
        lTitle.setBounds(10, 60, 100, 20);
        JTextField tfTitle = new JTextField();
        tfTitle.setBounds(10, 85, 260, 20);
        
        JLabel lDesc = new JLabel("Stock description:");
        lDesc.setBounds(10, 110, 120, 20);
        JTextField tfDesc = new JTextField();
        tfDesc.setBounds(10, 135, 260, 20);
        
        JLabel lUnitPrice = new JLabel("Stock unit price:");
        lUnitPrice.setBounds(10, 160, 120, 20);
        JTextField tfUnitPrice = new JTextField();
        tfUnitPrice.setBounds(10, 185, 260, 20);
        
        JLabel lQuantity = new JLabel("Stock quantity:");
        lQuantity.setBounds(10, 210, 100, 20);
        JTextField tfQuantity = new JTextField();
        tfQuantity.setBounds(10, 235, 260, 20);
        
        
        inputFrame.add(lCode);
        inputFrame.add(tfCode);
        inputFrame.add(lTitle);
        inputFrame.add(tfTitle);
        inputFrame.add(lDesc);
        inputFrame.add(tfDesc);
        inputFrame.add(lUnitPrice);
        inputFrame.add(tfUnitPrice);
        inputFrame.add(lQuantity);
        inputFrame.add(tfQuantity);
        
        //the submit button
        submitButton = new JButton("Create StockItem");
        submitButton.setLayout(null);
        submitButton.setBounds(140, 280, 135, 25);
        inputFrame.add(submitButton);
        
        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int unitPrice = -1;
                int quantity = -1;
                String code = tfCode.getText();
                String title = tfTitle.getText();
                String desc = tfDesc.getText();
                String unitPriceStr = tfUnitPrice.getText();
                if(!unitPriceStr.isEmpty())
                    unitPrice = Integer.parseInt(tfUnitPrice.getText());
                String quantityStr = tfQuantity.getText();
                if(!quantityStr.isEmpty())
                    quantity = Integer.parseInt(tfQuantity.getText());
                
                if(code.isEmpty() || title.isEmpty())
                    return;
                
                StockItem item = new StockItem(code, title, desc, unitPrice, quantity);
                stockManager.addStock(item, 1);
                
                //close the window
                inputFrame.setVisible(false);
                tfCode.setText("");
                tfTitle.setText("");
                tfDesc.setText("");
                tfUnitPrice.setText("");
                tfQuantity.setText("");
            }
        }
        );
       
        
        inputFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                inputFrame.setVisible(false);
                tfCode.setText("");
                tfTitle.setText("");
                tfDesc.setText("");
                tfUnitPrice.setText("");
                tfQuantity.setText("");
            }
        });
    }
    
    
}
