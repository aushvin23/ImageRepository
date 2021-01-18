# ImageRepository

A thread safe, memory cache image repository built to hold images to a certain capacity and includes autocomplete search query.

> This implementation uses a least recently used removal policy

## How to Use

### ImageRepository

ImageRepository class provides four methods as shown below:

- `getImage(String name)` - Gets image data including image name, size and url location from repository for specified name.
- `uploadImage(String name, int size, String url)` - Uploads image to repository for specified data.
- `deleteImage(String name)` - Removes the image for specified name.
- `getAllImages()` - Get all image names from image repository.
- `autoCompleteSearch(String prefix)` - Gets list of autocomplete image suggestions from given prefix

Simple implementation 

```java
ImageRepository imageRepo = new ImageRepository(500);
imageRepo.uploadImage("hotwheelscar", 100, "https://hotwheelscars.com/images/1");
```

Compiles all files to a specified target folder

```
$ javac ./src/main/java/com/image/repo/*.java -d ./target/classes
```

Run the main method with the image repository

```
$ java -Dfile.encoding=UTF-8 -cp ./target/classes com.image.repo.ImageRepository 
```

Expected Output

```
[aushvin at, aushvin at school, aushvin at work, aushvin at yosemite]
```



## Architecture 

### ImageList

ImageList is a fixed size cache implementation that remembers the order in which images are accessed and uploaded. Once the user-defined capacity has been reached, it replaces the least recently used image with a newly inserted one. This is ideal for fast lookup, insertion and deletion time. 

### AutoComplete

Autocomplete suggestions retrieval using trie data structure from given prefix. Each node stores a HashMap and boolean terminal value, where the key represents the current character and the value represents the next node. 

- Each path down the trie represents an entire word. 
- Each node down the trie will hold a boolean value representing the end of the word. Leaf nodes will hold a boolean value of true.   

## Test

```sh
mvn clean test
```

## Support

Java 1.7 or greater.
