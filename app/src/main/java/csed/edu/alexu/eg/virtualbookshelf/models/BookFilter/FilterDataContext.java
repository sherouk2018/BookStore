package csed.edu.alexu.eg.virtualbookshelf.models.BookFilter;

import com.google.api.services.books.Books;
import com.google.api.services.books.model.Volumes;

public interface FilterDataContext {

    public Volumes filterData(String query, Books book);
}
