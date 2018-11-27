package com.example.juicekaaa.xiaofang.firebox;

import android.content.Context;

import com.example.juicekaaa.xiaofang.udp.UDPSocket;

import static com.example.juicekaaa.xiaofang.udp.EncodingConversionTools.HexString2Bytes;


public class FireBox {
    private UDPSocket udpSocket;

    public FireBox(Context context, String ip, int port) {
        udpSocket = new UDPSocket(context, ip, port);
        udpSocket.startUDPSocket();
    }


    public void openBox() {
        final String OPEN_BOX = "68010102ffff16";
        udpSocket.sendBytes(HexString2Bytes(OPEN_BOX));
    }
}
