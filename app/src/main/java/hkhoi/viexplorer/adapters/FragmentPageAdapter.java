package hkhoi.viexplorer.adapters;

/**
 * Created by hkhoi on 8/4/15.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hkhoi.viexplorer.place_details.PlaceDetailsOverview;
import hkhoi.viexplorer.place_details.MenuListDetails;

public class FragmentPageAdapter extends FragmentPagerAdapter {

    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:
                return new PlaceDetailsOverview();
            case 1:
                return new MenuListDetails();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 2;
    }

}
