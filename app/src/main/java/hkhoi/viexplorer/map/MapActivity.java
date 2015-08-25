package hkhoi.viexplorer.map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.DatabaseUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import hkhoi.viexplorer.R;
import hkhoi.viexplorer.adapters.FragmentPageAdapter;
import hkhoi.viexplorer.other.Constant;
import hkhoi.viexplorer.other.DBUtilities;

public class MapActivity extends Activity{

    private GoogleMap map;
    private MapFragment mapFragment;
    private ArrayList<String> place_ids;
    private ProgressDialog dialog;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        context = this;
        // Receive signals
        place_ids = getIntent().getStringArrayListExtra(Constant.GEO_LOCS);

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        map = mapFragment.getMap();
        map.setMyLocationEnabled(true);

//        DBUtilities dbu = new DBUtilities(this, Constant.DB_NAME);
//        ArrayList<MarkerOptions> options = dbu.getMarkerOptions(place_ids);


//        for (MarkerOptions option : options) {
//            map.addMarker(option);
//        }
        new BackgroundMarkers().execute(place_ids);
    }

    private class BackgroundMarkers extends AsyncTask<ArrayList<String>, Void, ArrayList<MarkerOptions>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            dialog.setMessage(getResources().getString(R.string.mg_wait));
            dialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<MarkerOptions> markerOptions) {
            super.onPostExecute(markerOptions);
            for (MarkerOptions option : markerOptions) {
                map.addMarker(option);
            }
            LatLng position = markerOptions.get(0).getPosition();
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 12));
            dialog.dismiss();
        }

        @Override
        protected ArrayList<MarkerOptions> doInBackground(ArrayList<String>... params) {
            ArrayList<MarkerOptions> toReturn = null;
            DBUtilities dbu = new DBUtilities(context, Constant.DB_NAME);
            try {
                dbu.openDatabase();
                toReturn = dbu.getMarkerOptions(params[0]);
                dbu.closeDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return toReturn;
        }
    }
}
