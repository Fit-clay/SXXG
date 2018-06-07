package com.eric.sxxg.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.eric.sxxg.BaseActivity;
import com.eric.sxxg.R;
import com.eric.sxxg.ui.fragment.CommunityFragment;
import com.eric.sxxg.ui.fragment.HomeFragment;
import com.eric.sxxg.ui.fragment.TimelineFragment;
import com.eric.sxxg.ui.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eric on 2018/4/17.
 */

public class TestActivity extends BaseActivity {
    @BindView(R.id.vp)
    VerticalViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        //viewPager.setPageTransformer(false, new ZoomOutTransformer());
        //viewPager.setPageTransformer(true, new StackTransformer());
        String title = "ContentFragment";
        ArrayList<Fragment> fragmentList=new ArrayList<>();
        fragmentList  .add(new CommunityFragment());
        fragmentList  .add(new HomeFragment());
        fragmentList   .add(new UserFragment());
        fragmentList    .add(new TimelineFragment());
        viewPager.setAdapter(new ViewPageFragmentAdapter(getSupportFragmentManager(),fragmentList,new String[]{"4","3","2","1"}));
        //If you setting other scroll mode, the scrolled fade is shown from either side of display.
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);

    }



}
