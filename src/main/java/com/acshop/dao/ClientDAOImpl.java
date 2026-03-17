package com.acshop.dao;

import com.acshop.model.Client;
import com.acshop.util.DBConnection;
import com.acshop.util.DateUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    @Override
    public void addClient(Client client) throws SQLException {
        String sql = "INSERT INTO clients (name, dop, address, acModel, phone, price, description, remainingServices, currentPackDate, nextServiceDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, client.getName());
            stmt.setDate(2, new java.sql.Date(client.getDop().getTime()));
            stmt.setString(3, client.getAddress());
            stmt.setString(4, client.getAcModel());
            stmt.setLong(5, client.getPhone());
            stmt.setInt(6, client.getPrice());
            stmt.setString(7, client.getDescription());
            stmt.setInt(8, client.getRemainingServices());
            
            if (client.getCurrentPackDate() != null) {
                stmt.setDate(9, new java.sql.Date(client.getCurrentPackDate().getTime()));
            } else {
                stmt.setNull(9, Types.DATE);
            }

            if (client.getNextServiceDate() != null) {
                stmt.setDate(10, new java.sql.Date(client.getNextServiceDate().getTime()));
            } else {
                stmt.setNull(10, Types.DATE);
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public void updateClient(Client client) throws SQLException {
        String sql = "UPDATE clients SET dop = ?, address = ?, phone = ?, acModel = ?, price = ?, description = ? WHERE name = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(client.getDop().getTime()));
            stmt.setString(2, client.getAddress());
            stmt.setLong(3, client.getPhone());
            stmt.setString(4, client.getAcModel());
            stmt.setInt(5, client.getPrice());
            stmt.setString(6, client.getDescription());
            stmt.setString(7, client.getName());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Client update failed, no client found with name: " + client.getName());
            }
        }
    }

    @Override
    public void removeClient(String name) throws SQLException {
        String sql = "DELETE FROM clients WHERE name = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                 throw new SQLException("No client found with name: " + name);
            }
        }
    }

    @Override
    public List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
             while (rs.next()) {
                 clients.add(extractClientFromResultSet(rs));
             }
        }
        return clients;
    }

    @Override
    public List<Client> searchClientByName(String name) throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients WHERE name LIKE ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
             
             stmt.setString(1, "%" + name + "%");
             try(ResultSet rs = stmt.executeQuery()) {
                 while (rs.next()) {
                     clients.add(extractClientFromResultSet(rs));
                 }
             }
        }
        return clients;
    }

    @Override
    public Client searchClientByDate(java.sql.Date date) throws SQLException {
        String sql = "SELECT * FROM clients WHERE nextServiceDate = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
             
             stmt.setDate(1, date);
             try(ResultSet rs = stmt.executeQuery()) {
                 if (rs.next()) {
                     return extractClientFromResultSet(rs);
                 }
             }
        }
        return null;
    }

    @Override
    public void performService(String name) throws SQLException {
        List<Client> clients = searchClientByName(name);
        if (clients.isEmpty()) {
            throw new SQLException("CLIENT NOT FOUND.");
        }
        
        // Exact match preferred usually
        Client targetClient = null;
        for (Client c : clients) {
            if (c.getName().equalsIgnoreCase(name)) {
                targetClient = c;
                break;
            }
        }
        if (targetClient == null) targetClient = clients.get(0); // fallback to partial match if no exact
        
        if (targetClient.getRemainingServices() > 0) {
            String sql = "UPDATE clients SET remainingServices = ?, nextServiceDate = ? WHERE name = ?";
            try (Connection con = DBConnection.getConnection();
                 PreparedStatement stmt = con.prepareStatement(sql)) {
                 
                 int remServices = targetClient.getRemainingServices() - 1;
                 targetClient.setRemainingServices(remServices);
                 
                 java.util.Date calculatedNextServiceDate = DateUtil.calculateNextServiceDate(
                        targetClient.getDop(), targetClient.getNextServiceDate(), remServices);
                        
                 targetClient.setNextServiceDate(calculatedNextServiceDate);
                 
                 stmt.setInt(1, remServices);
                 if (calculatedNextServiceDate != null) {
                     stmt.setDate(2, new java.sql.Date(calculatedNextServiceDate.getTime()));
                 } else {
                     stmt.setNull(2, Types.DATE);
                 }
                 stmt.setString(3, targetClient.getName());
                 
                 stmt.executeUpdate();
            }
        } else {
            throw new SQLException("No services remaining for client: " + name + "\nPlease update the service package.");
        }
    }

    @Override
    public void updatePackage(String name) throws SQLException {
        List<Client> clients = searchClientByName(name);
        if (clients.isEmpty()) {
            throw new SQLException("CLIENT NOT FOUND.");
        }
        Client updatePackClient = null;
        for (Client c : clients) {
            if (c.getName().equalsIgnoreCase(name)) {
                updatePackClient = c;
                break;
            }
        }
        if (updatePackClient == null) updatePackClient = clients.get(0);
        
        // Renew logic:
        updatePackClient.setRemainingServices(3);
        java.util.Date currentPackDate = java.util.Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        updatePackClient.setCurrentPackDate(currentPackDate);
        updatePackClient.setNextServiceDate(DateUtil.addMonths(currentPackDate, 4));
        
        String sql = "UPDATE clients SET remainingServices = ?, currentPackDate = ?, nextServiceDate = ? WHERE name = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
             
             stmt.setInt(1, updatePackClient.getRemainingServices());
             stmt.setDate(2, new java.sql.Date(updatePackClient.getCurrentPackDate().getTime()));
             stmt.setDate(3, new java.sql.Date(updatePackClient.getNextServiceDate().getTime()));
             stmt.setString(4, updatePackClient.getName());
             
             stmt.executeUpdate();
        }
    }

    private Client extractClientFromResultSet(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        java.sql.Date dop = rs.getDate("dop");
        String address = rs.getString("address");
        Long phone = rs.getLong("phone");
        String acModel = rs.getString("acModel");
        int price = rs.getInt("price");
        String description = rs.getString("description");
        int remainingServices = rs.getInt("remainingServices");
        java.sql.Date currentPackDate = rs.getDate("currentPackDate");
        java.sql.Date nextServiceDate = rs.getDate("nextServiceDate");

        return new Client(name, dop, address, phone, acModel, price, description, remainingServices, currentPackDate, nextServiceDate);
    }
}
