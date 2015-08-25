package hkhoi.viexplorer.other;

/**
 * Created by hkhoi on 8/3/15.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import hkhoi.viexplorer.R;
import hkhoi.viexplorer.region_details.PlaceThumbnail;

/**
 * Created by hkhoi on 7/29/15.
 */
public class DBUtilities extends SQLiteOpenHelper {
    private String dbName;
    private String dbPath;
    private Context context;
    private SQLiteDatabase db;


    public DBUtilities(Context context, String dbName) {
        super(context, dbName, null, 1);
        this.context = context;
        this.dbName = dbName;
        dbPath = context.getDatabasePath(dbName).getAbsolutePath();
        db = null;
    }

    public void openDatabase() throws IOException {
        if (!isExisted()) {
            copyDatabase();
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        }
    }

    private void copyDatabase() throws IOException {
        InputStream is = context.getAssets().open(dbName);
        File dir = new File(dbPath);
        dir.getParentFile().mkdirs();
        OutputStream os = new FileOutputStream(dbPath);
        byte[] buf = new byte[1024];
        int byteRead;

        while ((byteRead = is.read(buf)) > 0) {
            os.write(buf, 0, byteRead);
        }
    }

    public void closeDatabase() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean isExisted() {
        try {
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e) {
            // db = null -> db not available
        }
        return db != null;
    }

    /**
     * Custom methods
     */
    public ArrayList<PlaceThumbnail> getThumbnails(String regionId, String category) {
        ArrayList<PlaceThumbnail> toReturn = new ArrayList<>();
        final String SCRIPT = "select * from place_thumbnails where regionId = ? and category = ? order by rating desc";
        final String[] ARGS = {regionId, category};
        try {
            Cursor cc = db.rawQuery(SCRIPT, ARGS);
            cc.moveToPosition(-1);
            while (cc.moveToNext()) {
                // Todo: Build Database helper her
                String place_id = cc.getString(0);
                String name = cc.getString(1);
                float rating = cc.getFloat(2);
                boolean favorite = (cc.getInt(3) == 1);
                toReturn.add(new PlaceThumbnail(place_id, name, rating, favorite));
            }
        } catch (Exception e) {
            Log.d("debug", "ERROR: 0003");
        }

        return toReturn;
    }

    public Place getPlace(String placeId) {
        Place toReturn = null;

        final String SCRIPT = "select pt.name, pd.description, pd.address, pd.tel from place_thumbnails pt join place_details pd on pt.place_id = pd.place_id where pt.place_id = ?";
        final String[] ARGS = {Constant.getPlaceId()};

        try {
            Cursor cc = db.rawQuery(SCRIPT, ARGS);
            if (cc.getCount() == 0) {       // No data or retriever failed
                return null;
            }
            cc.moveToPosition(0);
            String name = cc.getString(0);
            String description = cc.getString(1);
            String address = cc.getString(2);
            String tel = cc.getString(3);
            toReturn = new Place(name, description, address, tel);
        } catch (SQLiteException e) {
            Log.d("debug", "ERROR: 6969");
        }

        return toReturn;
    }

    public ArrayList<NameDescription> getHotelRoom() {
        ArrayList<NameDescription> toReturn = new ArrayList<>();

        final String SCRIPT = "select name, description, price, photo_url from hotel_rooms where place_id = ?";
        final String[] ARGS = {Constant.getPlaceId()};

        try {
            Cursor cc = db.rawQuery(SCRIPT, ARGS);
            cc.moveToPosition(-1);
            while (cc.moveToNext()) {
                String name = cc.getString(0);
                String description = cc.getString(1);
                float price = cc.getFloat(2);
                String photo_url = cc.getString(3);

                toReturn.add(new NameDescription(name, description, price, photo_url));
            }
        } catch (SQLiteException e) {
            Log.d("debug", e.getMessage());
        }

        return toReturn;
    }

    public ArrayList<MarkerOptions> getMarkerOptions(ArrayList<String> place_ids) {
        ArrayList<MarkerOptions> toReturn = new ArrayList<>();

        final String SCRIPT = "select pt.name, geo.lat, geo.lng, pt.category from place_thumbnails pt join geo_locations geo on geo.place_id = pt.place_id where pt.place_id = ?";
        try {
            for (String id : place_ids) {
                String[] args = {id};
                Cursor cc = db.rawQuery(SCRIPT, args);
                cc.moveToPosition(0);

                String name = cc.getString(0);
                double lat = cc.getDouble(1);
                double lng = cc.getDouble(2);
                String category = cc.getString(3);
                int icon = R.drawable.marker_hotel;
                // Todo: Icon for marker

                switch (category) {
                    case Constant.RESTAURANT:
                        icon = R.drawable.marker_restaurant;
                        break;
                    case Constant.SHOP:
                        icon = R.drawable.marker_shop;
                        break;
                    case Constant.PARK:
                        icon = R.drawable.marker_park;
                        break;
                    case Constant.THEATER:
                        icon = R.drawable.mc_theater;
                        break;
                    case Constant.MUSEUM:
                        icon = R.drawable.marker_museum;
                        break;
                }

                toReturn.add(new MarkerOptions()
                .title(name)
                .position(new LatLng(lat, lng))
                .icon(BitmapDescriptorFactory.fromResource(icon)));
            }
        } catch (SQLiteException e) {
            // Catch shit here
        }

        return toReturn;
    }

    public ArrayList<String> getLocationFromRegion(String regionId) {
        ArrayList<String> toReturn = new ArrayList<>();

        final String SCRIPT = "select place_id from place_thumbnails where regionId = ?";
        final String[] ARGS = {Constant.getRegionId()};

        try {
            Cursor cc = db.rawQuery(SCRIPT, ARGS);
            cc.moveToPosition(-1);
            while (cc.moveToNext()) {
                toReturn.add(cc.getString(0));
            }

        } catch (SQLiteException e) {
            // Catch shit here
        }

        return toReturn;
    }
}

