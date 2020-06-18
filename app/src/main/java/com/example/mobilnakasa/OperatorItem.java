package com.example.mobilnakasa;

public class OperatorItem {
    private int imgResource;
    private String name;
    private String role;

    public OperatorItem(int imgResource, String name, String role) {
        this.imgResource = imgResource;
        this.name = name;
        this.role = role;
    }

    public void test(String text){
        this.name = text;
    }

    public int getImgResource() {
        return imgResource;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
