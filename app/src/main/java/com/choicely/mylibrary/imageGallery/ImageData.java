package com.choicely.mylibrary.imageGallery;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ImageData extends RealmObject {

    @PrimaryKey
    private int id;
    private String title;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
