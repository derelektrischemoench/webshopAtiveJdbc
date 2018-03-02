package model;

import java.util.ArrayList;

public class ArticleGroup extends ArticleFamily {
    private ArrayList<Article> articles;

    public ArticleGroup(String name, ArrayList<ArticleGroup> articleGroups, ArrayList<Article> articles) {
        super(name, articleGroups);
        this.articles = articles;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "ArticleGroup{" +
                "articles=" + articles +
                '}';
    }
}
