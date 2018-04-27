package csed.edu.alexu.eg.virtualbookshelf.utility;

import android.util.Log;

import com.google.api.services.books.Books;
import csed.edu.alexu.eg.virtualbookshelf.models.UserFunctionality.EditShelf;
import csed.edu.alexu.eg.virtualbookshelf.utility.UserUtils;
import com.google.api.services.books.Books.Mylibrary.Bookshelves.Volumes;
public class RemoveVolumeFromShelf extends UserUtils {
    private final String TAG = RemoveVolumeFromShelf.class.getSimpleName();

    public RemoveVolumeFromShelf(Books books) {
        super(books);
    }

    @Override
    protected Volumes doInBackground(String... params) {
        Log.d(TAG, "Begin removing volume from shelf with shelf id: " + params[0] + "and volume id: " + params[1]);
        //params[0]: ShelfID, params[1]: volumeID
        EditShelf shelf = new EditShelf();
        if(params.length == 1) shelf.clearShelf(params[0], books);
        else shelf.deleteFromShelf(params[0], params[1], books);
        Log.d(TAG, "Finish removing volume from shelf");
        return null;
    }
}
