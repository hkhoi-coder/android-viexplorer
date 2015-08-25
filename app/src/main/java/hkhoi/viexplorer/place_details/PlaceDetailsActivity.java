package hkhoi.viexplorer.place_details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import hkhoi.viexplorer.R;
import hkhoi.viexplorer.adapters.FragmentPageAdapter;
import hkhoi.viexplorer.other.Constant;
import hkhoi.viexplorer.other.ShowOnMap;

@SuppressWarnings("deprecation")
public class PlaceDetailsActivity extends AppCompatActivity implements ActionBar.TabListener{
    private ActionBar actionbar;
    private ViewPager viewpager;
    private FragmentPageAdapter ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);
        viewpager = (ViewPager) findViewById(R.id.vp_place_details);
        ft = new FragmentPageAdapter(getSupportFragmentManager());

        actionbar = getSupportActionBar();
        viewpager.setAdapter(ft);
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionbar.addTab(actionbar.newTab().setText(getResources().getString(R.string.tab_info)).setTabListener(this));
        actionbar.addTab(actionbar.newTab().setText(getResources().getString(R.string.tab_details)).setTabListener(this));
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                actionbar.setSelectedNavigationItem(arg0);

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        viewpager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_place_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.mnit_place_details_showmap) {
            ArrayList<String> places = new ArrayList<>();
            places.add(Constant.getPlaceId());
            ShowOnMap.Go(this, places);
        } else if (id == R.id.mnit_place_details_share) {
            share();
        }

        return super.onOptionsItemSelected(item);
    }

    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String toShare = "This function is not yet completely implemented, please wait!!";
        intent.putExtra(Intent.EXTRA_TEXT, toShare);
        startActivity(Intent.createChooser(intent, "Let the world know!"));
    }
}
