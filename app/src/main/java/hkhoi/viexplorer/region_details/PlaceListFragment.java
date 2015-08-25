package hkhoi.viexplorer.region_details;

import android.app.ProgressDialog;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import hkhoi.viexplorer.R;
import hkhoi.viexplorer.adapters.PlaceThumbnailAdapter;
import hkhoi.viexplorer.other.Constant;
import hkhoi.viexplorer.other.DBUtilities;
import hkhoi.viexplorer.region_details.PlaceThumbnail;

/**
 * Created by Admin on 04-06-2015.
 */
public class PlaceListFragment extends Fragment {

    private PlaceThumbnailAdapter adater;
    private ArrayList<PlaceThumbnail> thumbnails;
    private ListView placeList;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_place_list, container, false);
        placeList = (ListView) v.findViewById(R.id.lv_placelist);

        new BackgroundRetriever().execute();
        return v;
    }

    public void getThumbnailsFromDB() {
        DBUtilities dbu = new DBUtilities(getActivity(), Constant.DB_NAME);
        try {
            dbu.openDatabase();
            thumbnails = dbu.getThumbnails(Constant.getRegionId(), Constant.getCategory());
            dbu.closeDatabase();
        } catch (IOException e) {
            Log.d("debug", "ERROR: 0004");
        } catch (SQLiteException e) {
            Log.d("debug", "ERROR: 005");
        }
    }

    private class BackgroundRetriever extends AsyncTask<Void, Void, ArrayList<PlaceThumbnail>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getResources().getString(R.string.mg_wait));
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<PlaceThumbnail> placeThumbnails) {
            super.onPostExecute(placeThumbnails);
            adater = new PlaceThumbnailAdapter(getActivity(), placeThumbnails);
            placeList.setAdapter(adater);
            progressDialog.dismiss();
        }

        @Override
        protected ArrayList<PlaceThumbnail> doInBackground(Void... params) {
            DBUtilities dbu = new DBUtilities(getActivity(), Constant.DB_NAME);
            ArrayList<PlaceThumbnail> toReturn = new ArrayList<>();
            try {
                dbu.openDatabase();
                toReturn = dbu.getThumbnails(Constant.getRegionId(), Constant.getCategory());
                dbu.closeDatabase();
            } catch (IOException e) {
                Log.d("debug", "ERROR: 0004");
            } catch (SQLiteException e) {
                Log.d("debug", "ERROR: 005");
            }
            return toReturn;
        }
    }
}
