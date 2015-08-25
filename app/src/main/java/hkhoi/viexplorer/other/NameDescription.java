package hkhoi.viexplorer.other;

/**
 * Created by hkhoi on 8/4/15.
 */
public class NameDescription {
    private String name;
    private String description;
    private float price;
    private String url;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public NameDescription(String name, String description, float price, String url) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.url = url;
    }
}
