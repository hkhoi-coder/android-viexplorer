package hkhoi.viexplorer.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import hkhoi.viexplorer.other.Constant;
import hkhoi.viexplorer.place_details.PlaceDetailsActivity;
import hkhoi.viexplorer.region_details.PlaceThumbnail;
import hkhoi.viexplorer.R;

/**
 * Created by hkhoi on 8/3/15.
 */
public class PlaceThumbnailAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<PlaceThumbnail> thumbnails;

    public PlaceThumbnailAdapter(Context context, ArrayList<PlaceThumbnail> thumbnails) {
        this.context = context;
        this.thumbnails = thumbnails;
    }

    @Override
    public int getCount() {
        return thumbnails.size();
    }

    @Override
    public Object getItem(int position) {
        return thumbnails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.place_list_thumbnail, null);

        TextView name = (TextView) convertView.findViewById(R.id.place_thumbnail_name);
        RatingBar rate = (RatingBar) convertView.findViewById(R.id.place_thumbnail_rating);
        ImageButton favorite = (ImageButton) convertView.findViewById(R.id.place_thumbnail_favorite);

        final PlaceThumbnail current = thumbnails.get(position);

        name.setText(current.getName());
        rate.setRating(current.getRating());
        if (current.isFavorite()) {
            favorite.setImageResource(R.drawable.ic_hearted);
        } else {
            favorite.setImageResource(R.drawable.ic_heart);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlaceDetailsActivity.class);
                Constant.setPlaceId(current.getPlace_id());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
