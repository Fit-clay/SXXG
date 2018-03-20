package com.eric.sxxg.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eric.sxxg.R;
import com.eric.sxxg.ui.LazyFragment;

/**
 * Created by Eric on 2018/3/20.
 */

public class CommunityFragment extends LazyFragment {

    @Override
    public View onCreateRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_community,container,false);
    }
}
