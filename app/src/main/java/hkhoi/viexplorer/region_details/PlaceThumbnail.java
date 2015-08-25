package hkhoi.viexplorer.region_details;

/**
 * Created by hkhoi on 8/3/15.
 */
public class PlaceThumbnail {
    private String place_id;
    private String name;
    private float rating;
    private boolean favorite;

    public PlaceThumbnail(String place_id, String name, float rating, boolean favorite) {
        this.place_id = place_id;
        this.name = name;
        this.rating = rating;
        this.favorite = favorite;
    }

    public String getPlace_id() {
        return place_id;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
