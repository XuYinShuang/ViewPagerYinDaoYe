package com.xuyinshuang.viewpageryindaoye.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Administrator on 2016/8/3.
 */
public class MyPagerAdapter extends PagerAdapter {

    private List<View> viewList;
    private Context context;

    public MyPagerAdapter(List<View> viewList, Context context) {
        this.viewList = viewList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    //
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    //销毁视图，防止内存溢出
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(viewList.get(position));
        Toast.makeText(context, "我被你踢走了", Toast.LENGTH_SHORT).show();
    }
}
