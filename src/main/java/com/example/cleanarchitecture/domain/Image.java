package com.example.cleanarchitecture.domain;

public class Image {

private Long id;
private String photo;

    public Image() {
    }
    public Image(Long id, String photo) {
        this.id = id;
        this.photo = photo;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
