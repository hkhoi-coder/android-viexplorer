package hkhoi.viexplorer.other;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import hkhoi.viexplorer.R;

/**
 * Created by hkhoi on 8/2/15.
 */
public abstract class Constant {
    public static final String CATEGORY = "category";
    public static final String REGION_ID = "regionId";
    public static final String DB_NAME = "places.db3";
    public static final String HOTEL = "hotel";
    public static final String RESTAURANT = "restaurant";
    public static final String SHOP = "shop";
    public static final String PARK = "park";
    public static final String THEATER = "theater";
    public static final String MUSEUM = "museum";
    public static final String HCMC = "hcmc";
    public static final String HANOI = "hanoi";
    public static final String PLACE_ID = "place_id";
    public static final String GEO_LOCS = "geo_locs";
    public static final String DONG_NAI = "dong_nai";
    public static final String HAI_PHONG = "hai_phong";
    public static final String QUANG_TRI = "quang_tri";

    private static ArrayList<Integer> hanoiCovers = null;
    private static ArrayList<Integer> hcmcCovers = null;

    private static String regionId;
    private static String category;
    private static Context context;
    private static String placeId;
    private static String currency = "$";

    public static void setRegionId(String str) {
        regionId = str;
    }

    public static String getRegionId() {
        return regionId;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Constant.context = context;
    }

    public static String getCategory() {
        return category;
    }

    public static void setCategory(String category) {
        Constant.category = category;
    }

    public static String getPlaceId() {
        return placeId;
    }

    public static void setPlaceId(String placeId) {
        Constant.placeId = placeId;
    }

    public static ArrayList<Integer> getIntroCover(String regionId) {
        switch (regionId) {
            case HCMC:
                if (hcmcCovers == null) {
                    hcmcCovers = new ArrayList<>();
                    hcmcCovers.add(R.drawable.pagerview_hcmc0);
                    hcmcCovers.add(R.drawable.pagerview_hcmc1);
                    hcmcCovers.add(R.drawable.pagerview_hcmc2);
                    hcmcCovers.add(R.drawable.pagerview_hcmc3);
                }
                return hcmcCovers;
            case HANOI:
                if (hanoiCovers == null) {
                    hanoiCovers = new ArrayList<>();
                    hanoiCovers.add(R.drawable.pagerview_hanoi0);
                    hanoiCovers.add(R.drawable.pagerview_hanoi1);
                    hanoiCovers.add(R.drawable.pagerview_hanoi2);
                    hanoiCovers.add(R.drawable.pagerview_hanoi3);
                }
                return hanoiCovers;
            default:
                Log.d("debug", "ERROR: 0053");
                return null;
        }
    }

    public static void setCurrency(String currency) {
        Constant.currency = currency;
    }

    public static String getCurrency() {
        return currency;
    }
}
