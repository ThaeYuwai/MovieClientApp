package com.waiway.clientapp;

public class CategoryModel {
    String categoName;

    public CategoryModel(String categoName) {
        this.categoName = categoName;
    }

    public CategoryModel() {
    }

    public String getCategoName() {
        return categoName;
    }

    public void setCategoName(String categoName) {
        this.categoName = categoName;
    }
}
