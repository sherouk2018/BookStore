package csed.edu.alexu.eg.virtualbookshelf.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.services.books.model.Volume;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import csed.edu.alexu.eg.virtualbookshelf.R;

public class BookListAdapter extends ArrayAdapter {
    private static final String SEPARATOR = " , ";

    ArrayList<Volume> volumes;
    Context context;

    public BookListAdapter(Context context, int resource, ArrayList<Volume> volumes) {
        super(context, resource);
        this.context = context;
        this.volumes = volumes;

    }

    @Override
    public int getCount() {
        return volumes.size();
    }

    @Nullable
    @Override
    public Volume getItem(int position) {
        return volumes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public ArrayList<Volume> getVolumes() {
        return volumes;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listView;

        if (convertView == null) {
            Volume volume = volumes.get(position);
            listView = inflater.inflate(R.layout.books_list_item, null);

            // image
            ImageView imageView = (ImageView) listView.findViewById(R.id.book_image);
            Volume.VolumeInfo.ImageLinks imageLinks = volume.getVolumeInfo().getImageLinks();
            if (imageLinks != null) {
                Picasso.with(this.context).load(imageLinks.getSmallThumbnail()).into(imageView);
            }
            // title
            TextView title_tv = (TextView) listView.findViewById(R.id.book_title);
            title_tv.setText(volume.getVolumeInfo().getTitle());

            // authors
            TextView authorsTxt = listView.findViewById(R.id.authors);
            List<String> authors = volume.getVolumeInfo().getAuthors();

            if (authors != null && !authors.isEmpty()) {
                StringBuilder authorsJoined = new StringBuilder();
                for (String author : authors) {
                    authorsJoined.append(author);
                    authorsJoined.append(SEPARATOR);
                }
                String authorsStr = authorsJoined.toString();
                //Remove last comma
                authorsStr = authorsStr.substring(0, authorsStr.length() - SEPARATOR.length());
                authorsTxt.setText(authorsStr);
            }

           /* // Add to library
            Button addToLibrary = listView.findViewById(R.id.add_to_fav);
            addToLibrary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("BOOK-IN-LIST", "adding book to shelf");
                    Volume addedVolume = getItem(position);
                    EditFactory factory = EditFactory.getInstance();
                    UserUtils utils = factory.getEditFun("AddVolumeToShelf");
                    Log.d("BOOK-IN-LIST", "utils is : "+ utils.getClass().toString());
                    // TODO : ADD TO LIBRARY
                    //utils.execute(shelfID , addedVolume.getId());
                }
            });*/
        } else {
            listView = convertView;
        }
        return listView;
    }
}

