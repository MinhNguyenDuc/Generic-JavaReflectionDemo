package entity;

import annotation.*;

public class Student {
    private int id;
    @InsertField
    private String name;
    @InsertField
    private String address;
    @InsertField
    private String phone;
    @InsertField
    @FieldToModify
    @SearchField
    private String rollNumber;
    private int javaMark;
    private int webMark;

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
    public String getAddress() {
        return address;
    }

    @UpdateField
    public void setAddress(String address) {
        this.address = address;
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
    public String getRollNumber() {
        return rollNumber;
    }

    @UpdateField
    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public int getJavaMark() {
        return javaMark;
    }

    @UpdateField
    public void setJavaMark(int javaMark) {
        this.javaMark = javaMark;
    }

    public int getWebMark() {
        return webMark;
    }

    @UpdateField
    public void setWebMark(int webMark) {
        this.webMark = webMark;
    }

    public Student(String name, String address, String phone, String rollNumber) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rollNumber = rollNumber;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", address=" + address + ", phone=" + phone + ", rollNumber=" + rollNumber + '}';
    }
}
