package csed.edu.alexu.eg.virtualbookshelf.utility;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.api.services.books.Books;
import com.google.api.services.books.model.Volumes;
import csed.edu.alexu.eg.virtualbookshelf.models.BookFilter.FilterDataContext;

public class FilterData{
    private final String path = "csed.edu.alexu.eg.virtualbookshelf.models.BookFilter.";
    private final String TAG = FilterData.class.getSimpleName();


    public Volumes getVolumesBasedOnFilter(String... params) {
        try {
            Log.d(TAG, "Begin filtering data using params: " + params[0] + " " + params[1]);
            FilterDataContext filter = (FilterDataContext) Class.forName(path + params[0]).newInstance();
            Log.d(TAG, "Returning object from type " + filter.getClass().getSimpleName());
            return filter.filterData(params[1], EditFactory.getInstance().getBooks());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            Log.e(TAG, "Failed to create object");
            throw new RuntimeException("Invalid parameters for filter");
        }
    }
}
