package model;

public class ArticleSelection {
    private Article article;
    private int amount;

    public ArticleSelection(Article article, int amount) {
        this.article = article;
        this.amount = amount;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ArticleSelection{" +
                "article=" + article +
                ", amount=" + amount +
                '}';
    }
}
