package com.choicely.mylibrary.imageGallery;

import io.realm.RealmObject;

public class ImageData extends RealmObject {

    private String url;
//    private int id;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
