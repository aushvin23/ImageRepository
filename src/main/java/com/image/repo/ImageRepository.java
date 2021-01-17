package com.image.repo;

import java.util.ArrayList;

public class ImageRepository {
    ImageList imageStorage;

    public Image getImage(String name) {
        return this.imageStorage.imageMap.containsKey(name) ? this.imageStorage.imageMap.get(name).data : null;
    }

    public void uploadImage(String name, int size, String url) {
        this.imageStorage.uploadImage(name, size, url);
    }

    public void deleteImage(String name) {
        if (this.imageStorage.imageMap.containsKey(name)) {
            this.imageStorage.delete(this.imageStorage.imageMap.get(name));
        }
    }

    public ArrayList<String> autoCompleteSearch(String prefix) {
        return this.imageStorage.autoCompleteSearch(prefix);
    }

    public ImageRepository(int capacity) {
        this.imageStorage = new ImageList(capacity);
    }

    public static void main(String[] args) {
        ImageRepository imageRepo = new ImageRepository(500);
        imageRepo.uploadImage("aushvin at the gym", 100, "https://aushvin.com/images/gym");
        imageRepo.uploadImage("aushvin at school", 50, "https://aushvin.com/images/school");
        imageRepo.uploadImage("aushvin at work", 300, "https://aushvin.com/images/work");
        imageRepo.uploadImage("aushvin at the basketball game", 25, "https://aushvin.com/images/basketball_game");
        imageRepo.uploadImage("aushvin at yosemite", 100, "https://aushvin.com/images/yosemite");
        imageRepo.uploadImage("aushvin at big sur", 1000, "https://aushvin.com/images/big_sur");
        imageRepo.uploadImage("aushvin at", 10, "https://aushvin.com/images/aushvin_at");

        System.out.println(imageRepo.imageStorage.autoCompleteSearch("aushvin at"));

    }
}