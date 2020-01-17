package com.waiway.clientapp;

public class MovieModel {
String movieName;
String movieImgLink;
String movieVideoLink;
String movieCategories;
String movieSeries;

    public MovieModel(String movieName, String movieImgLink, String movieVideoLink, String movieCategories, String movieSeries) {
        this.movieName = movieName;
        this.movieImgLink = movieImgLink;
        this.movieVideoLink = movieVideoLink;
        this.movieCategories = movieCategories;
        this.movieSeries = movieSeries;
    }

    public MovieModel() {
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieImgLink() {
        return movieImgLink;
    }

    public void setMovieImgLink(String movieImgLink) {
        this.movieImgLink = movieImgLink;
    }

    public String getMovieVideoLink() {
        return movieVideoLink;
    }

    public void setMovieVideoLink(String movieVideoLink) {
        this.movieVideoLink = movieVideoLink;
    }

    public String getMovieCategories() {
        return movieCategories;
    }

    public void setMovieCategories(String movieCategories) {
        this.movieCategories = movieCategories;
    }

    public String getMovieSeries() {
        return movieSeries;
    }

    public void setMovieSeries(String movieSeries) {
        this.movieSeries = movieSeries;
    }
}
