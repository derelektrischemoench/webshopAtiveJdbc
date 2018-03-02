package model;

import java.util.ArrayList;

public class Customer {
    private int uid;
    private String surName;
    private String firstName;
    private String gender;
    private String salutation;
    private String street;
    private int houseNr;
    private int zip;
    private String city;
    private String country;
    private int phone;
    private String mail;
    private ArrayList<Order> orders;
    private ShoppingCart shoppingCart;
    private String password;

    public Customer(int uid, String surName, String firstName, String gender, String salutation, String street, int houseNr, int zip, String city, String country, int phone, String mail, ArrayList<Order> orders, ShoppingCart shoppingCart, String password) {
        this.uid = uid;
        this.surName = surName;
        this.firstName = firstName;
        this.gender = gender;
        this.salutation = salutation;
        this.street = street;
        this.houseNr = houseNr;
        this.zip = zip;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.mail = mail;
        this.orders = orders;
        this.shoppingCart = shoppingCart;
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNr() {
        return houseNr;
    }

    public void setHouseNr(int houseNr) {
        this.houseNr = houseNr;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "uid=" + uid +
                ", surName='" + surName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", gender='" + gender + '\'' +
                ", salutation='" + salutation + '\'' +
                ", street='" + street + '\'' +
                ", houseNr=" + houseNr +
                ", zip=" + zip +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phone=" + phone +
                ", mail='" + mail + '\'' +
                ", orders=" + orders +
                ", shoppingCart=" + shoppingCart +
                ", password='" + password + '\'' +
                '}';
    }
}
