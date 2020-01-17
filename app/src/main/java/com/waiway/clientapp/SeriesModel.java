package com.waiway.clientapp;

public class SeriesModel  {
    String seriesName;
    String seriesImageLink;
    String SeriesCategory;
    String seriesVideo;

    public SeriesModel(String seriesName, String seriesImageLink, String seriesCategory, String seriesVideo) {
        this.seriesName = seriesName;
        this.seriesImageLink = seriesImageLink;
        SeriesCategory = seriesCategory;
        this.seriesVideo = seriesVideo;
    }

    public SeriesModel() {
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesImageLink() {
        return seriesImageLink;
    }

    public void setSeriesImageLink(String seriesImageLink) {
        this.seriesImageLink = seriesImageLink;
    }

    public String getSeriesCategory() {
        return SeriesCategory;
    }

    public void setSeriesCategory(String seriesCategory) {
        SeriesCategory = seriesCategory;
    }

    public String getSeriesVideo() {
        return seriesVideo;
    }

    public void setSeriesVideo(String seriesVideo) {
        this.seriesVideo = seriesVideo;
    }
}
