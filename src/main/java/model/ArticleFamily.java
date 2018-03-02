package model;

import java.util.ArrayList;

public class ArticleFamily {
    private String name;
    private ArrayList<ArticleGroup> articleGroups;

    public ArticleFamily(String name, ArrayList<ArticleGroup> articleGroups) {
        this.name = name;
        this.articleGroups = articleGroups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ArticleGroup> getArticleGroups() {
        return articleGroups;
    }

    public void setArticleGroups(ArrayList<ArticleGroup> articleGroups) {
        this.articleGroups = articleGroups;
    }

    public void addArticleGroup(ArticleGroup articleGroup) {
        this.articleGroups.add(articleGroup);
    }
}
