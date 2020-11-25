package com.choicely.mylibrary.receiptSavingApp;

public class PictureData {

    private int id;
    private String pictureTitle;
    private String pictureDate;
    private String pictureDir;

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
