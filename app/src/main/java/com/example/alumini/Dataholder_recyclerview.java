package com.example.alumini;

public class Dataholder_recyclerview {

    int imgid;
    String text;

    public Dataholder_recyclerview(int imgid, String text) {
        this.imgid = imgid;
        this.text = text;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
