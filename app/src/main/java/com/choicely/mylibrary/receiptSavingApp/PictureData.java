package com.choicely.mylibrary.receiptSavingApp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PictureData extends RealmObject {
    @PrimaryKey
    private int id;

    private String pictureTitle;
    private String pictureDate;
    private String pictureDir;
    private String pictureUri;

    public String getPictureUri() {
        return pictureUri;
    }

    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPictureDir() {
        return pictureDir;
    }

    public void setPictureDir(String pictureDir) {
        this.pictureDir = pictureDir;
    }


    public String getPictureTitle() {
        return pictureTitle;
    }

    public void setPictureTitle(String pictureTitle) {
        this.pictureTitle = pictureTitle;
    }

    public String getPictureDate() {
        return pictureDate;
    }

    public void setPictureDate(String pictureDate) {
        this.pictureDate = pictureDate;
    }

}
