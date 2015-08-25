package hkhoi.viexplorer.other;

/**
 * Created by hkhoi on 8/4/15.
 */
public class Place {
    private String title;
    private String description;
    private String address;
    private String tel;

    public Place(String title, String description, String address, String tel) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.tel = tel;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }
}
