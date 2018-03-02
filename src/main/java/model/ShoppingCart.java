package model;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<ArticleSelection> articleSelections;

    public ShoppingCart(ArrayList<ArticleSelection> articleSelections) {
        this.articleSelections = articleSelections;
    }

    public ArrayList<ArticleSelection> getArticleSelections() {
        return articleSelections;
    }

    public void setArticleSelections(ArrayList<ArticleSelection> articleSelections) {
        this.articleSelections = articleSelections;
    }

    public void addArticleSelection(ArticleSelection articleSelection) {
        this.articleSelections.add(articleSelection);
    }
}
