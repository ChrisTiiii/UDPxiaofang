package com.example.juicekaaa.xiaofang.client;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UdpSendThread extends Thread {
    public static final String TAG = "UdpSendThread";
    private static int i = 0; //静态变量，记录发送消息的次数
    private static final int PORT = 12342;
    private static String msg = "";

    public UdpSendThread(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            Set<Map.Entry<String, String>> entrySet = getAllLocalBroadIp().entrySet();
//            String localIp = IpAddress.getLocalIPAddress();
            String broadIp = null;
            for (Map.Entry<String, String> entry : entrySet) { //遍历当前的 IP
                String localIp = entry.getKey();
                broadIp = entry.getValue();
                Log.i(TAG, "broadIp:" + broadIp + "\nlocalIp:" + localIp);
            }

            Log.d(TAG, "*** run udp send ***");
            DatagramSocket socket = new DatagramSocket(22223); //自定端口号
            InetAddress address = InetAddress.getByName(broadIp); //通过当前 IP 建立相应的 InetAddress
//            String data = "I love you" + "( " + i++ + " )";
            byte dataByte[] = msg.getBytes(); //建立数据
            DatagramPacket packet = new DatagramPacket(dataByte, dataByte.length, address, PORT); //通过该数据建包
            socket.send(packet); //开始发送该包
            socket.close(); //其实对于发送方来说没必要关闭 socket，但为了防止无法预知的意外，建议关闭
            Log.i(TAG, "send done，data: " + msg);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 获取本机ip的广播地址
     */
    private static Map<String, String> getAllLocalBroadIp() {
        Map<String, String> LocalIpAndbroadcastIp = new HashMap<>();
        try {
            Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
            while (b.hasMoreElements()) {
                for (InterfaceAddress f : b.nextElement().getInterfaceAddresses()) {
                    if (f.getBroadcast() != null) {
                        LocalIpAndbroadcastIp.put(f.getAddress().getHostAddress(), f.getBroadcast().getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        Log.e(TAG, LocalIpAndbroadcastIp + "");
        return LocalIpAndbroadcastIp;
    }

}