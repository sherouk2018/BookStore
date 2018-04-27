package csed.edu.alexu.eg.virtualbookshelf.models.UserFunctionality;

import android.util.Log;

import com.google.api.services.books.Books;

import java.io.IOException;

public class EditShelf {
    private final String TAG = EditShelf.class.getSimpleName();

    // Todo change method name and refactor the code.
    public void AddVolumeToShelf(String shelfID, String volumeID, Books  books) {
        if (shelfID == null || volumeID == null)
            throw new RuntimeException("Invalid args");
        Books.Mylibrary.Bookshelves.AddVolume bookShelve = null;
        try {
            //params[0]: ShelfID, params[1]: volumeID
            Log.d(TAG, "Before adding to bookshelf" + " shelfID: " + shelfID + " bookId: " + volumeID);
            bookShelve = books.mylibrary().bookshelves().addVolume(shelfID, volumeID);
            bookShelve.execute();
            Log.d(TAG, "After adding to bookshelf");
        } catch (IOException e) {
            Log.e(TAG, "Error in add book volume");
            throw new RuntimeException("Failed to add book to shelf");
        }
    }
    public Books.Mylibrary.Bookshelves.Volumes ShowVolumesInBookshelf(Books  books, String ID) {

        Books.Mylibrary.Bookshelves.Volumes bookVolumes = null;
        try {
            Log.d(TAG, "Before showing bookshelf");
            bookVolumes = books.mylibrary().bookshelves().volumes();
            //bookVolumes.execute();
            Log.d(TAG, "After showing bookshelf");
        } catch (Exception e) {
            Log.e(TAG, "Error in show bookShelf");
            throw new RuntimeException("Failed to show from BookShelf");
        }
        return bookVolumes;
    }
    public void clearShelf (String shelfID, Books books){
        if (shelfID == null)
            throw new RuntimeException("Invalid args");
        try {
            Books.Mylibrary.Bookshelves.ClearVolumes bookShelve = null;
            Log.d(TAG, "Before deleting from bookShelf");
            bookShelve = books.mylibrary().bookshelves().clearVolumes(shelfID);
            bookShelve.execute();
            Log.d(TAG, "After deleting from bookShelf");
        } catch (IOException e) {
            Log.e(TAG, "Error in remove book volume");
            throw new RuntimeException("Failed to clear shelf");
        }

    }
    public void deleteFromShelf (String shelfID, String volumeID, Books books){
        if (shelfID == null || volumeID == null)
            throw new RuntimeException("Invalid args");
        try {
            Books.Mylibrary.Bookshelves.RemoveVolume bookShelve = null;
            Log.d(TAG, "before delete from bookShelf");
            bookShelve = books.mylibrary().bookshelves().removeVolume(shelfID, volumeID);
            bookShelve.execute();
            Log.d(TAG, "after delete from bookShelf");
        } catch (IOException e) {
            Log.e(TAG, "Error in remove book volume");
            throw new RuntimeException("Failed to delete book from shelf");
        }
    }
}
