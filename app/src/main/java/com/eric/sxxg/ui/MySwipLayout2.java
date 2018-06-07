package com.eric.sxxg.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Scroller;

/**
 * Created by nthforever on 2017/6/9.
 */

public class MySwipLayout2 extends LinearLayout {
    private float initX;
    private float initY;
    private float currX;
    private float currY;
    private int offsetX;
    private Scroller mScroll;
    private int currIndex = 0;
    private int width;
    private int height;
    private int historyOffsetX;
    private int historyOffsetY;
    private float originalX;
    private float originalY;
    private boolean scrolling = false;
    private boolean hasEnd = false;
    private double distant;
    private View targetView;
    private View upView;
    private float mLastx;
    private float mLasy;
    private boolean hasintercept = false;

    private MySwipeLayoutListener listener;

    public MySwipLayout2(Context context) {
        this(context,null);
    }

    public MySwipLayout2(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MySwipLayout2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroll = new Scroller(context);
        setOrientation(VERTICAL);
    }

    public void setUpView(View upView) {
        this.upView = upView;
        Log.i("ppp", "setUpView now");
    }

    public void setTargetView(View view) {
        this.targetView = view;
        Log.i("ppp", "setTargetView now");
    }

    public void setListener(MySwipeLayoutListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
         int childCount = getChildCount();
        // 宽度模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        // 测量宽度
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        // 高度模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // 测量高度
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, widthMode);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, heightMode);
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
      /*  View childAt1 = this.findViewById(R.id.il_header);
        View childAt2 = this.findViewById(R.id.swipe_hide);
        childAt1.layout(0, 0, width, height);
        childAt2.layout(0, height, width, height * 2);
*/
    }

    @Override


    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            return super.dispatchTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        float x = (int) ev.getX();
        float y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                originalX = ev.getX();
                originalY = ev.getY();
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                distant = Math.sqrt((x - originalX) * (x - originalX) + (y - originalY) * (y - originalY));
                if (distant < 5) {
                    intercept = false;
                } else {
                    //避免横屏滑动冲突
                    if (Math.abs(x - originalX) > Math.abs(y - originalY) && Math.abs(x - originalX) >= 10) {
                        intercept = false;
                    } else {
                        //顶部视图滑动


                        if (!childCanScrollUp() && !childCanScrollDown()) {
                            intercept = false;
                        } else if (!childCanScrollUp()) {
                            intercept = true;
                        } else if (!childCanScrollDown()) {
                            intercept = true;
                        } else {
                            if (childCanScrollDown() && currIndex == 1 && (y - originalY) > 0 && !childCanScrollUp()) {
                                intercept = true;
                            } else {

                            }
                        }


                        if (originalY > y) {
                            intercept = false;
                        } else {
                            intercept = !childCanScrollUp();
                        }
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                distant = Math.sqrt((x - originalX) * (x - originalX) + (y - originalY) * (y - originalY));
                intercept = !(distant < 5);
                break;
        }
        mLastx = x;
        mLasy = y;
        return intercept;

    }

    public void reset() {
        int scrollY = getScrollY();
        int dy = 0 - scrollY;
        mScroll.startScroll(0, scrollY, 0, dy, 500);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initX = event.getX();
                initY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                currX = event.getX();
                currY = event.getY();
                offsetX = (int) (currX - initX);

                int offsety = (int) (currY - initY);
                offsety = (int) Math.max(offsety, currY - originalY);
                if (currIndex <= 0 && offsetX > 0) {

                } else if (currIndex >= 1 && offsetX < 0) {

                } else {
//                    scrollTo(historyOffsetX-offsetX,0);
                }
                if (currIndex <= 0 && offsety > 0) {

                } else if (currIndex >= 1 && offsety < 0) {

                } else {
                    scrolling = true;
                    scrollTo(0, historyOffsetY - offsety);
                    if (listener != null) {
                        listener.onPagePreChanged(currIndex);
                    }
                }
                return true;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                float targetIndex;
                if (currIndex == 1) {
                    targetIndex = (scrollY + height * 1 / 10f) / Float.valueOf(height);
                } else {
                    targetIndex = (scrollY + height * 9 / 10f) / Float.valueOf(height);
                }
                currIndex = (int) Math.floor(targetIndex);
                int dx = (currIndex) * width - scrollX;
                int dy = (currIndex) * height - scrollY;
                mScroll.startScroll(0, scrollY, 0, dy, 500);
                invalidate();
                historyOffsetX = currIndex * width;
                historyOffsetY = currIndex * height;
                scrolling = false;
                if (listener != null) {
                    listener.onPageAfterChanged(currIndex);
                }
                break;
        }
        return true;
    }

    private boolean upViewCanScrollUp() {
        if (upView == null) {
            Log.i("uuu", "upView is null");
            return false;
        }
        return ViewCompat.canScrollVertically(upView, -1);
    }

    private boolean upViewCanScrollDown() {
        if (upView == null) {
            Log.i("uuu", "upView is null");
            return true;
        }
        return upView.canScrollVertically(-1);
    }


    private boolean childCanScrollUp() {
        if (targetView == null) {
            Log.i("ppp", "targetView is null");
            return false;
        }
        return ViewCompat.canScrollVertically(targetView, -1);
    }

    private boolean childCanScrollDown() {
        if (targetView == null) {
            Log.i("ppp", "targetView is null");
            return true;
        }
        return targetView.canScrollVertically(-1);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroll.computeScrollOffset()) {
            scrollTo(mScroll.getCurrX(), mScroll.getCurrY());
            invalidate();
        }
    }

    public interface MySwipeLayoutListener {
        void onPagePreChanged(int currPage);

        void onPageAfterChanged(int currPage);
    }

    //ListView已到顶部的判断

    public boolean isListViewReachTopEdge(final ListView listView) {
        boolean result = false;
        if (listView.getFirstVisiblePosition() == 0) {
            final View topChildView = listView.getChildAt(0);
            result = topChildView.getTop() == 0;
        }
        return result;
    }

    //ListView已到底部的判断
    public boolean isListViewReachBottomEdge(final ListView listView) {
        boolean result = false;
        if (listView.getLastVisiblePosition() == (listView.getCount() - 1)) {
            final View bottomChildView = listView.getChildAt(listView.getLastVisiblePosition() - listView.getFirstVisiblePosition());
            result = (listView.getHeight() >= bottomChildView.getBottom());
        }
        return result;
    }
}
