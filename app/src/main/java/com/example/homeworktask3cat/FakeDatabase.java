package com.example.homeworktask3cat; // <============= CHANGE ME

import com.example.homeworktask3cat.model.Cat;
import com.example.homeworktask3cat.model.Picture;

import java.util.HashMap;
import java.util.ArrayList;

/***
 * A class to simulate some data source existing. Use to practice intents and lists/RecyclerView
 * before going into APIs.
 *
 * Week 6:
 *  Now this acts as a place to store our objects, and make them accessible by an ID.
 *  It no longer contains fake data in there by default. You must populate it by parsing the JSON.
 *
 *
 *      e.g. if I got [ArrayList<Book> booksFromJson] from my Gson parsing:
 *              FakeDatabase.saveBooksToFakeDatabase(booksFromJson);
 *
 *           now my FakeDatabase contains my books from JSON.
 *           And I can now do FakeDatabase.getBookByIsbn(1);
 *
 *
 *
 * Example usage:
 *      Article articleObject1 = FakeDatabase.getArticleById(1);
 *      System.out.println(articleObject1.getTitle());
 *
 * Output:
 *      Diamonds, Warlords and Mercenaries: Russia’s New Playbook in Africa
 */
public class FakeDatabase {
    public static HashMap<String, Cat> cats = new HashMap<>();

    public static HashMap<String, Picture> pictures = new HashMap<>();

    /***
     * Retrieves an Article object using the provided id.
     */
    public static Cat getCatById(String id) {return cats.get(id);}

    public static Picture getPictureById(String id) {return pictures.get(id);}


    // This methods simulates saving the data you get from the API to your local database.
    // This way, we retrieve the whole object for an Article by using its ID.
    // Keep in mind it's not a real database yet.
    public static void saveCatsToFakeDatabase(ArrayList<Cat> catsToSave) {
        for(int i = 0; i < catsToSave.size(); i++) {
            Cat cat = catsToSave.get(i);
            cats.put(cat.getId(),cat);
        }
    }



    public static void savePicturesToFakeDatabase(ArrayList<Picture> picturesToSave) {
        for(int i = 0; i < picturesToSave.size(); i++) {
            Picture picture = picturesToSave.get(i);
            pictures.put(picture.getId(), picture);
        }
    }


}
