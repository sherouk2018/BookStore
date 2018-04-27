package csed.edu.alexu.eg.virtualbookshelf.models.BookFilter;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.api.services.books.Books;
import com.google.api.services.books.model.Volumes;
import java.io.IOException;
import csed.edu.alexu.eg.virtualbookshelf.utility.Constants;

public class FilterDataByLocation implements FilterDataContext {

    private static final String TAG = FilterDataByLocation.class.getSimpleName();

    @Override
    public Volumes filterData(String location, Books books) {
        Log.d(TAG, "Begin Filtering data by location");
        if (location == null)
            throw new RuntimeException("Invalid arguments");
        Volumes volumes = null;
        try {
            String userId = LocationToUserId.getUserId(location);
            if (userId == null)
                throw new RuntimeException("Location not found");
            Books.Bookshelves.Volumes.List listBooksInst = books.bookshelves().volumes().list(userId, "0");
            listBooksInst.setMaxResults(Constants.MAX_RESULTS);
            volumes = listBooksInst.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Finish Filtering data by location and number of returned volumes = "
                + (volumes == null ? -1 : 1));
        return volumes;
    }
}