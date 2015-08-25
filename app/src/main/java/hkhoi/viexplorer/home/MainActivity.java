package hkhoi.viexplorer.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import hkhoi.viexplorer.R;
import hkhoi.viexplorer.adapters.RegionCoverAdapter;
import hkhoi.viexplorer.other.Constant;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<RegionCover> regions;
    private static ListView regionList;
    private static RegionCoverAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regionList = (ListView) findViewById(R.id.lv_main_regions);

        InitializeRegions();
        InflateListView();
    }

    private void InflateListView() {
        adapter = new RegionCoverAdapter(this, regions);
        regionList.setAdapter(adapter);
    }

    private void InitializeRegions() {
        regions = new ArrayList<>();

        regions.add(new RegionCover(getResources().getString(R.string.name_hanoi),
                getResources().getString(R.string.des_hanoi), Constant.HANOI, R.drawable.hanoi_cover));
        regions.add(new RegionCover(getResources().getString(R.string.name_hcmc),
                getResources().getString(R.string.des_hcmc), Constant.HCMC, R.drawable.hcmc_cover));
        regions.add(new RegionCover(getResources().getString(R.string.name_dongnai),
                getResources().getString(R.string.des_dongnai), Constant.DONG_NAI, R.drawable.dongnai_cover));
        regions.add(new RegionCover(getResources().getString(R.string.name_haiphong),
                getResources().getString(R.string.des_haiphong), Constant.HAI_PHONG, R.drawable.haiphong_cover));
        regions.add(new RegionCover(getResources().getString(R.string.name_quangtri),
                getResources().getString(R.string.des_quangtri), Constant.QUANG_TRI, R.drawable.cover_quangtri));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

