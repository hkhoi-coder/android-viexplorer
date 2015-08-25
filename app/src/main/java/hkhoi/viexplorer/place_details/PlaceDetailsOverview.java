package hkhoi.viexplorer.place_details;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import hkhoi.viexplorer.R;
import hkhoi.viexplorer.other.Constant;
import hkhoi.viexplorer.other.DBUtilities;
import hkhoi.viexplorer.other.Place;

public class PlaceDetailsOverview extends Fragment {

    private TextView title;
    private TextView description;
    private TextView address;
    private TextView tel;

    private ProgressDialog dialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view =  inflater.inflate(R.layout.place_overview_fragment, container,false);

        title = (TextView) view.findViewById(R.id.tv_place_overview_title);
        description = (TextView) view.findViewById(R.id.tv_place_overview_des);
        address = (TextView) view.findViewById(R.id.tv_place_overview_address);
        tel = (TextView) view.findViewById(R.id.tv_place_overview_tel);

        new BackgroundQuery().execute();

        return view;
    }

    private class BackgroundQuery extends AsyncTask<Void, Void, Place> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage(getResources().getString(R.string.mg_wait));
            dialog.show();
        }

        @Override
        protected void onPostExecute(Place place) {
            super.onPostExecute(place);
            try {
                title.setText(place.getTitle());
                description.setText(place.getDescription());
                address.setText(place.getAddress());
                tel.setText(place.getTel());
            } catch (NullPointerException e) {
                Toast.makeText(getActivity(), "Nothing here :(", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        }

        @Override
        protected Place doInBackground(Void... params) {
            Place toReturn = null;

            DBUtilities dbu = new DBUtilities(getActivity(), Constant.DB_NAME);
            try {
                dbu.openDatabase();
                toReturn = dbu.getPlace(Constant.getPlaceId());
                dbu.closeDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return toReturn;
        }
    }

}
