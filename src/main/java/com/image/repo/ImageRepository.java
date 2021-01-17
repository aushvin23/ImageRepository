package com.image.repo;
import java.util.ArrayList;

/*
  * @desc this class will hold methods for functionality of image repository
  * methods include uploadImage(name, size, url), deleteImage(name), autoCompleteSearch(prefix), getImage(name)
*/
public class ImageRepository {
    ImageList imageStorage;

    /*
      * @desc get Image data using name of image from repository
      * @param String name - name of image
      * @return Image - data including name, size and url of image */
    public Image getImage(String name) {
      if (this.imageStorage.imageMap.containsKey(name)) {
        return this.imageStorage.imageMap.get(name).data;
      } else {
        System.err.println("Image cannot be found in repository");
        return null;
      }
    }

    /*
      * @desc upload image to repository
      * @param String name - name of image
      * @param int size - size of image
      * @param String url - location of image url
      * @return void */
    public void uploadImage(String name, int size, String url) {
        this.imageStorage.uploadImage(name, size, url);
    }

    /*
      * @desc delete image from repository using image name
      * @param String name - name of image
      * @return void */
    public void deleteImage(String name) {
        if (this.imageStorage.imageMap.containsKey(name)) {
            this.imageStorage.delete(this.imageStorage.imageMap.get(name));
        } else {
          System.err.println("Image cannot be deleted since it does not exist in repository");
        }
    }

    /*
      * @desc get list of autocomplete search from prefix
      * @param String prefix - prefix used to search from list
      * @return List of autocomplete search */
    public ArrayList<String> autoCompleteSearch(String prefix) {
        return this.imageStorage.autoCompleteSearch(prefix);
    }

    public ImageRepository(int capacity) {
        if (capacity > 0) {
            this.imageStorage = new ImageList(capacity);
        } else {
          System.err.println("Capacity of image repository must be greater than 0 kbs");
        }
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