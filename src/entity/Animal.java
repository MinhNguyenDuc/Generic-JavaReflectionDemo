package entity;

import annotation.*;

public class Animal {
    private int id;
    @InsertField
    private String name;
    @InsertField
    private int age;
    @InsertField
    private String species;
    private int price;
    @InsertField
    @FieldToModify
    @SearchField
    private String animalNumber;

    @FieldValueFromGetterMethod
    public String getName() {
        return name;
    }

    @UpdateField
    public void setName(String name) {
        this.name = name;
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
    public String getSpecies() {
        return species;
    }

    @UpdateField
    public void setSpecies(String species) {
        this.species = species;
    }

    public int getPrice() {
        return price;
    }

    @UpdateField
    public void setPrice(int price) {
        this.price = price;
    }

    @FieldValueFromGetterMethod
    public String getAnimalNumber() {
        return animalNumber;
    }

    @UpdateField
    public void setAnimalNumber(String animalNumber) {
        this.animalNumber = animalNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Animal(String name, int age, String species, String animalNumber) {
        this.name = name;
        this.age = age;
        this.species = species;
        this.animalNumber = animalNumber;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", species='" + species + '\'' +
                ", price=" + price +
                ", animalNumber='" + animalNumber + '\'' +
                '}';
    }
}
