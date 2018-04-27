package csed.edu.alexu.eg.virtualbookshelf.utility;

import android.accounts.Account;
import android.content.Context;
import android.util.Log;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

public class EditFactory  {

    private static final String TAG = EditFactory.class.getSimpleName();
    private static Books books;
    private static EditFactory factory;

    private EditFactory() {
    }

    public static void init (Context myContext, Account myAccount){
        Log.d(TAG, "Begin initializing book object");
        if(factory == null){
            Log.d(TAG, "Begin creating credentials");
            GoogleAccountCredential credential = createGoogleAccountCredentials(myContext, myAccount);
            Log.d(TAG, "Finish creating credentials");
            credential.setSelectedAccount(myAccount);
            Log.d(TAG, "Begin creating books object");
            books = createBooksObject(credential);
            Log.d(TAG, "Finish creating books object");
            factory = new EditFactory();
            Log.d(TAG, "Finish initializing book object");
        }
    }

    private static Books createBooksObject(GoogleAccountCredential credential) {
        return new Books.Builder(new com.google.api.client.http.javanet.NetHttpTransport(),
                JacksonFactory.getDefaultInstance(), null)
                .setApplicationName("VirtualBookShelf")
                .setHttpRequestInitializer(credential)
                .build();
    }

    private static GoogleAccountCredential createGoogleAccountCredentials(Context myContext, Account myAccount) {
        return GoogleAccountCredential.usingOAuth2(myContext,
                Collections.singleton("https://www.googleapis.com/auth/books"));
    }

    public static EditFactory getInstance(){
        Log.d(TAG, "Returning EditFactory instance");
        return factory;
    }

    public UserUtils getEditFun(String className) {
        if (books == null) throw new RuntimeException ("Books have no entity");
        try {
            Log.d(TAG, "Creating an object for class: " + className);
            Constructor c = Class.forName("csed.edu.alexu.eg.virtualbookshelf.utility."+ className)
                    .getConstructor(Books.class);
            UserUtils obj = (UserUtils) c.newInstance(books);
            Log.d(TAG, "Finish creating object: " + obj.getClass().getSimpleName());
            return obj;
        } catch (ClassNotFoundException | NoSuchMethodException
                | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            Log.e(TAG, "Fail to create object in EditFactory");
            throw new RuntimeException("Invalid UserUtils Class");
        }
    }

    public Books getBooks() {
        Log.d(TAG, "Returning books object");
        if (books == null) throw new RuntimeException("Books is not set yet");
        return books;
    }
}
