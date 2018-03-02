package model;

public class Article {
    private int artNr;
    private String name;
    private String description;
    private double price;
    private int stock;
    private int leastStock;
    private int purchasePrice;
    private Image image;

    public Article(int artNr, String name, String description, double price, int stock, int leastStock, int purchasePrice, Image image) {
        this.artNr = artNr;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.leastStock = leastStock;
        this.purchasePrice = purchasePrice;
        this.image = image;
    }

    public int getArtNr() {
        return artNr;
    }

    public void setArtNr(int artNr) {
        this.artNr = artNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getLeastStock() {
        return leastStock;
    }

    public void setLeastStock(int leastStock) {
        this.leastStock = leastStock;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Article{" +
                "artNr=" + artNr +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", leastStock=" + leastStock +
                ", purchasePrice=" + purchasePrice +
                ", image=" + image +
                '}';
    }
}
