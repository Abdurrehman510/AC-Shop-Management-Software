package com.acshop.view;

import com.acshop.controller.ClientController;
import com.acshop.model.Client;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainFrame extends JFrame {
    private ClientController controller;
    private JTextField nameField, dopField, addressField, phoneField, acModelField, searchField, priceField, descriptionField;
    private JTextArea displayArea;

    public MainFrame() {
        controller = new ClientController();
        createUI();
    }

    private void createUI() {
        setTitle("AC Shop Management System");
        setSize(1200, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // UI Enhancements
        getContentPane().setBackground(new Color(245, 245, 250));
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 3, 10, 10));
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 15);
        Color fgColor = new Color(50, 50, 60);

        JLabel nameLabel = new JLabel("       Client Name");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(fgColor);
        nameField = new JTextField();
        nameField.setFont(fieldFont);
        
        JLabel dopLabel = new JLabel("       Date of Purchase ");
        dopLabel.setFont(labelFont);
        dopLabel.setForeground(fgColor);
        dopField = new JTextField();
        dopField.setFont(fieldFont);
        
        JLabel addressLabel = new JLabel("       Address");
        addressLabel.setFont(labelFont);
        addressLabel.setForeground(fgColor);
        addressField = new JTextField();
        addressField.setFont(fieldFont);
        
        JLabel phoneLabel = new JLabel("       Phone");
        phoneLabel.setFont(labelFont);
        phoneLabel.setForeground(fgColor);
        phoneField = new JTextField();
        phoneField.setFont(fieldFont);
        
        JLabel acModelLabel = new JLabel("       AC Model ");
        acModelLabel.setFont(labelFont);
        acModelLabel.setForeground(fgColor);
        acModelField = new JTextField();
        acModelField.setFont(fieldFont);
        
        JLabel priceLabel = new JLabel("       Price ");
        priceLabel.setFont(labelFont);
        priceLabel.setForeground(fgColor);
        priceField = new JTextField();
        priceField.setFont(fieldFont);
        
        JLabel descriptionLabel = new JLabel("       Description");
        descriptionLabel.setFont(labelFont);
        descriptionLabel.setForeground(fgColor);
        descriptionField = new JTextField();
        descriptionField.setFont(fieldFont);
        
        JLabel searchLabel = new JLabel("       Search[Name/Date]");
        searchLabel.setFont(labelFont);
        searchLabel.setForeground(fgColor);
        searchField = new JTextField();
        searchField.setFont(fieldFont);
        
        Font btnFont = new Font("Segoe UI", Font.BOLD, 14);
        Color btnBg = new Color(70, 130, 180);
        Color btnFg = Color.WHITE;
        
        JButton addButton = createStyledButton("Add Client", btnFont, btnBg, btnFg);
        addButton.addActionListener(e -> addClient());
        
        JButton removeButton = createStyledButton("Remove Client", btnFont, new Color(220, 53, 69), btnFg);
        removeButton.addActionListener(e -> removeClient());
        
        JButton updateButton = createStyledButton("Update Client", btnFont, new Color(255, 193, 7), Color.BLACK);
        updateButton.addActionListener(e -> updateClient());
        
        JButton searchButton = createStyledButton("Search", btnFont, btnBg, btnFg);
        searchButton.addActionListener(e -> searchClient());
        
        JButton displayButton = createStyledButton("Display All Clients", btnFont, new Color(40, 167, 69), btnFg);
        displayButton.addActionListener(e -> displayAllClients());
        
        JButton serviceButton = createStyledButton("Service", btnFont, new Color(23, 162, 184), btnFg);
        serviceButton.addActionListener(e -> performService());
        
        JButton packageButton = createStyledButton("Update Package", btnFont, new Color(111, 66, 193), btnFg);
        packageButton.addActionListener(e -> updatePackage());
        
        JButton exitButton = createStyledButton("Exit", btnFont, new Color(108, 117, 125), btnFg);
        exitButton.addActionListener(e -> exitApp());
        
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Consolas", Font.PLAIN, 15));
        displayArea.setBackground(new Color(250, 250, 255));
        displayArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 210)), 
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(dopLabel);
        panel.add(dopField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(acModelLabel);
        panel.add(acModelField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(searchLabel);
        panel.add(searchField);
        
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(updateButton);
        panel.add(searchButton);
        panel.add(displayButton);
        panel.add(serviceButton);
        panel.add(packageButton);
        panel.add(exitButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
    }
    
    private JButton createStyledButton(String text, Font font, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        return btn;
    }
    
    private void clearFields() {
        nameField.setText("");
        dopField.setText("");
        addressField.setText("");
        phoneField.setText("");
        acModelField.setText("");
        searchField.setText("");
        priceField.setText("");
        descriptionField.setText("");
    }
    
    // implement methods using controller
    private void addClient() {
        try {
            String name = nameField.getText();
            String dopStr = dopField.getText();
            String address = addressField.getText();
            String phoneStr = phoneField.getText();
            String acModel = acModelField.getText();
            String priceStr = priceField.getText();
            String description = descriptionField.getText();

            if (name.isEmpty() || dopStr.isEmpty() || address.isEmpty() || phoneStr.isEmpty() || acModel.isEmpty() || priceStr.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "PLEASE FILL ALL FIELDS.");
                return;
            }
            if (phoneStr.length() != 10) {
                JOptionPane.showMessageDialog(this, "PLEASE ENTER A VALID PHONE NO.");
                return;
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date dop = sdf.parse(dopStr);
            Long phone = Long.parseLong(phoneStr);
            int price = Integer.parseInt(priceStr);
            
            Client client = new Client(name, dop, address, phone, acModel, price, description, 3, dop, com.acshop.util.DateUtil.addMonths(dop, 4));
            controller.addClient(client);
            JOptionPane.showMessageDialog(this, "CLIENT " + name + " ADDED SUCCESSFULLY.");
            clearFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR: " + e.getMessage());
        }
    }
    
    private void removeClient() {
        String name = searchField.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "PLEASE ENTER A NAME TO REMOVE.");
            return;
        }
        try {
            controller.removeClient(name);
            JOptionPane.showMessageDialog(this, "CLIENT " + name + " REMOVED SUCCESSFULLY.");
            clearFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR: " + e.getMessage());
        }
    }
    
    private void updateClient() {
         try {
            String name = nameField.getText();
            String dopStr = dopField.getText();
            String address = addressField.getText();
            String phoneStr = phoneField.getText();
            String acModel = acModelField.getText();
            String priceStr = priceField.getText();
            String description = descriptionField.getText();

            if (name.isEmpty() || dopStr.isEmpty() || address.isEmpty() || phoneStr.isEmpty() || acModel.isEmpty() || priceStr.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "PLEASE FILL ALL FIELDS.");
                return;
            }
            if (phoneStr.length() != 10) {
                JOptionPane.showMessageDialog(this, "PLEASE ENTER A VALID PHONE NO.");
                return;
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date dop = sdf.parse(dopStr);
            Long phone = Long.parseLong(phoneStr);
            int price = Integer.parseInt(priceStr);
            
            Client client = new Client();
            client.setName(name);
            client.setDop(dop);
            client.setAddress(address);
            client.setPhone(phone);
            client.setAcModel(acModel);
            client.setPrice(price);
            client.setDescription(description);
            
            controller.updateClient(client);
            JOptionPane.showMessageDialog(this, "CLIENT " + name + " UPDATED SUCCESSFULLY.");
            clearFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR: " + e.getMessage());
        }
    }
    
    private void searchClient() {
        String query = searchField.getText();
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, "PLEASE ENTER A NAME OR DATE TO SEARCH.");
            return;
        }
        try {
            if (Character.isDigit(query.charAt(0)) && query.contains("-")) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date date = sdf.parse(query);
                Client c = controller.searchClientByDate(new java.sql.Date(date.getTime()));
                if (c != null) {
                    displayArea.setText(c.toString() + "\n\n");
                } else {
                    displayArea.setText("CLIENT NOT FOUND.");
                }
            } else {
                List<Client> clients = controller.searchClientByName(query);
                if (clients.isEmpty()) {
                    displayArea.setText("NO CLIENTS TO DISPLAY.");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (Client c : clients) {
                        sb.append(c.toString()).append("\n\n");
                    }
                    displayArea.setText(sb.toString());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR: " + e.getMessage());
        }
    }
    
    private void displayAllClients() {
        try {
            List<Client> clients = controller.getAllClients();
            if (clients.isEmpty()) {
                displayArea.setText("NO CLIENTS TO DISPLAY.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Client c : clients) {
                    sb.append(c.toString()).append("\n\n");
                }
                displayArea.setText(sb.toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR: " + e.getMessage());
        }
    }
    
    private void performService() {
        String service = searchField.getText();
        if (service.isEmpty()) {
            JOptionPane.showMessageDialog(this, "PLEASE FILL SEARCH FIELD.");
            return;
        }
        try {
            controller.performService(service);
            JOptionPane.showMessageDialog(this, "Service performed successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR: " + e.getMessage());
        }
    }
    
    private void updatePackage() {
        String packName = searchField.getText();
        if (packName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "PLEASE FILL SEARCH FIELD.");
            return;
        }
        try {
            controller.updatePackage(packName);
            JOptionPane.showMessageDialog(this, "CLIENT " + packName + " PACKAGE UPDATED SUCCESSFULLY.");
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this, "ERROR: " + e.getMessage());
        }
    }
    
    private void exitApp() {
        JOptionPane.showMessageDialog(this, "Exiting !!!!!");
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
