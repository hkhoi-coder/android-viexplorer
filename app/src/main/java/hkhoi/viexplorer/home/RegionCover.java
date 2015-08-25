package hkhoi.viexplorer.home;

/**
 * Created by hkhoi on 8/2/15.
 */
public class RegionCover {
    private String name;
    private String description;
    private int backgroundId;
    private String id;

    public RegionCover(String name, String description, String id, int backgroundId) {
        this.name = name;
        this.description = description;
        this.backgroundId = backgroundId;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getBackgroundId() {
        return backgroundId;
    }

    public String getId() {
        return id;
    }
}

