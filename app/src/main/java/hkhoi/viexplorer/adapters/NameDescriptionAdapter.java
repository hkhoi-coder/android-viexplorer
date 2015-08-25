package hkhoi.viexplorer.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hkhoi.viexplorer.R;
import hkhoi.viexplorer.other.Constant;
import hkhoi.viexplorer.other.NameDescription;

/**
 * Created by hkhoi on 8/4/15.
 */
public class NameDescriptionAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NameDescription> rooms;

    public NameDescriptionAdapter(Context context, ArrayList<NameDescription> rooms) {
        this.context = context;
        this.rooms = rooms;
    }

    @Override
    public int getCount() {
        return rooms.size();
    }

    @Override
    public Object getItem(int position) {
        return rooms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.name_description, null);

        ImageView photo = (ImageView) convertView.findViewById(R.id.iv_hotel_room_photo);
        TextView name = (TextView) convertView.findViewById(R.id.tv_hotel_room_name);
        TextView description = (TextView) convertView.findViewById(R.id.tv_hotel_room_des);
        TextView price = (TextView) convertView.findViewById(R.id.tv_hotel_room_price);

        NameDescription current = rooms.get(position);

        Picasso.with(context).load(current.getUrl()).error(R.drawable.error)
                .placeholder(R.drawable.loading).into(photo);
        name.setText(current.getName());
        description.setText(current.getDescription());
        price.setText(context.getResources().getString(R.string.etc_currency)
                + Constant.getCurrency() + current.getPrice());

        return convertView;
    }
}
