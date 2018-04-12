package entity;

import annotation.FieldToModify;
import annotation.FieldValueFromGetterMethod;
import annotation.InsertField;
import annotation.SearchField;
import annotation.UpdateField;

public class Customer {
    private int id;
    @InsertField
    private String name;
    @InsertField
    private String phone;
    @FieldToModify
    @InsertField
    @SearchField
    private String customerNumber;
    private int balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @FieldValueFromGetterMethod
    public String getName() {
        return name;
    }

    @UpdateField
    public void setName(String name) {
        this.name = name;
    }

    @FieldValueFromGetterMethod
    public String getPhone() {
        return phone;
    }

    @UpdateField
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @SearchField
    @FieldValueFromGetterMethod
    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getBalance() {
        return balance;
    }

    @UpdateField
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Customer(String name, String phone, String customerNumber, int balance) {
        this.name = name;
        this.phone = phone;
        this.customerNumber = customerNumber;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Customer{" + "name=" + name + ", phone=" + phone + ", customerNumber=" + customerNumber + ", balance=" + balance + '}';
    }
}
