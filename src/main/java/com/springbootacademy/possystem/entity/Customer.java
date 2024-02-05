package com.springbootacademy.possystem.entity;

import javax.persistence.*;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.annotations.TypeDef;
import java.util.ArrayList;


@Entity
@Table(name = "customer")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
public class Customer {

    @Id
    @Column(name = "customer_Id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @Column(name = "customer_name", length = 150, nullable = false)
    private String customerName;

    @Column(name = "customer_address", length = 255)
    private String CustomerAddress;

    @Column(name = "customer_salary")
    private double customerSalary;

    @Type(type = "json")
    @Column(name = "contact_numbers",columnDefinition = "json")
//    @Convert(converter = JsonType.class)
    private ArrayList contactNumbers;

    @Column(name = "nic")
    private String nic;

    @Column(name = "active_state", columnDefinition = "TINYINT default 0")
    private boolean active;

    public Customer() {
    }

    public Customer(int customerId, String customerName, String customerAddress, double customerSalary, ArrayList contactNumber, String nic, boolean active) {
        this.customerId = customerId;
        this.customerName = customerName;
        CustomerAddress = customerAddress;
        this.customerSalary = customerSalary;
        this.contactNumbers = contactNumber;
        this.nic = nic;
        this.active = active;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public double getCustomerSalary() {
        return customerSalary;
    }

    public void setCustomerSalary(double customerSalary) {
        this.customerSalary = customerSalary;
    }

    public ArrayList getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(ArrayList contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}


