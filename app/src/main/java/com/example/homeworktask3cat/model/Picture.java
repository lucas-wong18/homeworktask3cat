package com.example.homeworktask3cat.model; // <============= CHANGE ME


/***
 * Model class for news articles. This should be 100% familiar to you.
 *
 * Week 6
 *  This class now matches what an Article is represented as in the New York Times Most Viewed API.
 */
public class Picture {

    // We use a long because the number they use for ID is too big for an int
    private String id;
    private String url;


    public Picture(String id, String url) {
        this.id = id;
        this.url = url;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
