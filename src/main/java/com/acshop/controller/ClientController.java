package com.acshop.controller;

import com.acshop.dao.ClientDAO;
import com.acshop.dao.ClientDAOImpl;
import com.acshop.model.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientController {
    private ClientDAO clientDAO;

    public ClientController() {
        this.clientDAO = new ClientDAOImpl();
    }

    public void addClient(Client client) throws SQLException {
        clientDAO.addClient(client);
    }

    public void updateClient(Client client) throws SQLException {
        clientDAO.updateClient(client);
    }

    public void removeClient(String name) throws SQLException {
        clientDAO.removeClient(name);
    }

    public List<Client> getAllClients() throws SQLException {
        return clientDAO.getAllClients();
    }

    public List<Client> searchClientByName(String name) throws SQLException {
        return clientDAO.searchClientByName(name);
    }

    public Client searchClientByDate(java.sql.Date date) throws SQLException {
        return clientDAO.searchClientByDate(date);
    }

    public void performService(String name) throws SQLException {
        clientDAO.performService(name);
    }

    public void updatePackage(String name) throws SQLException {
        clientDAO.updatePackage(name);
    }
}
