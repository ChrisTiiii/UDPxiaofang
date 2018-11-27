package com.example.juicekaaa.xiaofang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: ZhongMing
 * DATE: 2018/11/27 0027
 * Description:
 **/
public class FirstActivity extends AppCompatActivity {
    @BindView(R.id.gridview)
    GridView gridview;
    @BindView(R.id.banner1)
    Banner banner1;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;
    private List<Integer> bannerList = new ArrayList();


    final int img[] = new int[]{R.drawable.wz, R.drawable.opendoor, R.drawable.qd,
            R.drawable.yh, R.drawable.msg, R.drawable.hd, R.drawable.qz, R.drawable.person, R.drawable.sq};

    final String text[] = new String[]{"物资管理", "应急开门", "签到巡逻", "隐患处理"
            , "我的消息", "培训活动", "在线求助", "个人中心", "授权中心"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
        ButterKnife.bind(this);
        //初始化数据
        initData();
        String[] from = {"img", "text"};
        int[] to = {R.id.img, R.id.text};
        adapter = new SimpleAdapter(this, dataList, R.layout.first_item, from, to);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                switch (position) {
                    case 1:
                        startActivity(new Intent(FirstActivity.this, MainActivity.class));
                        break;
                }

            }
        });


    }

    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < img.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", img[i]);
            map.put("text", text[i]);
            dataList.add(map);
        }

        bannerList = new ArrayList<>();
        bannerList.add(R.drawable.banner_1);
        bannerList.add(R.drawable.banner_2);
        bannerList.add(R.drawable.banner_3);
        bannerList.add(R.drawable.banner_4);
        //加载图片
        banner1.setImages(bannerList).setImageLoader(new GlideImageLoader()).start();

    }
}
