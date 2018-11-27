package com.example.juicekaaa.xiaofang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.juicekaaa.xiaofang.client.UdpSendThread;
import com.example.juicekaaa.xiaofang.firebox.FireBox;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_one)
    Button btnOne;
    @BindView(R.id.btn_two)
    Button btnTwo;
    @BindView(R.id.btn_three)
    Button btnThree;
    @BindView(R.id.btn_four)
    Button btnFour;

    private final static String BOX_IP = "";
    private final static int BOX_PORT = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        new UdpReceiveThread().start();
    }

    @OnClick({R.id.btn_one, R.id.btn_two, R.id.btn_three, R.id.btn_four})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                new UdpSendThread("68010101ffff16").start();
//                fireBox.openBox();
                break;
            case R.id.btn_two:
                new UdpSendThread("68010102ffff16").start();
                break;
            case R.id.btn_three:
                new UdpSendThread("68010103ffff16").start();
                break;
            case R.id.btn_four:
                new UdpSendThread("68010104ffff16").start();
                break;
        }
    }
}
