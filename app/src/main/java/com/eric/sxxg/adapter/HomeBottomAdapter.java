package com.eric.sxxg.adapter;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.eric.sxxg.R;

import java.util.List;

/**
 * Created by Eric on 2018/4/18.
 */

public class HomeBottomAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HomeBottomAdapter(List<String> data) {
        super(R.layout.item_home_bottom, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tv = helper.getView(R.id.tv_item_home);
        tv.setText(item);
    }
}