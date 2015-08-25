package hkhoi.viexplorer.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by hkhoi on 8/2/15.
 */
public class ImagePagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<Integer> imageIds;

    public ImagePagerAdapter(Context context, ArrayList<Integer> imageIds) {
        this.context = context;
        this.imageIds = imageIds;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView image = new ImageView(context);
        image.setImageResource(imageIds.get(position));
        image.setScaleType(ImageView.ScaleType.CENTER);
        image.setAdjustViewBounds(true);
        container.addView(image, 0);
        return image;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

    @Override
    public int getCount() {
        return imageIds.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }
}
