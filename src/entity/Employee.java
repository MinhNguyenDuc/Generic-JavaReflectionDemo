package entity;

import annotation.*;

public class Employee {
    private int id;
    @InsertField
    private int age;
    @InsertField
    private String name;
    @InsertField
    private String address;
    @InsertField
    @FieldToModify
    @SearchField
    private String rollNumber;
    private String salary;
    private String absentDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @FieldValueFromGetterMethod
    public int getAge() {
        return age;
    }

    @UpdateField
    public void setAge(int age) {
        this.age = age;
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
    
    @SearchField
    @FieldValueFromGetterMethod
    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAbsentDay() {
        return absentDay;
    }

    public void setAbsentDay(String absentDay) {
        this.absentDay = absentDay;
    }

    public Employee(int age, String name, String address, String rollNumber) {
        this.age = age;
        this.name = name;
        this.address = address;
        this.rollNumber = rollNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rollNumber='" + rollNumber + '\'' +
                ", absentDay='" + absentDay + '\'' +
                '}';
    }
}
