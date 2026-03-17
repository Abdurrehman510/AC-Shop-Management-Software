package com.acshop.dao;

import com.acshop.model.Client;
import java.sql.SQLException;
import java.util.List;

public interface ClientDAO {
    void addClient(Client client) throws SQLException;
    void updateClient(Client client) throws SQLException;
    void removeClient(String name) throws SQLException;
    List<Client> getAllClients() throws SQLException;
    List<Client> searchClientByName(String name) throws SQLException;
    Client searchClientByDate(java.sql.Date date) throws SQLException;
    void performService(String name) throws SQLException;
    void updatePackage(String name) throws SQLException;
}
