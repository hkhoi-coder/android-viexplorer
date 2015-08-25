package hkhoi.viexplorer.other;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import hkhoi.viexplorer.map.MapActivity;

/**
 * Created by hkhoi on 8/5/15.
 */
public abstract class ShowOnMap {
    public static void Go(Context context, ArrayList<String> place_ids) {
        Intent intent = new Intent(context, MapActivity.class);
        intent.putStringArrayListExtra(Constant.GEO_LOCS, place_ids);
        context.startActivity(intent);
    }
}
