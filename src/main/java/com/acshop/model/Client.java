package com.acshop.model;

import java.util.Date;

public class Client {
    private String name;
    private Date dop; // Date of purchase
    private Date currentPackDate; // Changed from static to instance variable
    private Date nextServiceDate;
    private String address;
    private Long phone;
    private String acModel;
    private int price;
    private String description;
    private int remainingServices;

    public Client() {
    }

    public Client(String name, Date dop, String address, Long phone, String acModel, int price, String description,
                  int remainingServices, Date currentPackDate, Date nextServiceDate) {
        this.name = name;
        this.dop = dop;
        this.address = address;
        this.phone = phone;
        this.acModel = acModel;
        this.price = price;
        this.description = description;
        this.remainingServices = remainingServices;
        this.currentPackDate = currentPackDate;
        this.nextServiceDate = nextServiceDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDop() {
        return dop;
    }

    public void setDop(Date dop) {
        this.dop = dop;
    }

    public Date getCurrentPackDate() {
        return currentPackDate;
    }

    public void setCurrentPackDate(Date currentPackDate) {
        this.currentPackDate = currentPackDate;
    }

    public Date getNextServiceDate() {
        return nextServiceDate;
    }

    public void setNextServiceDate(Date nextServiceDate) {
        this.nextServiceDate = nextServiceDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getAcModel() {
        return acModel;
    }

    public void setAcModel(String acModel) {
        this.acModel = acModel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRemainingServices() {
        return remainingServices;
    }

    public void setRemainingServices(int remainingServices) {
        this.remainingServices = remainingServices;
    }

    @Override
    public String toString() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
        StringBuilder sb = new StringBuilder();

        try {
            sb.append("Name: ").append(name != null ? name.toUpperCase() : "Unknown").append("\n")
                    .append("Date of Purchase: ").append(dop != null ? sdf.format(dop) : "Unknown").append("\n")
                    .append("Address: ").append(address != null ? address : "Unknown").append("\n")
                    .append("Phone: ").append(phone != null ? phone : "Unknown").append("\n")
                    .append("AC Model: ").append(acModel != null ? acModel : "Unknown").append("\n")
                    .append("Price: ").append(price != 0 ? price : "Unknown").append("\n")
                    .append("Description: ").append(description != null ? description : "No description")
                    .append("\n\n");

            java.time.LocalDate currentPackLocalDate = (currentPackDate != null)
                    ? new java.sql.Date(currentPackDate.getTime()).toLocalDate()
                    : java.time.LocalDate.now();
            java.time.LocalDate nextServiceLocalDate = (nextServiceDate != null)
                    ? new java.sql.Date(nextServiceDate.getTime()).toLocalDate()
                    : null;

            sb.append("Remaining Services: ").append(remainingServices != 0 ? remainingServices : "No Services Left.")
                    .append("\n");

            java.time.LocalDate today = java.time.LocalDate.now();
            sb.append("Remaining Service Time: ")
                    .append(com.acshop.util.DateUtil.getTimePeriodBetweenDates(today, currentPackLocalDate)).append("\n");

            if (remainingServices != 0 && nextServiceLocalDate != null) {
                sb.append("Next Service Date: ")
                        .append(sdf.format(java.sql.Date.valueOf(nextServiceLocalDate)))
                        .append("\n");
            } else if (remainingServices == 0) {
                 sb.append("Next Service Date: No more services remaining.\n");
            }
        } catch (Exception e) {
            sb.append("Error: ").append(e.getMessage()).append("\n");
        }

        return sb.toString();
    }
}
