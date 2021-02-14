package com.image.repo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
  * @desc this class will hold methods for functionality of image repository
  * methods include uploadImage(name, size, url), delete(currentImageNode), autoCompleteSearch(prefix)
*/
public class ImageList {
    private int size;
    private int capacity;
    private ImageNode head;
    private ImageNode tail;
    public Map<String, ImageNode> imageMap;
    public AutoComplete search;

    /*
      * @desc inserts ImageNode into data structure
      * @param ImageNode currentImage - image to be inserted
      * @return void */
    private void add(ImageNode currentImage) {
        if (currentImage.data.size <= this.capacity && currentImage.data.size > 0) {
            //nodes are always added to the front of list since most recently used
            currentImage.prev = this.head;
            currentImage.next = this.head.next;
            //head pointers -> slide the head.next behind new node 
            ImageNode tempNext = head.next;
            tempNext.prev = currentImage;
            head.next = currentImage;
            this.size += currentImage.data.size;
            this.imageMap.put(currentImage.data.name, currentImage);
            this.search.insertion(currentImage.data.name);
            //if above capacity, remove least recently used until size < capacity
            if (this.size > this.capacity) {
                this.removeLeastRecent();
            }
        }
    }

    /*
      * @desc get ImageNode from name
      * @param String name - name of ImageNode to be retrieved
      * @return ImageNode - returns ImageNode from name of image */
    public ImageNode getImage(String name) {
        ImageNode currentImageNode = (this.imageMap.containsKey(name)) ? this.imageMap.get(name) : null;
        //error handling
        if (currentImageNode == null) return currentImageNode;
        //move to head since it was just accessed
        this.updateMostRecent(currentImageNode);
        return currentImageNode;
    }

    /*
      * @desc upload image and input name, size and url of image
      * @param String name - name of image
      * @param int size - image size
      * @param String url - url location of image
      * @return void */
    public void uploadImage(String name, int size, String url) {
        ImageNode uploadNode = this.imageMap.get(name);
        if (uploadNode == null) {
            ImageNode newImageNode = new ImageNode(new Image(name, size, url));
            this.add(newImageNode);
        } else {
            updateImage(name, size, url);
        }
    }

    /*
      * @desc update image if image name currently exists
      * @param String name - name of image to be updated
      * @param int size - image size to be updated
      * @param String url - url location of image to be updated
      * @return void */
    private void updateImage(String name, int size, String url) {
        //update values in ImageNode as this becomes most recent
        ImageNode currentUpdate = this.getImage(name);
        this.size += (size - currentUpdate.data.size);
        currentUpdate.data.name = name;
        currentUpdate.data.size = size;
        currentUpdate.data.imageURL = url;
        if (this.size > this.capacity) {
            this.removeLeastRecent();
        }
    }

    /*
      * @desc delete ImageNode from data structure
      * @param ImageNode currentImage - node to be deleted
      * @return void */
    public void delete(ImageNode currentImage) {
        String word = currentImage.data.name;
        if (this.imageMap.containsKey(word)) {
            ImageNode prev = currentImage.prev;
            ImageNode next = currentImage.next;
            prev.next = next;
            next.prev = prev;
            this.size -= currentImage.data.size;
            this.imageMap.remove(word);
            this.search.deleteWord(word);
        }
    }

    /*
      * @desc move specific node to the head of the data structure 
      * @param ImageNode currentImage - node to be updated and moved 
      * @return void */
    private void updateMostRecent(ImageNode currentImage) {
        //move curentImage node to beginning since it was just accessed
        this.delete(currentImage);
        this.add(currentImage);
    }

    /*
      * @desc delete ImageNode when space does not suffice maximum capacity
      * @return void */
    private void removeLeastRecent() {
        while (this.size > this.capacity) {
            ImageNode currentImage = tail.prev;
            delete(currentImage);
        }
    }

    /*
      * @desc get autocomplete search from prefix 
      * @param String prefix - prefix used to get list of autocomplete search
      * @return List of Strings - list of autocomplete search from prefix */
    public List<String> autoCompleteSearch(String prefix) {
        return this.search.autoCompleteSearch(prefix);
    }

    public ImageList(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.search = new AutoComplete();
        //initialize empty head with size 0
        Image head = new Image("head", 0, null);
        this.head = new ImageNode(head);
        //initialize empty tail with size 0
        Image tail = new Image("tail", 0, null);
        this.tail = new ImageNode(tail);

        this.imageMap = new HashMap<>();
        //initially head will point towards tail and vice versa
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
}
