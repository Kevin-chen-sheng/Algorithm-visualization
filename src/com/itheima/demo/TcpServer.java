package com.itheima.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10002);
        Socket accept = serverSocket.accept();
        String inetAddress = accept.getInetAddress().getHostAddress();
        System.out.println(inetAddress+"...connect");
        InputStream inputStream = accept.getInputStream();
        byte[] bytes = new byte[1024];
        int len = inputStream.read(bytes);
        String s = new String(bytes, 0, len);
        System.out.println(s);
        accept.close();
        serverSocket.close();


    }

}
