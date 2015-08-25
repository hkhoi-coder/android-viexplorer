package hkhoi.viexplorer.place_details;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import hkhoi.viexplorer.R;
import hkhoi.viexplorer.adapters.NameDescriptionAdapter;
import hkhoi.viexplorer.other.Constant;
import hkhoi.viexplorer.other.DBUtilities;
import hkhoi.viexplorer.other.NameDescription;

public class MenuListDetails extends Fragment {

	private ListView list;
	private ProgressDialog dialog;
	private NameDescriptionAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =  inflater.inflate(R.layout.menu_list_details, container,false);

		list = (ListView) view.findViewById(R.id.lv_details_list);
		new BackgroundLoader().execute();

		return view;
	}

	private class BackgroundLoader extends AsyncTask<Void, Void, ArrayList<NameDescription>> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(getActivity());
			dialog.setMessage(getResources().getString(R.string.mg_wait));
			dialog.show();
		}

		@Override
		protected void onPostExecute(ArrayList<NameDescription> nameDescriptions) {
			super.onPostExecute(nameDescriptions);
			adapter = new NameDescriptionAdapter(getActivity(), nameDescriptions);
			list.setAdapter(adapter);
			dialog.dismiss();
		}

		@Override
		protected ArrayList<NameDescription> doInBackground(Void... params) {
			ArrayList<NameDescription> toReturn = null;
			DBUtilities dbu = new DBUtilities(getActivity(), Constant.DB_NAME);
			try {
				dbu.openDatabase();
				toReturn = dbu.getHotelRoom();
				dbu.closeDatabase();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return toReturn;
		}
	}
}
