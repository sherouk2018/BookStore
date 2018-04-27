package csed.edu.alexu.eg.virtualbookshelf;

import android.util.Log;

import com.google.api.services.books.Books;
import com.google.api.services.books.model.Volumes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import csed.edu.alexu.eg.virtualbookshelf.models.BookFilter.FilterDataByAttribute;
import csed.edu.alexu.eg.virtualbookshelf.models.BookFilter.FilterDataByLocation;
import csed.edu.alexu.eg.virtualbookshelf.models.BookFilter.FilterDataContext;
import csed.edu.alexu.eg.virtualbookshelf.models.UserFunctionality.EditShelf;
import csed.edu.alexu.eg.virtualbookshelf.utility.EditFactory;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Log.class)
public class ExampleUnitTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    Books books;

    @Before
    public void setup() {
        PowerMockito.mockStatic(Log.class);
    }

    @Test(expected = RuntimeException.class)
    public void testFilterDataByLocationInvalidLocation() {
        FilterDataContext filterData = new FilterDataByLocation();
        filterData.filterData("ALF1", books);
    }

    @Test(expected = RuntimeException.class)
    public void testFilterDataByLocationGiveNullValue() {
        FilterDataContext filterData = new FilterDataByLocation();
        filterData.filterData(null, books);
    }

    @Test
    public void testFilterDataByLocation() throws IOException {
        FilterDataContext filterData = new FilterDataByLocation();
        Books books = Mockito.mock(Books.class, Mockito.RETURNS_DEEP_STUBS);
        when(books.bookshelves().volumes().list(anyString(), anyString()).execute()).thenReturn(new Volumes());
        assertNotNull(filterData.filterData("ALF", books));
    }

    @Test
    public void testFilterDataByAttribute() throws IOException {
        FilterDataContext filterData = new FilterDataByAttribute();
        Books books = Mockito.mock(Books.class, Mockito.RETURNS_DEEP_STUBS);
        when(books.volumes().list(anyString()).execute()).thenReturn(new Volumes());
        assertNotNull(filterData.filterData("intitle:Harry", books));
    }

    @Test(expected = RuntimeException.class)
    public void testFilterDataByAttributeGiveNullValue() {
        FilterDataContext filterData = new FilterDataByAttribute();
        filterData.filterData(null, books);
    }

    @Test(expected = RuntimeException.class)
    public void testAddVolumeToBookshelfWithInvalidParameters() {
        EditShelf editShelf = new EditShelf();
        editShelf.AddVolumeToShelf(null, null, books);
    }

    @Test(expected = RuntimeException.class)
    public void testClearVolumeToBookshelfWithInvalidParameters() {
        EditShelf editShelf = new EditShelf();
        editShelf.clearShelf(null, books);
    }

    @Test(expected = RuntimeException.class)
    public void testDeleteVolumeToBookshelfWithInvalidParameters() {
        EditShelf editShelf = new EditShelf();
        editShelf.deleteFromShelf(null, null, books);
    }

    @Test(expected = RuntimeException.class)
    public void testCallingFunctionGetEditFuncWithoutSettingBooks() {
        EditFactory.getInstance().getEditFun("EditShelf");
    }

    @Test(expected = RuntimeException.class)
    public void testCallingFunctionGetBooksWithoutSettingBooks() {
        EditFactory.getInstance().getBooks();
    }

}