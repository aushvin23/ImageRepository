package com.image.repo;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class ImageRepositoryTest {
    @Test
    public void testUploadImageWithinCapacity() {
        ImageRepository imageRepo = new ImageRepository(500);
        imageRepo.uploadImage("testImage1", 50, "https://images.com/testImage1");
        imageRepo.uploadImage("testImage2", 50, "https://images.com/testImage2");
        ArrayList<String> autoCompleteResults = imageRepo.autoCompleteSearch("t"); 


        assertTrue(imageRepo.imageStorage.imageMap.containsKey("testImage1"));
        assertTrue(imageRepo.imageStorage.imageMap.containsKey("testImage2"));

        assertTrue(imageRepo.imageStorage.search.searchWord("testImage1"));
        assertTrue(imageRepo.imageStorage.search.searchWord("testImage2"));

        assertTrue(autoCompleteResults.contains("testImage1"));
        assertTrue(autoCompleteResults.contains("testImage1"));
    }

    @Test
    public void testUploadImageAboveCapacity() {
        ImageRepository imageRepo = new ImageRepository(500);
        imageRepo.uploadImage("testImage1", 550, "https://images.com/testImage1");
        imageRepo.uploadImage("testImage2", 50, "https://images.com/testImage2");
        ArrayList<String> autoCompleteResults = imageRepo.autoCompleteSearch("t"); 

        assertTrue(!imageRepo.imageStorage.imageMap.containsKey("testImage1"));
        assertTrue(!imageRepo.imageStorage.search.searchWord("testImage1"));
        assertTrue(!autoCompleteResults.contains("testImage1"));
    }

    @Test
    public void testUploadRemoveRemoveLeastRecentlyUsed() {
        ImageRepository imageRepo = new ImageRepository(500);
        imageRepo.uploadImage("testImage1", 350, "https://images.com/testImage1");
        imageRepo.uploadImage("testImage2", 50, "https://images.com/testImage2");
        imageRepo.uploadImage("testImage3", 50, "https://images.com/testImage3");
        imageRepo.uploadImage("testImage4", 100, "https://images.com/testImage4");
        ArrayList<String> autoCompleteResults = imageRepo.autoCompleteSearch("t"); 

        assertTrue(!imageRepo.imageStorage.imageMap.containsKey("testImage1"));
        assertTrue(!imageRepo.imageStorage.search.searchWord("testImage1"));
        assertTrue(!autoCompleteResults.contains("testImage1"));
    }

    @Test
    public void deleteImage() {
        ImageRepository imageRepo = new ImageRepository(500);
        imageRepo.uploadImage("testImage1", 350, "https://images.com/testImage1");
        imageRepo.uploadImage("testImage2", 50, "https://images.com/testImage2");
        imageRepo.uploadImage("testImage3", 50, "https://images.com/testImage3");

        imageRepo.deleteImage("testImage1");
        ArrayList<String> autoCompleteResults = imageRepo.autoCompleteSearch("t"); 

        assertTrue(!imageRepo.imageStorage.imageMap.containsKey("testImage1"));
        assertTrue(!imageRepo.imageStorage.search.searchWord("testImage1"));
        assertTrue(!autoCompleteResults.contains("testImage1"));
    }

    @Test
    public void deleteImageNonExisting() {
        ImageRepository imageRepo = new ImageRepository(500);
        imageRepo.uploadImage("testImage1", 350, "https://images.com/testImage1");
        imageRepo.uploadImage("testImage2", 50, "https://images.com/testImage2");
        imageRepo.uploadImage("testImage3", 50, "https://images.com/testImage3");

        int sizeBeforeInvalidDelete = imageRepo.imageStorage.imageMap.size();
        imageRepo.deleteImage("testImage4");
        ArrayList<String> autoCompleteResults = imageRepo.autoCompleteSearch("t"); 

        assertTrue(imageRepo.imageStorage.imageMap.size() == sizeBeforeInvalidDelete);
        assertTrue(autoCompleteResults.size() == sizeBeforeInvalidDelete);
    }

    @Test 
    public void getImage() {
        ImageRepository imageRepo = new ImageRepository(500);
        imageRepo.uploadImage("testImage1", 350, "https://images.com/testImage1");
        imageRepo.uploadImage("testImage2", 50, "https://images.com/testImage2");
        imageRepo.uploadImage("testImage3", 50, "https://images.com/testImage3");

        Image image = imageRepo.getImage("testImage1");

        assertTrue(image.imageURL.equals("https://images.com/testImage1"));
        assertTrue(image.name.equals("testImage1"));;
        assertTrue(image.size == 350);
    }

    @Test
    public void getNonExistingImage() {
        ImageRepository imageRepo = new ImageRepository(500);
        imageRepo.uploadImage("testImage1", 350, "https://images.com/testImage1");
        imageRepo.uploadImage("testImage2", 50, "https://images.com/testImage2");
        imageRepo.uploadImage("testImage3", 50, "https://images.com/testImage3");

        Image image = imageRepo.getImage("testImage4");

        assertNull(image);
    }

    @Test 
    public void getListOfAllImages() {
        ImageRepository imageRepo = new ImageRepository(500);
        imageRepo.uploadImage("testImage1", 350, "https://images.com/testImage1");
        imageRepo.uploadImage("testImage2", 50, "https://images.com/testImage2");
        imageRepo.uploadImage("testImage3", 50, "https://images.com/testImage3");
        ArrayList<String> list = imageRepo.getAllImages();
        int size = list.size();
        assertTrue(list.contains("testImage1"));
        assertTrue(list.contains("testImage2"));
        assertTrue(list.contains("testImage3"));
        assertEquals(size, 3);

    }


}
