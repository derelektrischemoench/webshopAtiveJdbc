package model;

import java.util.ArrayList;

public class Catalogue {
    private static Catalogue instance;
    private ArrayList<ArticleFamily> articleFamilies;

    private Catalogue() {
        this.articleFamilies = new ArrayList<ArticleFamily>();
    }

    public static Catalogue getInstance(){
        if(instance == null){
            instance = new Catalogue();
        }
        return instance;
    }

    public ArrayList<ArticleFamily> getArticleFamilies() {
        return articleFamilies;
    }

    public void addArticleFamily(ArticleFamily articleFamily) {
        this.articleFamilies.add(articleFamily);
    }
}
