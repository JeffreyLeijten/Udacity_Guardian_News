package com.example.android.guardiannews;

import java.util.Date;

/**
 * Created by Jeffrey on 26-6-2017.
 */

public class Article {

    private String title;
    private String section;
    private Date publicationDate;
    private String url;

    public Article(String title, String section, Date publicationDate, String url) {
        this.title = title;
        this.section = section;
        this.publicationDate = publicationDate;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getSection() {
        return section;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public String getUrl() {
        return url;
    }
}
