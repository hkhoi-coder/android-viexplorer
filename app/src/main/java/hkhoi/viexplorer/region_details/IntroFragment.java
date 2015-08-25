package hkhoi.viexplorer.region_details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hkhoi.viexplorer.R;
import hkhoi.viexplorer.adapters.ImagePagerAdapter;
import hkhoi.viexplorer.other.Constant;
import hkhoi.viexplorer.other.Transformer;

/**
 * Created by Admin on 04-06-2015.
 */
public class IntroFragment extends Fragment {

    private TextView description;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_intro, container, false);

        description = (TextView) v.findViewById(R.id.iv_intro_des);
        viewPager = (ViewPager) v.findViewById(R.id.view_pager);

        switch (Constant.getRegionId()) {
            case Constant.HANOI:
                description.setText(getResources().getString(R.string.long_des_hanoi));
                break;
            case Constant.HCMC:
                description.setText(getResources().getString(R.string.long_des_hcmc));
                break;
            default:
                break;
        }

        ImagePagerAdapter adapter = new ImagePagerAdapter(getActivity(), Constant.getIntroCover(Constant.getRegionId()));
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(false, new Transformer(Transformer.TransformType.DEPTH));

        return v;
    }
}
