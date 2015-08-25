package hkhoi.viexplorer.region_details;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import hkhoi.viexplorer.R;
import hkhoi.viexplorer.other.Constant;
import hkhoi.viexplorer.other.DBUtilities;
import hkhoi.viexplorer.other.ShowOnMap;

public class RegionDetailsActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private FragmentManager fragManager;
    private FragmentTransaction fragmentTransaction;
    private ArrayList<Integer> imageIds;
    private ProgressDialog dialog;
    private Context context;

    private IntroFragment introFragment;
    private PlaceListFragment placeListHotel;
    private PlaceListFragment placeListRestaurant;
    private PlaceListFragment placeListShop;
    private PlaceListFragment placeListPark;
    private PlaceListFragment placeListTheater;
    private PlaceListFragment placeListMuseum;

    // Todo: ImagePagerAdapter


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.region_details_activity);

        context = this;

        introFragment = new IntroFragment();
        placeListHotel = new PlaceListFragment();
        placeListMuseum = new PlaceListFragment();
        placeListPark = new PlaceListFragment();
        placeListRestaurant = new PlaceListFragment();
        placeListShop = new PlaceListFragment();
        placeListTheater = new PlaceListFragment();

        imageIds = new ArrayList<>();

        fragManager = getSupportFragmentManager();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigateTo(introFragment);

        Constant.setContext(this);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {
                    case R.id.home:
                        navigateTo(introFragment);
                        return true;
                    case R.id.hotel:
                        Constant.setCategory(Constant.HOTEL);
                        navigateTo(placeListHotel);
                        return true;
                    case R.id.restaurant:
                        Constant.setCategory(Constant.RESTAURANT);
                        navigateTo(placeListRestaurant);
                        return true;
                    case R.id.shop:
                        Constant.setCategory(Constant.SHOP);
                        navigateTo(placeListShop);
                        return true;
                    case R.id.park:
                        Constant.setCategory(Constant.PARK);
                        navigateTo(placeListPark);
                        return true;
                    case R.id.theater:
                        Constant.setCategory(Constant.THEATER);
                        navigateTo(placeListTheater);
                        return true;
                    case R.id.museum:
                        Constant.setCategory(Constant.MUSEUM);
                        navigateTo(placeListMuseum);
                        return true;
                    case R.id.overview:
                        new BackgroundRetriever().execute(Constant.getRegionId());
                        return true;
                    default:
                        return false;
                }
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();


    }

    private void navigateTo(android.support.v4.app.Fragment fragment) {
        fragmentTransaction = fragManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_region_details, menu);
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
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private class BackgroundRetriever extends AsyncTask<String, Void, ArrayList<String>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            dialog.setMessage(context.getResources().getString(R.string.mg_wait));
            dialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);
            dialog.dismiss();
            ShowOnMap.Go(context, strings);
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            DBUtilities dbu = new DBUtilities(context, Constant.DB_NAME);
            ArrayList<String> toReturn = null;
            try {
                dbu.openDatabase();
                toReturn = dbu.getLocationFromRegion(params[0]);
                dbu.closeDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return toReturn;
        }
    }
}
