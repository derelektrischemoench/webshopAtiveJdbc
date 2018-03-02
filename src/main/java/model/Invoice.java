package model;

public class Invoice {
    private int uid;
    private double price;
    private double tax;
    private Customer customer; // For address
    private Order order;

    public Invoice(int uid, double price, double tax, Customer customer, Order order) {
        this.uid = uid;
        this.price = price;
        this.tax = tax;
        this.customer = customer;
        this.order = order;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "uid=" + uid +
                ", price=" + price +
                ", tax=" + tax +
                ", customer=" + customer +
                ", order=" + order +
                '}';
    }
}
