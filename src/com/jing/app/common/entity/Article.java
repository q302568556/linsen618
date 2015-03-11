package com.jing.app.common.entity;

import java.util.Date;

/**
 * Created by Jing on 2014/11/24.
 */
public class Article {
    private String articleid;
    private String articletitle;
    private String articlecontent;
    private Date articledate;

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    public String getArticletitle() {
        return articletitle;
    }

    public void setArticletitle(String articletitle) {
        this.articletitle = articletitle;
    }

    public String getArticlecontent() {
        return articlecontent;
    }

    public void setArticlecontent(String articlecontent) {
        this.articlecontent = articlecontent;
    }

    public Date getArticledate() {
        return articledate;
    }

    public void setArticledate(Date articledate) {
        this.articledate = articledate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (articlecontent != null ? !articlecontent.equals(article.articlecontent) : article.articlecontent != null)
            return false;
        if (articledate != null ? !articledate.equals(article.articledate) : article.articledate != null) return false;
        if (articleid != null ? !articleid.equals(article.articleid) : article.articleid != null) return false;
        if (articletitle != null ? !articletitle.equals(article.articletitle) : article.articletitle != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = articleid != null ? articleid.hashCode() : 0;
        result = 31 * result + (articletitle != null ? articletitle.hashCode() : 0);
        result = 31 * result + (articlecontent != null ? articlecontent.hashCode() : 0);
        result = 31 * result + (articledate != null ? articledate.hashCode() : 0);
        return result;
    }
}
