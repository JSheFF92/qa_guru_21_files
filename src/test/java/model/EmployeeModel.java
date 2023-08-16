package model;

import model.sub_model.Address;
import model.sub_model.Properties;

import java.util.ArrayList;

public class EmployeeModel {
    private float id;
    private String name;
    private boolean permanent;

    Address address;

    private String street;

    ArrayList<Integer> phoneNumbers = new ArrayList <Integer> ();
    private String role;
    ArrayList <String> cities = new ArrayList <String> ();
    Properties properties;

    // Getter Methods
//мой код
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    //тут мой код заканчивается
    public float getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getPhoneNumbers() {
        return phoneNumbers;
    }

    public ArrayList<String> getCities() {
        return cities;
    }

    public boolean getPermanent() {
        return permanent;
    }

    public Address getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }

    public Properties getProperties() {
        return properties;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public void setAddress(Address addressObject) {
        this.address = addressObject;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setProperties(Properties propertiesObject) {
        this.properties = propertiesObject;
    }
}
