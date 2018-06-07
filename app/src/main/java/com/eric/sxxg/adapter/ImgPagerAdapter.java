package com.eric.sxxg.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.eric.sxxg.R;

import java.util.List;

/**
 * Created by Eric on 2018/4/11.
 */

public class ImgPagerAdapter extends PagerAdapter {
    private LayoutInflater mInflater;
    private List<String> mList;
    private Context mContext;

    public ImgPagerAdapter(Context context, List<String> mList) {
        mInflater = LayoutInflater.from(context);
        this.mList = mList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        //轮播设置最大值
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        //判断两页是否相同，google要求这样写
        return view == object;
    }

    //加载视图对象，不要弄成View container的函数，这个已经是过期的了
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view= (View) mInflater.inflate(R.layout.item_img,container,false);
        ImageView imageView=view.findViewById(R.id.iv);
        Glide.with(mContext).load(mList.get(position % mList.size()))
                .into(imageView);
        container.addView(view);
        return view;
    }

    //销毁一个视图
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

//    String[] titles = {"第一页", "第二页", "第三页", "第四页", "第五页", "第六页"};
//
//    //给定系统的指示器的时候，需要重写getPageTitle方法
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return titles[position % 6];
//    }
}
