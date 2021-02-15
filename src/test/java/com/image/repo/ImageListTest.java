package com.image.repo;
import static org.junit.Assert.*;
import org.junit.Test;

public class ImageListTest 
{
    @Test
    public void testAddImageToRepo() {
        ImageList imageList = new ImageList(500);
        imageList.uploadImage("normal image", 100, "https://test.com");
        assertTrue(imageList.getImageMap().containsKey("normal image"));
    }

    @Test 
    public void updateImage() {
        ImageList imageList = new ImageList(100);
        imageList.uploadImage("update image", 50, "https://test.com");
        imageList.uploadImage("update image", 75, "https://test.com/update");

        String expectedOutput = "https://test.com/update";
        String actualOutput = imageList.getImageMap().get("update image").data.imageURL;

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testAddImageLargerThanCapacity() {
        ImageList imageList = new ImageList(100);
        imageList.uploadImage("image larger than capacity", 500, "https://test.com");
        assertTrue(!imageList.getImageMap().containsKey("image larger than capacity"));
    }

    @Test 
    public void testAddImageWithNegativeSize() {
        ImageList imageList = new ImageList(100);
        imageList.uploadImage("image size less than 0", -100, "https://test.com");

        assertTrue(!imageList.getImageMap().containsKey("image size less than 0"));
    }

    @Test
    public void testAddImageSizeLargerThanCapacity() {
        ImageList imageList = new ImageList(100);
        imageList.uploadImage("image to be removed", 50, "https://test.com");
        imageList.uploadImage("image input", 25, "https://test.com/imageinput");
        imageList.uploadImage("image input 2", 50, "https://test.com/imageinput2");

        assertTrue(!imageList.getImageMap().containsKey("image to be removed"));
    }

    @Test
    public void deleteExistingImage() {
        ImageList imageList = new ImageList(100);
        imageList.uploadImage("image to be removed", 50, "https://test.com");
        imageList.uploadImage("image input", 25, "https://test.com/imageinput");
        imageList.delete(imageList.getImageMap().get("image to be removed"));
        
        assert(!imageList.getImageMap().containsKey("image to be removed"));
    }
}
