package com.image.repo;

public class ImageNode {
    ImageNode prev;
    ImageNode next;
    Image data;

    public ImageNode(Image data) {
        this.data = data;
    }
}
