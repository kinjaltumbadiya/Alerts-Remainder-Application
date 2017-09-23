package com.example.alerts;

public class RowItem {
    private int imageId;
    private String title;
    private String desc;
     private String path;
     private String id;
    public RowItem(int imageId, String title, String desc ,String path,String id) {
        this.imageId = imageId;
        this.title = title;
        this.desc = desc;
        this.path=path;
        this.id=id;
    }
    
    public String getid() {
        return id;
    }
    public void setid(String id) {
        this.id = id;
    }
    
    
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getpath() {
        return path;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title + "\n" + desc;
    }  
}