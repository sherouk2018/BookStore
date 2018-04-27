package csed.edu.alexu.eg.virtualbookshelf.utility;

import android.accounts.Account;
import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.Books.Mylibrary.Bookshelves.Volumes;

import java.security.ProtectionDomain;
import java.util.Collections;

public abstract class UserUtils extends AsyncTask<String, Void, Volumes>{

    protected Books books;
    public UserUtils(Books books) {
       this.books = books;
    }
    @Override
    protected abstract Volumes doInBackground(String... params);
}
