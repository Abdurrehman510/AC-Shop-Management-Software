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
        setSize(1200, 580);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 3, 10, 10));

        JLabel nameLabel = new JLabel("                 Client Name");
        nameLabel.setFont(new Font("Elephant", Font.PLAIN, 20));
        nameField = new JTextField();
        
        JLabel dopLabel = new JLabel("             Date of Purchase ");
        dopLabel.setFont(new Font("Elephant", Font.PLAIN, 19));
        dopField = new JTextField();
        
        JLabel addressLabel = new JLabel("                      Address");
        addressLabel.setFont(new Font("Elephant", Font.PLAIN, 20));
        addressField = new JTextField();
        
        JLabel phoneLabel = new JLabel("                       Phone");
        phoneLabel.setFont(new Font("Elephant", Font.PLAIN, 20));
        phoneField = new JTextField();
        
        JLabel acModelLabel = new JLabel("                    AC Model ");
        acModelLabel.setFont(new Font("Elephant", Font.PLAIN, 19));
        acModelField = new JTextField();
        
        JLabel priceLabel = new JLabel("                        Price ");
        priceLabel.setFont(new Font("Elephant", Font.PLAIN, 19));
        priceField = new JTextField();
        
        JLabel descriptionLabel = new JLabel("                  Description");
        descriptionLabel.setFont(new Font("Elephant", Font.PLAIN, 19));
        descriptionField = new JTextField();
        
        JLabel searchLabel = new JLabel("       Search[Name / Date]");
        searchLabel.setFont(new Font("Elephant", Font.PLAIN, 20));
        searchField = new JTextField();
        
        Font btnFont = new Font("Comic Sans MS", Font.BOLD, 15);
        
        JButton addButton = new JButton("Add Client");
        addButton.setFont(btnFont);
        addButton.addActionListener(e -> addClient());
        
        JButton removeButton = new JButton("Remove Client");
        removeButton.setFont(btnFont);
        removeButton.addActionListener(e -> removeClient());
        
        JButton updateButton = new JButton("Update Client");
        updateButton.setFont(btnFont);
        updateButton.addActionListener(e -> updateClient());
        
        JButton searchButton = new JButton("Search");
        searchButton.setFont(btnFont);
        searchButton.addActionListener(e -> searchClient());
        
        JButton displayButton = new JButton("Display All Clients");
        displayButton.setFont(btnFont);
        displayButton.addActionListener(e -> displayAllClients());
        
        JButton serviceButton = new JButton("Service");
        serviceButton.setFont(btnFont);
        serviceButton.addActionListener(e -> performService());
        
        JButton packageButton = new JButton("Update Package");
        packageButton.setFont(btnFont);
        packageButton.addActionListener(e -> updatePackage());
        
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(btnFont);
        exitButton.addActionListener(e -> exitApp());
        
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Consolas", Font.BOLD, 17));

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
