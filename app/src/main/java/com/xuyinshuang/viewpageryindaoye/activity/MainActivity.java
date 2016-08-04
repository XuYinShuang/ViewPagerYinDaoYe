package com.xuyinshuang.viewpageryindaoye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuyinshuang.viewpageryindaoye.R;
import com.xuyinshuang.viewpageryindaoye.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView textView;
    private ViewGroup main, group;
    private List<View> viewList;//布局集合
    private ImageView[] imageViews;//用来存放圆点的集合
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        initView();


    }

    private void initView() {

        //得到视图
        LayoutInflater inflater = getLayoutInflater();

        main = (ViewGroup) inflater.inflate(R.layout.activity_main, null);
        viewPager = (ViewPager) main.findViewById(R.id.viewPager);
        group = (LinearLayout) main.findViewById(R.id.layout);

        viewList = new ArrayList<>();
        viewList.add(inflater.inflate(R.layout.weimei1, null));
        viewList.add(inflater.inflate(R.layout.weimei2, null));
        viewList.add(inflater.inflate(R.layout.weimei3, null));
        View view = inflater.inflate(R.layout.weimei4, null);
        viewList.add(view);
        textView = (TextView) view.findViewById(R.id.textView);

        addRound();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intent);
            }
        });

    }

    //添加小圆点
    private void addRound() {
        //按照视图的数量来添加圆点
        imageViews = new ImageView[viewList.size()];

        //动态添加圆点
        for (int i = 0; i < viewList.size(); i++) {
            imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
            imageViews[i] = imageView;
//
            //默认选中第一张图片
            if (i == 0) {
                imageViews[i].setImageResource(R.drawable.dot_red);
            } else {
                imageViews[i].setImageResource(R.drawable.dot_white);
            }
            //点击小圆点事件监听
            imageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                viewPager.setCurrentItem(whereItem(imageViews,(ImageView)v));
                }
            });
            //添加小圆点视图
            group.addView(imageViews[i]);

            MyPagerAdapter adapter = new MyPagerAdapter(viewList, this);
            viewPager.setAdapter(adapter);


        }
        //绑定主视图
        setContentView(main);
        viewPager.setOnPageChangeListener(new MyPageListener());

    }

    //判断用户点击圆点的哪一项，使其定位到指定图片的位置下
    private int whereItem(ImageView[] imageViews, ImageView imageView) {
        for (int i = 0; i < imageViews.length; i++) {
            if (imageViews[i] == imageView) {
                return i;
            }
        }
        return -1;
    }

    private class MyPageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < imageViews.length; i++) {
                if (position == i) {
                    imageViews[i].setImageResource(R.drawable.dot_red);
                } else {
                    imageViews[i].setImageResource(R.drawable.dot_white);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
