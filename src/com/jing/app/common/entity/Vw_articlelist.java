package com.jing.app.common.entity;

/**
 * Created by Jing on 2014/11/26.
 */
public class Vw_articlelist {
    private String articleid;
    private String articletitle;
    private String articlesummary;
    private String articledate;

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

    public String getArticlesummary() {
        return articlesummary;
    }

    public void setArticlesummary(String articlesummary) {
        this.articlesummary = articlesummary;
    }

    public String getArticledate() {
        return articledate;
    }

    public void setArticledate(String articledate) {
        this.articledate = articledate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vw_articlelist that = (Vw_articlelist) o;

        if (articlesummary != null ? !articlesummary.equals(that.articlesummary) : that.articlesummary != null)
            return false;
        if (articledate != null ? !articledate.equals(that.articledate) : that.articledate != null) return false;
        if (articleid != null ? !articleid.equals(that.articleid) : that.articleid != null) return false;
        if (articletitle != null ? !articletitle.equals(that.articletitle) : that.articletitle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = articleid != null ? articleid.hashCode() : 0;
        result = 31 * result + (articletitle != null ? articletitle.hashCode() : 0);
        result = 31 * result + (articlesummary != null ? articlesummary.hashCode() : 0);
        result = 31 * result + (articledate != null ? articledate.hashCode() : 0);
        return result;
    }
}
