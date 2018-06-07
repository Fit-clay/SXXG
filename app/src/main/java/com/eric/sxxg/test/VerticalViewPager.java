package com.eric.sxxg.test;

/**
 * Copyright (C) 2015 Kaelaela
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class VerticalViewPager extends ViewPager {
    int  mLastMotionY,mLastMotionX;

    public VerticalViewPager(Context context) {
        this(context, null);
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(false, new DefaultTransformer());
    }

    private MotionEvent swapTouchEvent(MotionEvent event) {
        float width = getWidth();
        float height = getHeight();

        float swappedX = (event.getY() / height) * width;
        float swappedY = (event.getX() / width) * height;

        event.setLocation(swappedX, swappedY);

        return event;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = super.onInterceptTouchEvent(swapTouchEvent(event));
        swapTouchEvent(event);
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapTouchEvent(ev));
    }


/*    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        int y = (int) e.getRawY();
        int x = (int) e.getRawX();
        boolean resume = false;
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 发生down事件时,记录y坐标
                mLastMotionY = y;
                mLastMotionX = x;
                resume = false;
                break;
            case MotionEvent.ACTION_MOVE:
                // deltaY > 0 是向下运动,< 0是向上运动
                int deltaY = y - mLastMotionY;
                int deleaX = x - mLastMotionX;

                if (Math.abs(deleaX) > Math.abs(deltaY)) {
                    resume = false;
                } else {
                    //当前正处于滑动
                    if (isRefreshViewScroll(deltaY)) {
                        resume = true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return resume;
    }*/

}
