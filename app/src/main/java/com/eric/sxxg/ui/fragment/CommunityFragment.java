package com.eric.sxxg.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eric.sxxg.R;
import com.eric.sxxg.test.ViewPageFragmentAdapter;
import com.eric.sxxg.ui.LazyFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Eric on 2018/3/20.
 */

public class CommunityFragment extends LazyFragment {

    @BindView(R.id.tl_community)
    SlidingTabLayout tlCommunity;
    @BindView(R.id.vp_community)
    ViewPager vpCommunity;
    Unbinder unbinder;
    View rootView;
    String[] title={"衣,食,住,行"};
    @Override
    public View onCreateRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView =inflater.inflate(R.layout.fragment_community, container, false);
                unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void doHandler(Bundle bundle) {
        super.doHandler(bundle);
//        vpCommunity.

       /* ArrayList<Fragment> fragmentList=new ArrayList<>();
        fragmentList  .add(new HomeFragment());
        fragmentList   .add(new UserFragment());
        fragmentList    .add(new TimelineFragment());
        vpCommunity.setAdapter(new ViewPageFragmentAdapter(getActivity().getSupportFragmentManager(),fragmentList,new String[]{"4","2","1"}));*/
        //If you setting other scroll mode, the scrolled fade is shown from either side of display.

    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
