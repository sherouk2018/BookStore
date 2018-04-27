package csed.edu.alexu.eg.virtualbookshelf.models.BookFilter;

import java.util.HashMap;

public class LocationToUserId {

    private static final HashMap<String, String> locationToUserId = new HashMap<>();

    // Todo update the user id to a real one
    static {
        locationToUserId.put("ALF", "1");
        locationToUserId.put("BIBALEX", "2");
    }

    private LocationToUserId() {}

    public static String getUserId(String location) {
        return locationToUserId.get(location);
    }

}
