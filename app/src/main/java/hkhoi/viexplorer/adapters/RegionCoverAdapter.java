package hkhoi.viexplorer.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hkhoi.viexplorer.R;
import hkhoi.viexplorer.home.RegionCover;
import hkhoi.viexplorer.region_details.RegionDetailsActivity;
import hkhoi.viexplorer.other.Constant;

/**
 * Created by hkhoi on 8/2/15.
 */
public class RegionCoverAdapter extends BaseAdapter {

    private Context context;
    private static LayoutInflater inflater;
    ArrayList<RegionCover> regions;

    public RegionCoverAdapter(Context context, ArrayList<RegionCover> regions) {
        this.context = context;
        this.regions = regions;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return regions.size();
    }

    @Override
    public Object getItem(int position) {
        return regions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.region_cover_layout, null);
        ImageView coverPhoto = (ImageView) convertView.findViewById(R.id.iv_region_cover);
        TextView name = (TextView) convertView.findViewById(R.id.tv_region_name);
        TextView description = (TextView) convertView.findViewById(R.id.tv_region_description);

        final RegionCover current = regions.get(position);
        
        coverPhoto.setImageResource(current.getBackgroundId());
        name.setText(current.getName());
        description.setText(current.getDescription());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Todo: Redirect from Main to RegionDetail
                Intent intent = new Intent(context, RegionDetailsActivity.class);
                Constant.setRegionId(current.getId());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
