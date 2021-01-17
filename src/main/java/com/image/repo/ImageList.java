package com.image.repo;

import java.util.HashMap;
import java.util.ArrayList;

public class ImageList {
    private int size;
    private int capacity;
    private ImageNode head;
    private ImageNode tail;
    public HashMap<String, ImageNode> imageMap;
    public AutoComplete search;

    //add node to data strucuture
    private void add(ImageNode currentImage) {
        //check if its large enough to upload
        if (currentImage.data.size <= this.capacity && currentImage.data.size > 0) {
            //nodes are always added to the front of list since most recently used
            //point currentImage node 
            currentImage.prev = this.head;
            currentImage.next = this.head.next;
            //head pointers -> slide the head.next behind new node 
            ImageNode tempNext = head.next;
            tempNext.prev = currentImage;
            head.next = currentImage;
            //increase storage size
            this.size += currentImage.data.size;
            //insert into imageMap
            this.imageMap.put(currentImage.data.name, currentImage);
            this.search.insertion(currentImage.data.name);
            if (this.size > this.capacity) {
                this.removeLeastRecent();
            }
        }
    }

    //get and return node from name
    private ImageNode getImage(String name) {
        ImageNode currentImageNode = (this.imageMap.containsKey(name)) ? this.imageMap.get(name) : null;
        //error handling
        if (currentImageNode == null) return currentImageNode;
        //move to head since it was just accessed
        this.updateMostRecent(currentImageNode);
        return currentImageNode;
    }

    public void uploadImage(String name, int size, String url) {
        ImageNode uploadNode = this.imageMap.get(name);
        if (uploadNode == null) {
            ImageNode newImageNode = new ImageNode(new Image(name, size, url));
            this.add(newImageNode);
        } else {
            //image exists 
            updateImage(name, size, url);
        }
    }

    private void updateImage(String name, int size, String url) {
        //update values in ImageNode
        //and move this to the front of the 
        ImageNode currentUpdate = this.getImage(name);
        this.size += (size - currentUpdate.data.size);
        currentUpdate.data.name = name;
        currentUpdate.data.size = size;
        currentUpdate.data.imageURL = url;
        if (this.size > this.capacity) {
            this.removeLeastRecent();
        }
    }

    //delete specific node from list
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

    //move specific node to head of list 
    private void updateMostRecent(ImageNode currentImage) {
        //move curentImage node to beginning since it was just accessed
        this.delete(currentImage);
        this.add(currentImage);
    }

    //remove nodes when space does not suffice
    private void removeLeastRecent() {
        while (this.size > this.capacity) {
            ImageNode currentImage = tail.prev;
            delete(currentImage);
        }
    }

    public ArrayList<String> autoCompleteSearch(String prefix) {
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
