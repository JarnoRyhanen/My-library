package com.choicely.mylibrary.imageGallery;

import io.realm.RealmObject;

public class ImageData extends RealmObject {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
