package csed.edu.alexu.eg.virtualbookshelf.utility;

import android.util.Log;

import com.google.api.services.books.Books;


import com.google.api.services.books.model.Volume;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import csed.edu.alexu.eg.virtualbookshelf.models.UserFunctionality.EditShelf;
import com.google.api.services.books.Books.Mylibrary.Bookshelves.Volumes;
public class ShowVolumesInBookshelf extends UserUtils {
    private final String TAG = AddVolumeToShelf.class.getSimpleName();
    public ShowVolumesInBookshelf(Books books) {super(books);}

    @Override
    protected Volumes doInBackground(String... params) {
        //params[0] = shelfID
        Log.d(TAG, "Begin showVolume async task");
        EditShelf shelf = new EditShelf();
        Books.Mylibrary.Bookshelves.Volumes
                volumes = shelf.ShowVolumesInBookshelf(books, params[0]);
        Log.d("Soso ", "invistigate volumes ");
        Volumes wrapperVol = null ;
        try {
            for(Volume v : volumes.list(params[0]).execute().getItems()){
                Log.d("Soso ", "From ShowVolumesInShelf VolumeID from new: " +
                        v.getId() + " VolumeName: " + v.getKind());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return volumes;
    }
}
