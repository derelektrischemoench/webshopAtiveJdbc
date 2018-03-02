package model;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private ArrayList<ArticleSelection> orderedArticles;
    private double price;
    private Customer customer;

    public Order(int orderId, ArrayList<ArticleSelection> orderedArticles, double price, Customer customer) {
        this.orderId = orderId;
        this.orderedArticles = orderedArticles;
        this.price = price;
        this.customer = customer;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ArrayList<ArticleSelection> getOrderedArticles() {
        return orderedArticles;
    }

    public void setOrderedArticles(ArrayList<ArticleSelection> orderedArticles) {
        this.orderedArticles = orderedArticles;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderedArticles=" + orderedArticles +
                ", price=" + price +
                ", customer=" + customer +
                '}';
    }
}
