package com.example.finalproject;

public class CarbonDioxideModel {
    private String Title;
    private String PubDate;
    private String Thumbnail;
    private String URL;
    public String guid;

    public CarbonDioxideModel(String title, String pubDate, String thumbnail,String url,String guid) {
        Title = title;
        PubDate = pubDate;
        Thumbnail = thumbnail;
        URL = url;
        this.guid = guid;
    }
    public CarbonDioxideModel() {

    }
    public String getTitle() {
        return Title;
    }

    public String getPubDate() {
        return PubDate;
    }

    public String getThumbnail() {
        return Thumbnail;
    }
    public String getURL() {
        return URL;
    }
    public String getGuid() {
        return guid;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setPubDate(String pubDate) {
        PubDate = pubDate;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
